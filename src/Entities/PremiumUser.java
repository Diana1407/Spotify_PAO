package Entities;

public class PremiumUser extends User{
    private String plan;
    private double price;

    public PremiumUser(){}

    public PremiumUser(int id, String email, String username, String password, String plan, double price)
    {
        super(id, email, username, password);
        this.plan = plan;
        this.price = price;
    }

    public String getPlan()
    {
        return plan;
    }

    public void setPlan(String plan)
    {
        this.plan = plan;
    }

    public double getPrice()
    {
        return price;
    }

    public void setprice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Username: " + this.username + '\n' + "Email: " + this.email + '\n' + "Password: " +
                this.password.toString() + '\n' + "Plan: " + this.plan + '\n' +  "Price: " + this.price;
    }
}
