package Entities;

import java.util.ArrayList;
import java.util.List;

public class App {
    private int id;
    private List<Song> songs;
    private List<Album> albums;
    private List<Artist> artists;

    public App(){}

    public App(int id, List<Song> songs, List<Album> albums, List<Artist> artists)
    {
        this.id = id;
        this.songs = songs;
        this.albums = albums;
        this.artists = artists;
    }

    public int getId()
    {
        return id;
    }

    public void setid(int id)
    {
        this.id = id;
    }

    public List<Song> getSongs()
    {
        return songs;
    }

    public void setSongs(ArrayList songs)
    {
        this.songs = songs;
    }

    public List<Album> getAlbums()
    {
        return albums;
    }

    public void setAlbums(ArrayList albums)
    {
        this.albums = albums;
    }

    public List<Artist> getArtists()
    {
        return artists;
    }

    public void setArtists(ArrayList artists)
    {
        this.artists = artists;
    }

    @Override
    public String toString(){
        String result = "App id: " + id + "\n";
        if(songs != null) {
            for (Song song : songs) {
                result += song.toString();
            }
        }

        if(albums != null) {
            for (Album album : albums) {
                result += album.toString();
            }
        }

        if(artists != null) {
            for (Artist artist : artists) {
                result += artists.toString();
            }
        }
        result += "\n---------------------------------------------------------------------------------------------------------------------------\n";
        return result;
    }

}
