package Entities;

import CRUD.AlbumCRUD;
import CRUD.ArtistCRUD;

public class Song {
    private int id;
    private String name;
    private int artistId;
    private int albumId;
    private int duration;
    private String link;

    public Song() {
    }

    public Song(int id, String name, int artistId, int albumId, int duration, String link) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.albumId = albumId;
        this.duration = duration;
        this.link = link;
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

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        String result = "Song id: " + id + "\n";
        result += "Name: " + name + "\n";
        Artist artist = ArtistCRUD.getInstance().getArtistById(artistId);
        result += "Artist: " + artist.getUsername() + "\n";
        Album album = AlbumCRUD.getInstance().getAlbumById(albumId);
        result += "Album: " + album.getTitle() + "\n";
        result += "Duration: " + duration + "\n";
        ///result += "link: " + link + "\n"; sa dam play de aici
        result += "\n---------------------------------------------------------------------------------------------------------------------------\n";
        return result;
    }

}
