package Services;

import Entities.Artist;
import Entities.PremiumUser;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteCSV {
    public static void writeArtist(int id, String email, String username, String password, int followers)
    {
        String path = "src\\main\\java\\Files\\Artist.csv";
        try
        {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            String artistString = id + "," + email + "," + username + "," + password + "," + followers;
            printWriter.println(artistString);

            printWriter.flush();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public static List<Artist> readArtist()
    {
        List<Artist> artists = new ArrayList<>();
        String path = "src\\main\\java\\Files\\Artist.csv";

        BufferedReader bufferedReader;
        String line;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null)
            {
                String[] row = line.split(",");
                int id = Integer.parseInt(row[0]);
                String email = row[1];
                String username = row[2];
                String password = row[3];
                int followers = Integer.parseInt(row[4]);

                Artist artist = new Artist(id, email, username, password, followers);
                artists.add(artist);
            }
            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return artists;
    }

    public static void writePremiumUser(int id, String email, String username, String password, String plan, double price)
    {
        String path = "src\\main\\java\\Files\\PremiumUser.csv";
        try
        {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            String premiumUserString = id + "," + email + "," + username + "," + password + "," + plan + "," + price;
            printWriter.println(premiumUserString);

            printWriter.flush();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
    }

    public static List<PremiumUser> readPremiumUser()
    {
        List<PremiumUser> premiumUsers = new ArrayList<>();
        String path = "src\\main\\java\\Files\\PremiumUser.csv";

        BufferedReader bufferedReader;
        String line;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null)
            {
                String[] row = line.split(",");
                int id = Integer.parseInt(row[0]);
                String email = row[1];
                String username = row[2];
                String password = row[3];
                String plan = row[4];
                double price = Double.parseDouble(row[5]);

                PremiumUser premiumUser = new PremiumUser(id, email, username, password, plan, price);
                premiumUsers.add(premiumUser);
            }
            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return premiumUsers;
    }
}
