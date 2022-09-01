import javax.lang.model.util.ElementScanner14;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.*;

import java.util.ArrayList;

import java.util.Random;

public class Main 
{ 
    public static void main(String[] args) throws SQLException
    {
        dashBoard(); 
    }

    //Write to database generic function, taking 3 arguments which correspond to database entries for a new user and the other two are set two 0 as default
    public static void writeToDatabaseNewUser(String val1, String val2, String val3)
    {
        //Login to database
        String dbUname = "root";
        String dbPword = "root";
        String dbQuery = "INSERT INTO USERS VALUES ('" +  val1 + "'," + "0," + "0,'" + val2 + "','" + val3 + "', ''" + ");";

        String url = "jdbc:mysql://localhost/users";
 
        //Check for drivers
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
 
        //Connect and execute query
        try
        {
            Connection conn = DriverManager.getConnection(url, dbUname, dbPword);
            Statement statement = conn.createStatement();
            statement.executeUpdate(dbQuery);
        }
 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Find user details in database, match username and password
    public static Boolean findUserInDataBase(String val1, String val2)
    {
        //If details are correct login becomes true
        Boolean login = false;

        //Login to database
        String dbUname = "root";
        String dbPword = "root";
        String dbQuery = "SELECT * FROM USERS WHERE username = '" + val1 + "' AND password = '" + val2 + "';";
        String url = "jdbc:mysql://localhost/users";
  
        //Check for drivers
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
  
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
  
        //Connect and execute query
        try
        {
            Connection conn = DriverManager.getConnection(url, dbUname, dbPword);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(dbQuery);

            if (result.next())
            {
                //If statement executed successfully login is true
                login = true;
            }

            else
            {
                createMessage("Username or password incorrect, please re-enter.");
                dashBoard();
            }
        }
  
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return login;
    }

    //Read owned and sold properties for user dashboard using username to find it
    public static String readOwnedAndSoldProperties(String username)
    {
         //Login to database
         String dbUname = "root";
         String dbPword = "root";
         String dbQuery = "SELECT * FROM users WHERE username = '" + username + "';";
         String url = "jdbc:mysql://localhost/users";
   
         //Check for drivers
         try
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
         }
   
         catch (ClassNotFoundException e)
         {
             e.printStackTrace();
         }
   
         //Connect and execute query
         try
         {
             Connection conn = DriverManager.getConnection(url, dbUname, dbPword);
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(dbQuery);
 
             while (result.next())
             {
                 //get name from database based on sql query
                 String owned = result.getString(2);
                 String sold = result.getString(3);
                 final String concatenate = owned + ", Properties sold: " + sold;
                 return concatenate; 
             }
         }
 
         catch (SQLException e)
         {
             e.printStackTrace();
             createMessage("Error finding properties for current user");
             dashBoard();
         }

         //if fails return nothing
         createMessage("Error finding properties for current user");
         dashBoard();
         return null;
    }

    //Increment properties bought by one
    public static void incrementPropertyBought(String username)
    {
         //Login to database
         String dbUname = "root";
         String dbPword = "root";
         String dbQuery = "SELECT numberOfOwnedProperties FROM users WHERE username = '" + username + "'";
         String url = "jdbc:mysql://localhost/users";
   
         //Check for drivers
         try
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
         }
   
         catch (ClassNotFoundException e)
         {
             e.printStackTrace();
         }
   
         //Connect and execute query
         try
         {
             Connection conn = DriverManager.getConnection(url, dbUname, dbPword);
             Statement statement = conn.createStatement();
             Statement secondStatement = conn.createStatement();
             ResultSet result = statement.executeQuery(dbQuery);
 
             while (result.next())
             {
                 //get number of currently owned properties for user from database based on sql query
                 int number = result.getInt(1);
                 int secondNumber = number++;
                 String secondDbQuery = "UPDATE users SET numberOfOwnedProperties = '" + secondNumber + "' WHERE username = '" + username + "'";
                 secondStatement.executeUpdate(secondDbQuery);
                 dashBoard();
             }
         }
 
