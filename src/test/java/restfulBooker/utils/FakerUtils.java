package restfulBooker.utils;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FakerUtils {
    public static String generateFirstName()
    {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String generateLastName()
    {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String generateCheckinDate()
    {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Faker faker = new Faker();
        return date.format(faker.date().past(10, TimeUnit.DAYS));
    }

    public static String generateCheckoutDate()
    {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Faker faker = new Faker();
        return date.format(faker.date().future(10, TimeUnit.DAYS));
    }

    public static int generatePrice()
    {
        Faker faker = new Faker();
        return (int)Double.parseDouble(faker.commerce().price(1300, 2000));
    }

    public static String generateAdditionalNeeds()
    {
        Faker faker = new Faker();
        return faker.esports().game();
    }
}
