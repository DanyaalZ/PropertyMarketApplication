public class User 
{
    String username;
    String password;
    String name;

    public User(String username, String password, String name)
    {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
