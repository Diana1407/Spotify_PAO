package Entities;

public class Song {
    private int id;
    private String name;
    private Artist artist;
    private Album album;
    private int duration;
    private String genre;

    public Song() {
    }

    public Song(int id, String name, Artist artist, Album album, int duration, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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
        result += "Name: c" + name + "\n";
        result += "Artist: " + artist.getUsername() + "\n";
        result += "Album: " + album.getTitle() + "\n";
        result += "Duration: " + duration + "\n";
        result += "Genre: " + genre + "\n";
        result += "\n---------------------------------------------------------------------------------------------------------------------------\n";
        return result;
    }

}
