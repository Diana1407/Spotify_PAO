package Services;

import Entities.Artist;
import Entities.Song;

import java.util.*;

public class SongService {
    public List<Song> songs = new ArrayList<>();
    private static SongService instance;

    private SongService(){}

    public static class SongNotFoundException extends Exception {
        public SongNotFoundException(String message) {
            super(message);
        }
    }

    public static SongService getInstance()
    {
        if(instance == null)
        {
            instance = new SongService();
        }
        return instance;
    }

    public List<Song> getSongs()
    {
        List<Song> songs1 = new ArrayList<>();
        songs1.addAll(this.songs);
        Collections.sort(songs1, new SongOrd());
        return songs1;
    }

    public Song getSongByName(String name) throws SongService.SongNotFoundException {
        Song song = new Song();
        boolean ok = false;
        for(int j = 0; j < this.songs.size(); j++)
        {
            if(this.songs.get(j).getName() == name)
            {
                song = this.songs.get(j);
                ok = true;
            }
        }
        if(ok == true)
            return song;
        else
            throw new SongService.SongNotFoundException("Artist not found with this name");
    }

    public void updateSong(int i, Song song)
    {
        for(int j = 0; j < this.songs.size(); j++)
        {
            if(this.songs.get(i).getId() == i)
            {
                this.songs.remove(j);
                this.songs.add(i, song);
                break;
            }
        }
    }

    public void addSongs(Song song)
    {
        this.songs.add(song);
    }

    public void deleteSongByName(Song song)
    {
        for(int i = 0; i < this.songs.size(); i++)
        {
            if(this.songs.get(i).equals(song))
            {
                this.songs.remove(song);
                break;
            }
        }
    }

    public Song readSong()
    {
        Scanner scanner = new Scanner(System.in);
        ArtistService artistService = ArtistService.getInstance();
        Song song = new Song();
        System.out.println("Id");
        try
        {
            song.setId(scanner.nextInt());
        }catch (Exception e)
        {
            System.out.println("Provide int!");
            song.setId(scanner.nextInt());
        }

        System.out.println("Artist: ");
        Artist artist = artistService.readArtist();
        song.setArtist(artist);

        System.out.println("Duration in seconds: ");
        try
        {
            song.setDuration(scanner.nextInt());
        }catch (Exception e)
        {
            System.out.println("Provide int!");
            song.setDuration(scanner.nextInt());
        }

        System.out.println("Genre: ");
        song.setGenre(scanner.next());

        return song;
    }

}
