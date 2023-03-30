package Entities;

public class Song {
    private int id;
    private String name;
    private Artist artist;
    private int duration;
    private String genre;

    public Song() {
    }

    public Song(int id, String name, Artist artist, Album album, int duration, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        String result = "Song id: " + id + "\n";
        result += "Name: " + name + "\n";
        result += "Artist: " + artist.getUsername() + "\n";
        result += "Duration: " + duration + "\n";
        result += "Genre: " + genre + "\n";
        result += "\n---------------------------------------------------------------------------------------------------------------------------\n";
        return result;
    }

}
