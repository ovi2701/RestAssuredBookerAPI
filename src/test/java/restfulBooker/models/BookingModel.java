package restfulBooker.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingModel {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private String additionalneeds;
    private String checkin;
    private String checkout;
}
