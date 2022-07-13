package restfulBooker.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader()
    {
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }

        return dataLoader;
    }

    public String getCheckin(){
        String prop = properties.getProperty("checkin");
        if(prop != null) return prop;
        else throw new RuntimeException("property checkin is not specified in the config properties file");
    }

    public String getCheckout(){
        String prop = properties.getProperty("checkout");
        if(prop != null) return prop;
        else throw new RuntimeException("property checkout is not specified in the config properties file");
    }

    public String getFirstname(){
        String prop = properties.getProperty("firstname");
        if(prop != null) return prop;
        else throw new RuntimeException("property firstname is not specified in the config properties file");
    }

    public String getLastname(){
        String prop = properties.getProperty("lastname");
        if(prop != null) return prop;
        else throw new RuntimeException("property lastname is not specified in the config properties file");
    }

    public String getTotalprice(){
        String prop = properties.getProperty("totalprice");
        if(prop != null) return prop;
        else throw new RuntimeException("property totalprice is not specified in the config properties file");
    }

    public String getDepositpaid(){
        String prop = properties.getProperty("depositpaid");
        if(prop != null) return prop;
        else throw new RuntimeException("property depositpaid is not specified in the config properties file");
    }

    public String getAdditionalneeds(){
        String prop = properties.getProperty("additionalneeds");
        if(prop != null) return prop;
        else throw new RuntimeException("property additionalneeds is not specified in the config properties file");
    }
}
