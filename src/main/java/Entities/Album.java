package Entities;

import java.util.List;

public class Album extends TrackList {
    private String releasedate;
    private int artistId;

    public Album() {
    }

    public Album(int id, String title, int duration, String ReleaseDate, int artistId) {
        super(id, title, duration);
        this.releasedate = ReleaseDate;
        this.artistId = artistId;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleaseDate(String releasedate) {
        this.releasedate = releasedate;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId() {
        this.artistId = artistId;
    }

    @Override
    public String toString() {
        String result = "Album name: " + this.title + '\n' + "Duration: " +
                this.duration + '\n' + "Release Date: " + this.releasedate + '\n' + "Artist Name: " ;//+ this.artist.getUsername() + '\n';

        return result;
    }
}
