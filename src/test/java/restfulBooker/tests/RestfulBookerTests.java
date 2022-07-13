package restfulBooker.tests;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import restfulBooker.api.applicationApi.BookingApi;
import restfulBooker.pojo.Booking;
import restfulBooker.pojo.DeserializingBooking;
import restfulBooker.pojo.SerializingBooking;
import restfulBooker.pojo.Bookingdates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static restfulBooker.utils.FakerUtils.*;

@Epic("Restful Booker")
@Feature("Booking API")
public class RestfulBookerTests extends BaseTest {

    @Step
    public SerializingBooking bookingBuilder(String checkin, String checkout, String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds){
        Bookingdates dates = new Bookingdates();
        dates.setCheckin(checkin);
        dates.setCheckout(checkout);
        SerializingBooking requestBooking = new SerializingBooking();
        requestBooking.setFirstname(firstname);
        requestBooking.setLastname(lastname);
        requestBooking.setTotalprice(totalprice);
        requestBooking.setDepositpaid(depositpaid);
        requestBooking.setBookingdates(dates);
        requestBooking.setAdditionalneeds(additionalneeds);

        return requestBooking;
    }

    @Step
    public void assertBookingEqual(Booking responseBooking, SerializingBooking requestBooking)
    {
        assertThat(responseBooking.getFirstname(), is(equalTo(requestBooking.getFirstname())));
        assertThat(responseBooking.getLastname(), is(equalTo(requestBooking.getLastname())));
        assertThat(responseBooking.getTotalprice(), is(equalTo(requestBooking.getTotalprice())));
        assertThat(responseBooking.getDepositpaid(), is(equalTo(requestBooking.getDepositpaid())));
        assertThat(responseBooking.getBookingdates().getCheckin(), is(equalTo(requestBooking.getBookingdates().getCheckin())));
        assertThat(responseBooking.getBookingdates().getCheckout(), is(equalTo(requestBooking.getBookingdates().getCheckout())));
        assertThat(responseBooking.getAdditionalneeds(), is(equalTo(requestBooking.getAdditionalneeds())));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode)
    {
        assertThat(actualStatusCode, is(equalTo(expectedStatusCode)));
    }
//
//    @Test
//    public void shouldBeAbleToGetABookingWithSpecificId(){
//
//        Response response = BookingApi.get("6978");
//        assertThat(response.statusCode(), equalTo(200));
//    }
//    @Test
//    public void shouldBeAbleToCreateABooking(){
//
//        SerializingBooking requestBooking = bookingBuilder("2022-08-06", "2022-08-15", "Andrei", "Marinescu", 1200, true, "Breakfast");
//
//        Response response = BookingApi.post(requestBooking);
//        assertThat(response.statusCode(), equalTo(200));
//
//        DeserializingBooking responseBooking = response.as(DeserializingBooking.class);
//
//        assertBookingEqual(responseBooking.getBooking(), requestBooking);
//
//    }
//
//    @Test
//    public void shouldBeAbleToUpdateABooking(){
//        SerializingBooking updateBooking = bookingBuilder("2022-08-01", "2022-08-09", "Andrei", "Marinescu", 980, true, "Breakfast");
//
//        Response response = BookingApi.put(updateBooking, "25");
//
//        assertThat(response.statusCode(), is(equalTo(200)));
//
//        Booking responseUpdateBooking = response.as(Booking.class);
//
//        assertBookingEqual(responseUpdateBooking, updateBooking);
//    }
//
//    @Test
//    public void shouldBeAbleToPartialUpdateABooking(){
//        SerializingBooking updateBooking = new SerializingBooking();
//        updateBooking.setFirstname("Andrei-Marius");
//        updateBooking.setLastname("Marinescuu");
//
//        Response response = BookingApi.patch(updateBooking, "25");
//        assertThat(response.statusCode(), equalTo(200));
//
//        Booking responseUpdateBooking = response.as(Booking.class);
//        assertThat(responseUpdateBooking.getFirstname(), is(equalTo(updateBooking.getFirstname())));
//        assertThat(responseUpdateBooking.getLastname(), is(equalTo(updateBooking.getLastname())));
//    }
//
//    @Test
//    public void shouldBeAbleToDeleteABooking(){
//        Response response = BookingApi.delete("170");
//        assertThat(response.statusCode(), is(equalTo(201)));
//    }
    @Story("Get all ID's for boogings story")
    @Description("This test is created with the purpose to return all the existing id's for all the bookings.")
    @Test(description = "Get all the ID's for all the bookings")
    @Issue("Issue with extracting ID's")
    @TmsLink("test - get id's")
    public void shouldBeAbleToGetAllIds(){

        Response response = BookingApi.get();
        JsonPath j = new JsonPath(response.asString());
        int length = j.getInt("data.size()");
        assertStatusCode(response.statusCode(), 200);
        assertThat(length, is(greaterThan(0)));
    }

