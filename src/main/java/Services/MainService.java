package Services;

import CRUD.ArtistCRUD;
import CRUD.PremiumUserCRUD;
import Config.DatabaseConfig;
import Entities.Artist;
import Entities.PremiumUser;
import Entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainService
{
    public static List<Artist> artists = new ArrayList<>();
    public static List<PremiumUser> premiumUsers = new ArrayList<>();

    AuditService auditService = AuditService.getInstance();

    ArtistCRUD artistCRUD = ArtistCRUD.getInstance();
    PremiumUserCRUD premiumUserCRUD = PremiumUserCRUD.getInstance();

    public void loadData()
    {
        artists = ReadWriteCSV.readArtist();
        premiumUsers = ReadWriteCSV.readPremiumUser();

        artistCRUD.add();
        premiumUserCRUD.add();

        try
        {
            auditService.logAction("load data");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void configureTables()
    {
        artistCRUD.createTable();
        premiumUserCRUD.createTable();

        try
        {
            auditService.logAction("configure tables");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addArtist()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Artist ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Artist a: artists)
                {
                    if(a.getId() == id)
                    {
                        ok = false;
                        break;
                    }
                }

                if(!ok)
                    throw new Exception();
                else
                    break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("Artist ID: ");
            }

            catch (Exception e)
            {
                System.out.println("This Artist ID already exists!");
                System.out.print("Artist ID: ");
            }
        }

        System.out.print("Email: ");
        String email = scan.nextLine();

        System.out.print("Username: ");
        String username = scan.nextLine();

        System.out.print("Password: ");
        String password = scan.nextLine();

        System.out.print("Followers: ");
        int followers;

        while(true)
        {
            try
            {
                followers = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Artist a: artists)
                {
                    if(a.getFollowers() == followers)
                    {
                        ok = false;
                        break;
                    }
                }

                if(!ok)
                    throw new Exception();
                else
                    break;
            }
            catch (Exception e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("Followers: ");
            }
        }

        artists.add(new Artist(id, email, username, password, followers));
        ReadWriteCSV.writeArtist(id, email, username, password, followers);
        artistCRUD.addArtist(id, email, username, password, followers);

        try
        {
            auditService.logAction("add artist");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printArtistById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Artist ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("Artist ID: ");
            }
        }

        if(artistCRUD.getArtistById(id) != null)
            System.out.println(artistCRUD.getArtistById(id).toString());
        else
            System.out.println("This Artist with this ID does not exist!");

        try
        {
            auditService.logAction("print artist by id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updateArtist()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Artist ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("Artist ID: ");
            }
        }

        boolean ok = false;
        for(Artist a: artists)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            System.out.print("New followers number: ");
            int followers;

            while(true)
            {
                try
                {
                    followers = Integer.parseInt(scan.nextLine());

                    boolean okk = true;
                    for(Artist a: artists)
                    {
                        if(a.getFollowers() == followers)
                        {
                            okk = false;
                            break;
                        }
                    }

                    if(!okk)
                        throw new Exception();
                    else
                        break;
                }
                catch (Exception e)
                {
                    System.out.println("Introduce an integer value!");
                    System.out.print("Followers: ");
                }
            }

            artistCRUD.updateArtistFollowers(followers, id);
        }
        else
            System.out.println("This Artist with this ID does not exist!");

        try
        {
            auditService.logAction("update artist");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteArtistById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Artist ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("Artist ID: ");
            }
        }

        boolean ok = false;
        for(Artist a: artists)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            artistCRUD.deleteArtistById(id);
            System.out.println("Artist deleted succesfully!");
        }
        else
            System.out.println("This Artist with this ID does not exist!");

        try
        {
            auditService.logAction("delete artist");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printArtists()
    {
        artistCRUD.showArtists();

        try
        {
            auditService.logAction("prints all artists");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printSortedArtists()
    {
        List<Artist> sortedArtists = artists.stream().sorted(Comparator.comparing(User::getUsername)).collect(Collectors.toList());

        System.out.println("Artists sorted succesfully!");

        for(Artist a: sortedArtists)
        {
            System.out.print(a.toString());
        }
    }

    public void addPremiumUser()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("PremiumUser ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(PremiumUser a: premiumUsers)
                {
                    if(a.getId() == id)
                    {
                        ok = false;
                        break;
                    }
                }

                if(!ok)
                    throw new Exception();
                else
                    break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("PremiumUser ID: ");
            }

            catch (Exception e)
            {
                System.out.println("This PremiumUser ID already exists!");
                System.out.print("PremiumUser ID: ");
            }
        }

        System.out.print("Email: ");
        String email = scan.nextLine();

        System.out.print("Username: ");
        String username = scan.nextLine();

        System.out.print("Password: ");
        String password = scan.nextLine();

        System.out.print("Plan: ");
        String plan = scan.nextLine();

        System.out.print("Price: ");
        double price;

        while(true)
        {
            try
            {
                price = Double.parseDouble(scan.nextLine());

                boolean ok = true;
                for(PremiumUser a: premiumUsers)
                {
                    if(a.getPrice() == price)
                    {
                        ok = false;
                        break;
                    }
                }

                if(!ok)
                    throw new Exception();
                else
                    break;
            }
            catch (Exception e)
            {
                System.out.println("Introduce a double value!");
                System.out.print("Price: ");
            }
        }

        premiumUsers.add(new PremiumUser(id, email, username, password, plan, price));
        ReadWriteCSV.writePremiumUser(id, email, username, password, plan, price);
        premiumUserCRUD.addPremiumUser(id, email, username, password, plan, price);

        try
        {
            auditService.logAction("add premiumUser");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printPremiumUserById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("PremiumUser ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("PremiumUser ID: ");
            }
        }

        if(premiumUserCRUD.getPremiumUserById(id) != null)
            System.out.println(premiumUserCRUD.getPremiumUserById(id).toString());
        else
            System.out.println("This PremiumUser with this ID does not exist!");

        try
        {
            auditService.logAction("print premiumUser by id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updatePremiumUser()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("PremiumUser ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("PremiumUser ID: ");
            }
        }

        boolean ok = false;
        for(PremiumUser a: premiumUsers)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            System.out.print("New plan: ");
            String plan = scan.nextLine();

            System.out.print("New price: ");
            double price;

            while(true)
            {
                try
                {
                    price = Double.parseDouble(scan.nextLine());

                    boolean okk = true;
                    for(PremiumUser a: premiumUsers)
                    {
                        if(a.getPrice() == price)
                        {
                            okk = false;
                            break;
                        }
                    }

                    if(!okk)
                        throw new Exception();
                    else
                        break;
                }
                catch (Exception e)
                {
                    System.out.println("Introduce a double value!");
                    System.out.print("Price: ");
                }
            }

            premiumUserCRUD.updatePremiumUserPlan(plan, price, id);
        }
        else
            System.out.println("This PremiumUser with this ID does not exist!");

        try
        {
            auditService.logAction("update premiumUser");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deletePremiumUserById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("PremiumUser ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Introduce an integer value!");
                System.out.print("PremiumUser ID: ");
            }
        }

        boolean ok = false;
        for(PremiumUser a: premiumUsers)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            premiumUserCRUD.deletePremiumUserById(id);
            System.out.println("PremiumUser deleted succesfully!");
        }
        else
            System.out.println("This PremiumUser with this ID does not exist!");

        try
        {
            auditService.logAction("delete premiumUser");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printPremiumUsers()
    {
        premiumUserCRUD.showPremiumUsers();

        try
        {
            auditService.logAction("prints all premiumUsers");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void closeConnection()
    {
        DatabaseConfig.closeDatabaseConnection();

        try
        {
            auditService.logAction("closed connection with db");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

















