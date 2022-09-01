public class House 
{
    public String type;
    public int marketValue;
    private String location;
    private int numberOfRooms;
    private int numberOfBeds;
    private int numberOfFloors;
    private int sizeInSquareFeet;
    
    //Constructors for house objects (apartments or houses)
    public House(String type)
    {
        this.type = type;
    }

    public House(String type, int numberOfRooms, int sizeInSquareFeet)
    {
        this.type = type;
        this.marketValue = marketValue;
        this.numberOfRooms = numberOfRooms;
        this.sizeInSquareFeet = sizeInSquareFeet;
    }
    
    //Create the market value for current product (change for others later)
    public int newMarketValue(String type)
    {
        MarketValue market = new MarketValue(type);
        int amount = market.marketValue;
        this.marketValue = amount;
        return amount;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setNumberOfBeds(int amount)
    {
        this.numberOfBeds = amount;
    }

    public int getNumberOfBeds()
    {
        return this.numberOfBeds;
    }

    public void setNumberOfFloors(int amount)
    {
        this.numberOfFloors = amount;
    }

    public int getNumberOfFloors()
    {
        return this.numberOfFloors;
    }

    public void setNumberOfRooms(int amount)
    {
        this.numberOfRooms = amount;
    }

    public int getNumberOfRooms()
    {
        return this.numberOfRooms;
    }

    public void setSizeInSquareFeet(int amount)
    {
        this.sizeInSquareFeet = amount;
    }

    public int getSizeInSquareFeet()
    {
        return this.sizeInSquareFeet;
    }
}
