package Entities;

public abstract class User {
    protected int id;
    protected String email;
    protected String username;
    protected String password;

    public User(){}

    public User(int id, String email, String username, String password)
    {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public abstract String toString();
}