         catch (SQLException e)
         {
             e.printStackTrace();
         }
    }

    //Get name of existing user
    public static String getExistingName(String val1, String val2)
    {
        //Login to database
        String dbUname = "root";
        String dbPword = "root";
        String dbQuery = "SELECT * FROM users WHERE username = '" + val1 + "' AND password = '" + val2 + "';";
        String url = "jdbc:mysql://localhost/users";
  
        //Check for drivers
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
  
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
  
        //Connect and execute query
        try
        {
            Connection conn = DriverManager.getConnection(url, dbUname, dbPword);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(dbQuery);

            while (result.next())
            {
                //get name from database based on sql query
                String name = result.getString(1);
                return name;
            }
        }

        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    //Set up message box for GUI
    public static JFrame createMessage(String message)
    {
        JFrame jframe = new JFrame();
        JOptionPane.showMessageDialog(jframe, message);
        return jframe;
    }

    //Get string from user
    public static String getMessageString(String message)
    {
        JFrame jframe = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jframe, message);
        return getMessage;
    }

    //Get double from user
    public static Double getMessageDouble(String message)
    {
        JFrame jframe = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jframe, message);
        Double getMessageDouble = Double.parseDouble(getMessage);
        return getMessageDouble;
    }

    //Get int from user
    public static int getMessageInt(String message)
    {
        JFrame jframe = new JFrame();
        String getMessage = JOptionPane.showInputDialog(jframe, message);
        int getMessageInt = Integer.parseInt(getMessage);
        return getMessageInt;
    }

    //Start of program, ask user whether they are a buyer or seller
    public static User getUser()
    {
        createMessage("Welcome to the Property Market Application.");

        String existingUserCheck = getMessageString("Are you an existing user? Y/N");
        
        if (existingUserCheck.equals("Y") || existingUserCheck.equals("y"))
        {
          //login
          String user = getMessageString("Enter username");
          String pass = getMessageString("Enter password");
          User existing = new User(user, pass, null);

          //Read from database
          Boolean existingUser = findUserInDataBase(existing.username, existing.password);
          
          //Register name of user from database to object
          existing.setName(getExistingName(existing.username, existing.password));

          return existing;
        }
        
        else if (existingUserCheck.equals("N") || existingUserCheck.equals("n"))
        {
            //Create account    
            String name = getMessageString("Enter name:");
            String user = getMessageString("Create username:");
            String pass = getMessageString("Create password:");
            User newUser = new User(user, pass, name);

            //Write to database
            writeToDatabaseNewUser(name, user, pass);

            return newUser;
        }

        else
        {
            createMessage("Incorrect option, please re-enter.");    
            getUser();
        }
        //Return none
        User nullUser = new User(null, null, null);

        return nullUser;
    }

    //Dashboard allowing user to enter options for program (start of main program)
    public static BuyerOrSeller dashBoard()
    {
        User details = getUser();

        createMessage("Welcome, " + details.getName());

        //Create Buyer or Seller object
        BuyerOrSeller choice = new BuyerOrSeller(details.getUsername(), details.getPassword(), details.getName(), null, null);

        String option = getMessageString(details.name + " - Home \n Properties Owned: " + readOwnedAndSoldProperties(details.getUsername()) + " \n Options: \n 1. Buy \n 2. Sell");

        //Make an array of function to separate values to be returned (properties owned and sold)
        String[] objectProperties = (readOwnedAndSoldProperties(details.getUsername())).split("");
        
        final int endOfArray = objectProperties.length;
        String OwnedProperties = objectProperties[0];

        //To parse to next function
        choice.numberOfOwnedProperties = OwnedProperties;

        if (option.equals("1"))
        {
            buy(choice);
        }

        else if (option.equals("2"))
        {
            sell(choice);
        }

        else 
        {
            createMessage("Incorrect option please re-enter details");
            dashBoard();
        }

        return choice; 
    }

    public static int generateRandomNumber(int number)
    {
        Random rand = new Random();
        int randNumber = rand.nextInt(number);

        //Ensure the number can't be less than one
        if (randNumber < 1)
        {
            randNumber ++;
        }
        return randNumber;
    }

    public static Double generateRandomNumberDouble(Double number)
    {
        Random rand = new Random();
        Double randNumber = rand.nextDouble(number);
        return randNumber;
    }

    public static void buy(BuyerOrSeller buyer)
    {
        createMessage("Current Available properties: ");

        //Array Lists for the loop which displays the properties and market values
        ArrayList Properties = new ArrayList(); 
        ArrayList marketValues = new ArrayList();

        Random rand = new Random();
        int numberOfPropertiestoDisplay = rand.nextInt(5);

        //Types of houses
        String[] types = {"House", "Apartment"};
        

        //Algorithm to create random objects from house (up to 5 random)
        int randomTypeToUse = generateRandomNumber(types.length);

        String typeForHouse = types[randomTypeToUse];

        int numberOfRoomsRandom = generateRandomNumber(7);
        int sizeInSquareFeetRandom = generateRandomNumber(100000);

        //Potential houses to display depending on how many available
        House PotentialHouseOne = new House(typeForHouse, numberOfRoomsRandom, sizeInSquareFeetRandom); 
        PotentialHouseOne.setType(typeForHouse);
        int houseMarketValueOne = PotentialHouseOne.newMarketValue(PotentialHouseOne.type);

        House PotentialHouseTwo = new House(typeForHouse, numberOfRoomsRandom, sizeInSquareFeetRandom); 
        PotentialHouseTwo.setType(typeForHouse);
        int houseMarketValueTwo = PotentialHouseTwo.newMarketValue(PotentialHouseTwo.type);

        
        House PotentialHouseThree = new House(typeForHouse, numberOfRoomsRandom, sizeInSquareFeetRandom); 
        PotentialHouseThree.setType(typeForHouse);
        int houseMarketValueThree = PotentialHouseThree.newMarketValue(PotentialHouseThree.type);

        
        House PotentialHouseFour = new House(typeForHouse, numberOfRoomsRandom, sizeInSquareFeetRandom); 
        PotentialHouseFour.setType(typeForHouse);
        int houseMarketValueFour = PotentialHouseFour.newMarketValue(PotentialHouseFour.type);

        
        House PotentialHouseFive = new House(typeForHouse, numberOfRoomsRandom, sizeInSquareFeetRandom); 
        PotentialHouseFive.setType(typeForHouse);
        int houseMarketValueFive = PotentialHouseFive.newMarketValue(PotentialHouseFive.type);

        Properties.add(PotentialHouseOne.getType() + "\nNumber of rooms: " + PotentialHouseOne.getNumberOfRooms() + "\nSize in Square Feet: "+ PotentialHouseOne.getSizeInSquareFeet() + "sqft" + "\nMarket Value: £" + houseMarketValueOne);
        Properties.add(PotentialHouseTwo.getType() + "\nNumber of rooms: " + PotentialHouseTwo.getNumberOfRooms() + "\nSize in Square Feet: "+ PotentialHouseTwo.getSizeInSquareFeet() + "sqft" + "\nMarket Value: £" + houseMarketValueTwo);
        Properties.add(PotentialHouseThree.getType() + "\nNumber of rooms: " + PotentialHouseThree.getNumberOfRooms() + "\nSize in Square Feet: "+ PotentialHouseThree.getSizeInSquareFeet() + "sqft" + "\nMarket Value: £" + houseMarketValueThree);
        Properties.add(PotentialHouseFour.getType() + "\nNumber of rooms: " + PotentialHouseFour.getNumberOfRooms() + "\nSize in Square Feet: "+  PotentialHouseFour.getSizeInSquareFeet() + "sqft" + "\nMarket Value: £" + houseMarketValueFour);
        Properties.add(PotentialHouseOne.getType() + "\nNumber of rooms: " + PotentialHouseFive.getNumberOfRooms() + "\nSize in Square Feet: "+ PotentialHouseFive.getSizeInSquareFeet() + "sqft" + "\nMarket Value: £" + houseMarketValueFive);
        marketValues.add(houseMarketValueOne);
        marketValues.add(houseMarketValueTwo);
        marketValues.add(houseMarketValueThree);
        marketValues.add(houseMarketValueFour);
        marketValues.add(houseMarketValueFive);


        createMessage("The following Properties are available: ");

        if(numberOfPropertiestoDisplay == 0)
        {
            createMessage("There are no properties to display at this time.");
            dashBoard();
        }

        for (int i = 0; i < numberOfPropertiestoDisplay; i++)
        {
            String confirmBuy = "";
            createMessage(Properties.get(i).toString());
            confirmBuy = getMessageString("Property " + (i + 1) + ": Buy? Y/N/Exit");
    
            if (confirmBuy.equals("Y") || (confirmBuy.equals("y")))
            {
                i = numberOfPropertiestoDisplay;
                //In theory use boolean logic for all house market values
                finaliseBuy(houseMarketValueOne, buyer.getUsername());
            }

            else if (confirmBuy.equals("Exit") || (confirmBuy.equals("exit")))
            {
                System.exit(0);
            }  
        }

        //If number of properties available exhausted
        createMessage("There are no properties to display at this time, please try again later");
        dashBoard();

    }

    //Also display potential on market houses from sellers (cant be same seller)

    public static void sell(BuyerOrSeller seller)
    {
        //Enter details and sell

        //Add to database
    }

    public static void finaliseBuy(int marketValue, String user)
    {
        int years = getMessageInt("How many years would you like to pay off the mortgage of £" + marketValue + " on?");
        Mortgage buyer = new Mortgage(generateRandomNumberDouble(30.0), marketValue, years);

        createMessage("You now have a mortgage consisting of a " + buyer.getAprRate() + "% APR Rate, with a value of £" + buyer.getTotalAmount() + ", which is to be paid off over " + buyer.getNumberOfYears() + " years.");
        createMessage("Thank you for using the Property Market Application.");
        incrementPropertyBought(user);
    }
}
