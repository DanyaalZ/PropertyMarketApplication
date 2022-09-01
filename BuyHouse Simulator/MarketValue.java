import java.util.Random;

public class MarketValue extends House
{
    public int marketValue;

    public MarketValue(String type)
    {
        super(type);
        Random Rand = new Random();

        int upperBound = 0;
        int lowerBound = 0;

        if (type == "Apartment")
        {
            upperBound = 1000000;
            lowerBound = 150000;
        }

        else if (type == "House")
        {
            upperBound = 5000000;
            lowerBound = 450000;
        }
        
        //Generate random price based on an upperbound and lowerbound
        int amount = Rand.nextInt((upperBound-lowerBound) + lowerBound);

        this.marketValue = amount;
    }
}