package Services;

import Entities.PremiumUser;

//import java.util.ArrayList;
//import java.util.List;
import java.util.*;

public class PremiumUserService {
    private List<PremiumUser> premiumUsers = new ArrayList<>();
    private static PremiumUserService instance;

    private PremiumUserService(){}

    public static PremiumUserService getInstance()
    {
        if(instance == null)
        {
            instance = new PremiumUserService();
        }
        return instance;
    }

    public List<PremiumUser> getPremiumUsers()
    {
        List<PremiumUser> premiumUsers1 = new ArrayList<>();
        premiumUsers1.addAll(this.premiumUsers);
        return premiumUsers1;
    }

    public PremiumUser getPremiumUserById(int i)
    {
        PremiumUser premiumUser = new PremiumUser();
        for(int j = 0; j < this.premiumUsers.size(); j++)
        {
            if(this.premiumUsers.get(j).getId() == i)
            {
                premiumUser = this.premiumUsers.get(j);
            }
        }
        return premiumUser;
    }

    public void updatePremiunUser(int i, PremiumUser premiumUser)
    {
        for(int j = 0; j < this.premiumUsers.size(); j++)
        {
            if(this.premiumUsers.get(j).getId() == i)
            {
                this.premiumUsers.remove(j);
                this.premiumUsers.add(i, premiumUser);
                break;
            }
        }
    }

    public void addPremiumUser(PremiumUser premiumUser)
    {
        this.premiumUsers.add(premiumUser);
    }

    public void deletePremiumUser(PremiumUser premiumUser)
    {
        for(int i = 0; i < this.premiumUsers.size(); i++)
        {
            if(this.premiumUsers.get(i).equals(premiumUser))
            {
                this.premiumUsers.remove(premiumUser);
                break;
            }
        }
    }

    public PremiumUser readPremiumUser()
    {
        Scanner scanner = new Scanner(System.in);
        PremiumUser premiumUser = new PremiumUser();
        System.out.println("Id");
        try{
            premiumUser.setId(scanner.nextInt());
        }catch (Exception e) {
            System.out.println("Provide int");
            premiumUser.setId(scanner.nextInt());
        }

        System.out.println("Email: ");
        premiumUser.setEmail(scanner.next());

        System.out.println("Username: ");
        premiumUser.setUsername(scanner.next());

        System.out.println("Password: ");
        premiumUser.setPassword(scanner.next());

        System.out.println("Plan: ");
        premiumUser.setPlan(scanner.next());

        System.out.println("Price: ");
        try {
            premiumUser.setPrice(scanner.nextDouble());
        } catch (Exception e){
            System.out.println("Provide double");
            premiumUser.setPrice(scanner.nextDouble());
        }

        return premiumUser;
    }
}
