package Services;

import CRUD.AlbumCRUD;
import CRUD.ArtistCRUD;
import CRUD.PlaylistCRUD;
import CRUD.PremiumUserCRUD;
import Config.DatabaseConfig;
import Entities.*;

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
    public static List<Album> albums = new ArrayList<>();
    public static List<Playlist> playlists = new ArrayList<>();

    AuditService auditService = AuditService.getInstance();

    ArtistCRUD artistCRUD = ArtistCRUD.getInstance();
    PremiumUserCRUD premiumUserCRUD = PremiumUserCRUD.getInstance();
    AlbumCRUD albumCRUD = AlbumCRUD.getInstance();
    PlaylistCRUD playlistCRUD = PlaylistCRUD.getInstance();

    public void loadData()
    {
        artists = ReadWriteCSV.readArtist();
        premiumUsers = ReadWriteCSV.readPremiumUser();
        albums = ReadWriteCSV.readAlbum();
        playlists = ReadWriteCSV.readPlaylist();

        artistCRUD.add();
        premiumUserCRUD.add();
        albumCRUD.add();
        playlistCRUD.add();

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
        albumCRUD.createTable();
        playlistCRUD.createTable();

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
            auditService.logAction("add Artist");
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
            auditService.logAction("print Artist by id");
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
            auditService.logAction("update Artist");
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
            auditService.logAction("delete Artist");
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
            auditService.logAction("prints all Artists");
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
            auditService.logAction("add PremiumUser");
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
            auditService.logAction("print PremiumUser by id");
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
            auditService.logAction("update PremiumUser");
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
            auditService.logAction("delete PremiumUser");
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
            auditService.logAction("prints all PremiumUsers");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

//    public void closeConnection()
//    {
//        DatabaseConfig.closeDatabaseConnection();
//
//        try
//        {
//            auditService.logAction("closed connection with db");
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public void addAlbum()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Album ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Album a: albums)
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
                System.out.print("Album ID: ");
            }

            catch (Exception e)
            {
                System.out.println("This Album ID already exists!");
                System.out.print("Album ID: ");
            }
        }

        System.out.print("Title: ");
        String title = scan.nextLine();

        System.out.print("Duration: ");
        int duration;
        while(true)
        {
            try
            {
                duration = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Album a: albums)
                {
                    if(a.getDuration() == duration)
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
                System.out.print("Duration: ");
            }
        }

        System.out.print("RealeaseDate: ");
        String releaseDate = scan.nextLine();


        System.out.print("Artist ID: ");
        int artistId;
        while(true)
        {
            try
            {
                artistId = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Album a: albums)
                {
                    if(a.getArtistId() == artistId)
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
                System.out.print("Artist ID: ");
            }
        }

        albums.add(new Album(id, title, duration, releaseDate, artistId));
        ReadWriteCSV.writeAlbum(id, title, duration, releaseDate, artistId);
        albumCRUD.addAlbum(id, title, duration, releaseDate, artistId);

        try
        {
            auditService.logAction("add Album");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printAlbumById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Album ID: ");
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

        if(albumCRUD.getAlbumById(id) != null)
            System.out.println(albumCRUD.getAlbumById(id).toString());
        else
            System.out.println("This Album with this ID does not exist!");

        try
        {
            auditService.logAction("print Album by id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteAlbumById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Album ID: ");
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
                System.out.print("Album ID: ");
            }
        }

        boolean ok = false;
        for(Album a: albums)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            albumCRUD.deleteAlbumById(id);
            System.out.println("Album deleted succesfully!");
        }
        else
            System.out.println("This Album with this ID does not exist!");

        try
        {
            auditService.logAction("delete Album");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printAlbums()
    {
        albumCRUD.showAlbums();

        try
        {
            auditService.logAction("prints all Albums");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addPlaylist()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Playlist ID: ");
        int id;

        while(true)
        {
            try
            {
                id = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Playlist a: playlists)
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
                System.out.print("Playlist ID: ");
            }

            catch (Exception e)
            {
                System.out.println("This Playlist ID already exists!");
                System.out.print("Playlist ID: ");
            }
        }

        System.out.print("Title: ");
        String title = scan.nextLine();

        System.out.print("Duration: ");
        int duration;
        while(true)
        {
            try
            {
                duration = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Album a: albums)
                {
                    if(a.getDuration() == duration)
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
                System.out.print("Duration: ");
            }
        }

        System.out.print("Privacy: ");
        boolean privacy = scan.nextBoolean();

        System.out.print("User ID: ");
        int ownerId;
        while(true)
        {
            try
            {
                ownerId = Integer.parseInt(scan.nextLine());

                boolean ok = true;
                for(Playlist a: playlists)
                {
                    if(a.getOwnerId() == ownerId)
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
                System.out.print("Owner ID: ");
            }
        }

        playlists.add(new Playlist(id, title, duration, privacy, ownerId, null));
        ReadWriteCSV.writePlaylist(id, title, duration, privacy, ownerId);
        playlistCRUD.addPlaylist(id, title, duration, privacy, ownerId);

        try
        {
            auditService.logAction("add Playlist");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void printPlaylistById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Playlist ID: ");
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
                System.out.print("Playlist ID: ");
            }
        }

        if(playlistCRUD.getPlaylistById(id) != null)
            System.out.println(playlistCRUD.getPlaylistById(id).toString());
        else
            System.out.println("This Playlist with this ID does not exist!");

        try
        {
            auditService.logAction("print Playlist by id");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updatePlaylist()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Playlist ID: ");
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
                System.out.print("Playlist ID: ");
            }
        }

        boolean ok = false;
        for(Playlist a: playlists)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            System.out.print("New privacy: ");
            boolean privacy;

            while(true)
            {
                try
                {
                    privacy = scan.nextBoolean();

                    boolean okk = true;
                    for(Playlist a: playlists)
                    {
                        if(a.getPrivacy() == privacy)
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
                    System.out.println("Introduce an boolean value!");
                    System.out.print("Privacy: ");
                }
            }

            playlistCRUD.updatePlaylistPrivacy(privacy, id);
        }
        else
            System.out.println("This Playlist with this ID does not exist!");

        try
        {
            auditService.logAction("update playlist");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void deletePlaylistById()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Playlist ID: ");
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
                System.out.print("Playlist ID: ");
            }
        }

        boolean ok = false;
        for(Playlist a: playlists)
        {
            if(id == a.getId())
            {
                ok = true;
                break;
            }
        }

        if(ok)
        {
            playlistCRUD.deletePlaylistById(id);
            System.out.println("Playlist deleted succesfully!");
        }
        else
            System.out.println("This Playlist with this ID does not exist!");

        try
        {
            auditService.logAction("delete Playlist");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void printPlaylists()
    {
        playlistCRUD.showPlaylists();

        try
        {
            auditService.logAction("prints all Playlists");
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

















