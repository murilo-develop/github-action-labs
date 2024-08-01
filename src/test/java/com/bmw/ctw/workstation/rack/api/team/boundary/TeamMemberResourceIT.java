package com.bmw.ctw.workstation.rack.api.team.boundary;

import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import com.bmw.ctw.workstation.rack.api.team.entity.Role;
import com.bmw.ctw.workstation.rack.api.team.entity.Team;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMember;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.SecurityAttribute;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.HttpHeaders;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testsupport.IntegrationTestBase;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
@TestSecurity(user = "test@academy.com", roles = "Zen.Developer", attributes = {
    @SecurityAttribute(key = "upn", value = "ctwxxx@criticaltechworks.com") })
@JwtSecurity(claims = {
    @Claim(key = "email", value = "xxx@criticaltechworks.com"),
    @Claim(key = "upn", value = "ctwxxx@criticaltechworks.com"),
    @Claim(key = "name", value = "Brother")
})
public class TeamMemberResourceIT extends IntegrationTestBase {
    private static final Jsonb JSONB = JsonbBuilder.create();

    private final Team aTeam = new Team();
    private TeamMember aTeamMember;

    @BeforeAll
    @Transactional
    void setUp() {
        this.aTeam.name = "Spider-man";
        this.aTeam.product = "Amazing Spider-man";
        this.aTeam.persist();

        this.aTeamMember = new TeamMember();
        this.aTeamMember.name = "Peter Parker";
        this.aTeamMember.ctwCode = "CTW99999";
        this.aTeamMember.role = Role.ROCKSTAR_DEVELOPER;
        this.aTeamMember.team = this.aTeam;
    }

    @AfterAll
    void cleanUp() {
        cleanDatabase();
    }

    @Test
    void shouldCreateTeam() {
        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .body(JSONB.toJson(this.aTeamMember, TeamMember.class))
            .when()
            .post("/" + Routes.TEAM_MEMBER);

        // then
        this.aTeamMember = response.body().as(TeamMember.class);
        String location = response.getHeader(HttpHeaders.LOCATION);
        assertThat("Should return 201 created code", response.getStatusCode(), is(CREATED.getStatusCode()));
        assertThat("Should have Team Member Id in the Header under Location key", location, containsString(Routes.TEAM_MEMBER + "/" + this.aTeamMember.id));
    }
}
