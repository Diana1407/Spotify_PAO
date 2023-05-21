package Services;

import Entities.Artist;
import Entities.PremiumUser;

import java.util.*;

public class ArtistService {
    private List<Artist> artists = new ArrayList<>();
    private static ArtistService instance;

    private ArtistService(){}

    public static ArtistService getInstance()
    {
        if(instance == null)
        {
            instance = new ArtistService();
        }
        return instance;
    }

    public static class ArtistNotFoundException extends Exception {
        public ArtistNotFoundException(String message) {
            super(message);
        }
    }

    public List<Artist> getArtists()
    {
        List<Artist> artists1 = new ArrayList<>();
        artists1.addAll(this.artists);
        return artists1;
    }

    public Artist getArtistByName(String name) throws ArtistNotFoundException {
        Artist artist = new Artist();
        boolean ok = false;
        for(int j = 0; j < this.artists.size(); j++)
        {
            if(this.artists.get(j).getUsername().equals(name))
            {
                artist = this.artists.get(j);
                ok = true;
            }
        }
        if(ok == true)
            return artist;
        else
            throw new ArtistNotFoundException("Artist not found with this name");
    }

    public Artist getArtistById(int i)
    {
        Artist artist = new Artist();
        for(int j = 0; j < this.artists.size(); j++)
        {
            if(this.artists.get(j).getId() == i)
            {
                artist = this.artists.get(j);
            }
        }
        return artist;
    }

    public void printArtist(Artist artist)
    {
        System.out.println(artist.toString());
    }

    public void updateArtist(int i, Artist artist)
    {
        for(int j = 0; j < artists.size(); j++)
        {
            if(this.artists.get(j).getId() == i)
            {
                this.artists.remove(j);
                this.artists.add(i, artist);
                break;
            }
        }
    }

    public void addArtist(Artist artist)
    {
        this.artists.add(artist);
    }

    public void deleteArtist(Artist artist)
    {
        for(int i = 0; i < this.artists.size(); i++)
        {
            if(this.artists.get(i).equals(artist))
            {
                this.artists.remove(artist);
                break;
            }
        }
    }

    public Artist readArtist()
    {
        Scanner scanner = new Scanner(System.in);
        Artist artist = new Artist();
        System.out.println("Id:");
        try
        {
            artist.setId(scanner.nextInt());
        }catch (Exception e)
        {
            System.out.println("Provide int!");
            artist.setId(scanner.nextInt());
        }

        System.out.println("Email: ");
        artist.setEmail(scanner.next());

        System.out.println("Username: ");
        artist.setUsername(scanner.next());

        System.out.println("Password: ");
        artist.setPassword(scanner.next());

        System.out.println("Followers: ");
        try{
            artist.setFollowers(scanner.nextInt());
        }catch (Exception e)
        {
            System.out.println("Provide int!");
            artist.setFollowers(scanner.nextInt());
        }

        return artist;

    }
}
