package com.bmw.ctw.workstation.rack.api.team.boundary;

import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import com.bmw.ctw.workstation.rack.api.team.entity.Team;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.SecurityAttribute;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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
public class TeamResourceIT extends IntegrationTestBase {
    private static final Jsonb JSONB = JsonbBuilder.create();

    private Team aTeam;

    @BeforeAll
    void setUp() {
        this.aTeam = new Team();
        this.aTeam.name = "Smallville";
        this.aTeam.product = "Superman ComicBook";
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
            .body(JSONB.toJson(this.aTeam, Team.class))
            .when()
            .post("/" + Routes.TEAM);

        // then
        this.aTeam = response.body().as(Team.class);
        String location = response.getHeader(HttpHeaders.LOCATION);
        assertThat("Should return 201 created code", response.getStatusCode(), is(CREATED.getStatusCode()));
        assertThat("Should have Team Id in the Header under Location key", location, containsString(Routes.TEAM + "/" + this.aTeam.id));
    }
}
