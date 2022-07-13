package restfulBooker.api.applicationApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import restfulBooker.api.RestResource;
import restfulBooker.pojo.SerializingBooking;

import static restfulBooker.api.Route.BOOKING;

public class BookingApi {

    @Step
    public static Response post(SerializingBooking serializingBooking)
    {
        return RestResource.post(serializingBooking, BOOKING);

    }

    @Step
    public static Response get()
    {
        return RestResource.get(BOOKING);
    }

    @Step
    public static Response get(String bookingID)
    {
        return RestResource.get(BOOKING + bookingID);
    }

    @Step
    public static Response put(SerializingBooking serializingBooking, String bookingID){
        return RestResource.put(serializingBooking, BOOKING + bookingID);
    }

    @Step
    public static Response patch(SerializingBooking serializingBooking, String bookingID)
    {
        return RestResource.patch(serializingBooking, BOOKING + bookingID);
    }

    @Step
    public static Response delete(String bookingID)
    {
        return RestResource.delete(BOOKING + bookingID);
    }
}
