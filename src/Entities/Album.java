package Entities;

import java.util.List;

public class Album extends TrackList {
    private String releasedate;
    private Artist artist;

    public Album() {
    }

    public Album(int id, String title, int duration, List<Song> songs, String ReleaseDate, Artist artist) {
        super(id, title, duration, songs);
        this.releasedate = ReleaseDate;
        this.artist = artist;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleaseDate(String releasedate) {
        this.releasedate = releasedate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist() {
        this.artist = artist;
    }

    @Override
    public String toString() {
        String result = "Album name: " + this.title + '\n' + "Duration: " +
                this.duration + '\n' + "Release Date: " + this.releasedate + '\n' + "Artist Name: " + this.artist.getUsername() + '\n' + "The tracklist is: \n";
        for (int i = 0; i < songs.size(); i++) {
            result += songs.get(i).toString();
        }
        return result;
    }
}
