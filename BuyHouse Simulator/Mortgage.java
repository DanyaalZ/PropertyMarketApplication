public class Mortgage
{
    Double AprRate;
    int totalAmount;
    int numberOfYears;

    public Mortgage(Double AprRate, int totalAmount, int numberOfYears)
    {
        this.AprRate = AprRate;
        this.totalAmount = totalAmount;
        this.numberOfYears = numberOfYears;
    }

    public void setAprRate(Double aprRate)
    {
        this.AprRate = AprRate;
    }

    public Double getAprRate() 
    {
        return AprRate;
    }

    public void setNumberOfYears(int numberOfYears) 
    {
        this.numberOfYears = numberOfYears;
    }

    public int getNumberOfYears()
    {
        return numberOfYears;
    }

    public int getTotalAmount() 
    {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) 
    {
        this.totalAmount = totalAmount;
    }
    
}
