import java.util.ArrayList;

public class BuyerOrSeller extends User
{
    String numberOfOwnedProperties;
    String propertyNames;

    public BuyerOrSeller(String username, String password, String name, String numberOfOwnedProperties, String propertyNames)
    {
        super(username, password, name);
        this.name = name;
        this.password = password;
        this.propertyNames = propertyNames;
        this.numberOfOwnedProperties = numberOfOwnedProperties;
    }

}