    @Story("CRUD booking operations story")
    @Description("This test has been created to veryfy CRUD operations for booking. Firstly, it is created a booking. Than, based on it's id, it is read, partial updated, full updated and finally, deleted.")
    @Test(description = "Create/Read/Update/Delete booking")
    @Issue("Issue with CRUD operations")
    @TmsLink("test - CRUD operations")
    public void crudBooking(){
        //create
//      ObjectMapper mapper = new ObjectMapper();
//      BookingModel bookingValues = mapper.readValue(Paths.get("src/test/resources/BookingData.json").toFile(), BookingModel.class);
        SerializingBooking requestPostBooking = bookingBuilder(generateCheckinDate(), generateCheckoutDate(), generateFirstName(), generateLastName(), generatePrice(), true, generateAdditionalNeeds());

        Response responsePost = BookingApi.post(requestPostBooking);
        assertStatusCode(responsePost.statusCode(), 200);

        DeserializingBooking responsePostBooking = responsePost.as(DeserializingBooking.class);

        assertBookingEqual(responsePostBooking.getBooking(), requestPostBooking);

        //read
        Response responseGet = BookingApi.get(String.valueOf(responsePostBooking.getBookingid()));
        assertThat(responseGet.statusCode(), equalTo(200));

        Booking responseGetBooking = responseGet.as(Booking.class);
        assertThat(responseGetBooking.getFirstname(), equalTo(requestPostBooking.getFirstname()));
        assertThat(responseGetBooking.getLastname(), equalTo(requestPostBooking.getLastname()));
        assertThat(responseGetBooking.getTotalprice(), equalTo(requestPostBooking.getTotalprice()));
        assertThat(responseGetBooking.getDepositpaid(), equalTo(requestPostBooking.getDepositpaid()));


        //update
        SerializingBooking updateBooking = bookingBuilder(generateCheckinDate(), generateCheckoutDate(), generateFirstName(), generateLastName(), generatePrice(), true, generateAdditionalNeeds());

        Response responseUpdate = BookingApi.put(updateBooking, String.valueOf(responsePostBooking.getBookingid()));

        assertStatusCode(responseUpdate.statusCode(), 200);

        Booking responseUpdateBooking = responseUpdate.as(Booking.class);

        assertBookingEqual(responseUpdateBooking, updateBooking);

        //partialUpdate
        SerializingBooking partialUpdateBooking = new SerializingBooking();
        partialUpdateBooking.setFirstname(generateFirstName());
        partialUpdateBooking.setLastname(generateLastName());

        Response responsePartialUpdate = BookingApi.patch(partialUpdateBooking, String.valueOf(responsePostBooking.getBookingid()));
        assertStatusCode(responsePartialUpdate.statusCode(), 200);

        Booking responsePartialUpdateBooking = responsePartialUpdate.as(Booking.class);
        assertThat(responsePartialUpdateBooking.getFirstname(), is(equalTo(partialUpdateBooking.getFirstname())));
        assertThat(responsePartialUpdateBooking.getLastname(), is(equalTo(partialUpdateBooking.getLastname())));

        //delete
        Response deleteResponse = BookingApi.delete(String.valueOf(responsePostBooking.getBookingid()));
        assertStatusCode(deleteResponse.statusCode(), 201);
    }
}
