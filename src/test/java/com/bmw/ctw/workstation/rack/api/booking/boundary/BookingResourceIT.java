package com.bmw.ctw.workstation.rack.api.booking.boundary;

import com.bmw.ctw.workstation.rack.api.booking.entity.BookingDTO;
import com.bmw.ctw.workstation.rack.api.booking.entity.BookingStatus;
import com.bmw.ctw.workstation.rack.api.infrastructure.constants.boundary.Routes;
import com.bmw.ctw.workstation.rack.api.team.entity.Role;
import com.bmw.ctw.workstation.rack.api.team.entity.Team;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamDTO;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMember;
import com.bmw.ctw.workstation.rack.api.team.entity.TeamMemberDTO;
import com.bmw.ctw.workstation.rack.api.workstation.entity.WorkStation;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.SecurityAttribute;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.HttpHeaders;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import testsupport.IntegrationTestBase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingResourceIT extends IntegrationTestBase {
    private static final Jsonb JSONB = JsonbBuilder.create(new JsonbConfig().withNullValues(Boolean.TRUE));

    private final TeamDTO.Builder aTeamDTOBuilder = new TeamDTO.Builder();
    private final TeamMemberDTO.Builder aTeamMemberDTOBuilder = new TeamMemberDTO.Builder();
    private final BookingDTO.Builder aBookingDTOBuilder = new BookingDTO.Builder();

    private TeamDTO aTeamDTO;
    private TeamDTO anotherTeamDTO;
    private TeamMemberDTO aTeamMemberDTO;
    private TeamMemberDTO anotherTeamMemberDTO;
    private BookingDTO aBookingDTO;
    private BookingDTO anotherBookingDTO;

    @BeforeAll
    @Transactional
    void setUp() {
        Team aTeam = new Team();
        aTeam.name = "Song of Ice and Fire";
        aTeam.product = "Game of Thrones";
        aTeam.persist();

        Team anotherTeam = new Team();
        anotherTeam.name = "Fellowship of the Ring";
        anotherTeam.product = "The Lord of the Rings";
        anotherTeam.persist();

        TeamMember aTeamMember = new TeamMember();
        aTeamMember.team = aTeam;
        aTeamMember.name = "Aegon Tageryan";
        aTeamMember.ctwCode = "CTW99999";
        aTeamMember.role = Role.ROCKSTAR_DEVELOPER;
        aTeamMember.persist();

        TeamMember anotherTeamMember = new TeamMember();
        anotherTeamMember.team = anotherTeam;
        anotherTeamMember.name = "Frodo Baggings";
        anotherTeamMember.ctwCode = "CTW99998";
        anotherTeamMember.role = Role.UX_GURU;
        anotherTeamMember.persist();

        WorkStation aWorkStation = new WorkStation();
        aWorkStation.name = "Westeros";
        aWorkStation.persist();

        WorkStation anotherWorkStation = new WorkStation();
        anotherWorkStation.name = "Middle Earth";
        anotherWorkStation.persist();

        this.aTeamDTO = this.aTeamDTOBuilder.with(builder -> {
            builder.id = aTeam.id;
            builder.name = aTeam.name;
            builder.product = aTeam.product;
        }).build();

        this.anotherTeamDTO = this.aTeamDTOBuilder.with(builder -> {
            builder.id = anotherTeam.id;
            builder.name = anotherTeam.name;
            builder.product = anotherTeam.product;
        }).build();

        this.aTeamMemberDTO = this.aTeamMemberDTOBuilder.with(builder -> {
            builder.id = aTeamMember.id;
            builder.ctwCode = aTeamMember.ctwCode;
            builder.name = aTeamMember.name;
            builder.team = this.aTeamDTO;
        }).build();

        this.anotherTeamMemberDTO = this.aTeamMemberDTOBuilder.with(builder -> {
            builder.id = anotherTeamMember.id;
            builder.ctwCode = anotherTeamMember.ctwCode;
            builder.name = anotherTeamMember.name;
            builder.team = this.anotherTeamDTO;
        }).build();

        this.aBookingDTO = this.aBookingDTOBuilder.with(builder -> {
            builder.teamMember = this.aTeamMemberDTO;
            builder.workStation = aWorkStation;
            builder.bookingFrom = LocalDateTime.of(2024, 7, 1, 0, 0, 0);
            builder.bookingTo = LocalDateTime.of(2024, 7, 2, 0, 0, 0);
            builder.status = BookingStatus.PENDING;
        }).build();

        this.anotherBookingDTO = this.aBookingDTOBuilder.with(builder -> {
            builder.teamMember = this.anotherTeamMemberDTO;
            builder.workStation = anotherWorkStation;
            builder.bookingFrom = LocalDateTime.of(2024, 7, 3, 0, 0, 0);
            builder.bookingTo = LocalDateTime.of(2024, 7, 4, 0, 0, 0);
            builder.status = BookingStatus.CANCELED;
        }).build();
    }

    @AfterAll
    void cleanUp() {
        cleanDatabase();
    }

    @Test
    @Order(1)
    void shouldCreateBooking() {
        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .body(JSONB.toJson(this.aBookingDTO, BookingDTO.class))
            .when()
            .post("/" + Routes.BOOKING);

        // then
        BookingDTO managedBooking = response.body().as(BookingDTO.class);
        this.aBookingDTO = new BookingDTO(
            managedBooking.id(),
            this.aBookingDTO.teamMember(),
            this.aBookingDTO.workStation(),
            this.aBookingDTO.bookingFrom(),
            this.aBookingDTO.bookingTo(),
            this.aBookingDTO.status()
        );
        String location = response.getHeader(HttpHeaders.LOCATION);
        assertThat("Should return 201 created code", response.getStatusCode(), is(CREATED.getStatusCode()));
        assertThat("Should have Booking Id in the Header under Location key", location, containsString(Routes.BOOKING + "/" + managedBooking.id()));
        assertThat("Should Booking DTO equals to the Booking DTO managed", this.aBookingDTO, is(equalTo(managedBooking)));
    }

    @Test
    @Order(2)
    void shouldReadBooking() {
        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get("/" + Routes.BOOKING + "/" + this.aBookingDTO.id());

        // then
        BookingDTO managedBookingDTO = response.body().as(BookingDTO.class);
        assertThat("Should return 200 success code", response.getStatusCode(), is(OK.getStatusCode()));
        assertThat("Should Booking DTO equals to the Booking DTO managed", this.aBookingDTO, is(equalTo(managedBookingDTO)));
    }

    @Test
    @Order(3)
    void shouldUpdateBooking() {
        // given
        this.anotherBookingDTO = new BookingDTO(
            this.aBookingDTO.id(),
            this.anotherBookingDTO.teamMember(),
            this.anotherBookingDTO.workStation(),
            this.anotherBookingDTO.bookingFrom(),
            this.anotherBookingDTO.bookingTo(),
            this.anotherBookingDTO.status()
        );

        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .body(JSONB.toJson(this.anotherBookingDTO, BookingDTO.class))
            .when()
            .put("/" + Routes.BOOKING + "/" + this.anotherBookingDTO.id());

        // then
        BookingDTO managedBooking = response.body().as(BookingDTO.class);
        assertThat("Should return 200 success code", response.getStatusCode(), is(OK.getStatusCode()));
        assertThat("Should Booking DTO equals to the Booking DTO managed", this.anotherBookingDTO, is(equalTo(managedBooking)));
    }

    @Test
    @Order(4)
    void shouldCreateBookingWhenThereIsNoBooking() {
        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .body(JSONB.toJson(this.anotherBookingDTO, BookingDTO.class))
            .when()
            .put("/" + Routes.BOOKING + "/" + UUID.randomUUID());

        // then
        BookingDTO managedBooking = response.body().as(BookingDTO.class);
        this.anotherBookingDTO = new BookingDTO(
            managedBooking.id(),
            this.anotherBookingDTO.teamMember(),
            this.anotherBookingDTO.workStation(),
            this.anotherBookingDTO.bookingFrom(),
            this.anotherBookingDTO.bookingTo(),
            this.anotherBookingDTO.status()
        );
        assertThat("Should return 201 created code", response.getStatusCode(), is(CREATED.getStatusCode()));
        assertThat("Should Booking DTO equals to the Booking DTO managed", this.anotherBookingDTO, is(equalTo(managedBooking)));
    }

    @Test
    @Order(5)
    void shouldRemoveBooking() {
        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .delete("/" + Routes.BOOKING + "/" + this.anotherBookingDTO.id());

        // then
        assertThat("Should return 204 no content code", response.getStatusCode(), is(NO_CONTENT.getStatusCode()));
    }

    @Test
    @Order(6)
    void shouldReadBookings() {
        // when
        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get("/" + Routes.BOOKING);

        // then
        var bookings = response.then().extract().body().as(List.class);
        assertThat("Should return 200 success code", response.getStatusCode(), is(OK.getStatusCode()));
        assertThat("Should return a list of bookings with 1 element", bookings.size(), is(equalTo(1)));
    }
}
