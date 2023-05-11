package Entities;

public class Artist extends User{
    private int followers;

    public Artist(){}

    public Artist(int id, String email, String username, String password, int followers)
    {
        super(id, email, username, password);
        this.followers = followers;
    }

    public int getFollowers()
    {
        return followers;
    }

    public void setFollowers(int followers)
    {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "Username: " + this.username + '\n' + "Email: " + this.email + '\n' + "Password: " +
                this.password.toString() + '\n' + "Followers: " + this.followers;
    }
}
