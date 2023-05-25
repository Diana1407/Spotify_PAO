package Entities;

import CRUD.PremiumUserCRUD;

import java.util.List;
import java.util.Objects;

public class Playlist extends TrackList {
    private String privacy;
    private int ownerId;
    private String songs;

    public Playlist(){}

    public Playlist(int id, String title, int duration, String privacy, int ownerId, String songs)
    {
        super(id, title, duration);
        this.privacy = privacy;
        this.ownerId = ownerId;
        this.songs = songs;
    }

    public String getPrivacy(){
        return privacy;
    }

    public void setPrivacy(String privacy)
    {
        this.privacy = privacy;
    }

    public int getOwnerId(){
        return ownerId;
    }
    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    public String getSongs(){
        return songs;
    }
    public void setSongs(String songs){
        this.songs = songs;
    }

    @Override
    public String toString() {
        PremiumUser user = PremiumUserCRUD.getInstance().getPremiumUserById(ownerId);
        StringBuilder result = new StringBuilder("Playlist name: " + this.title + '\n' + "Duration: " + this.duration + '\n'
                +"Owner UserName: " + user.getUsername() + '\n' + "This playlist is: " + this.privacy + '\n' + "The tracklist is: ");

        result.append(Objects.requireNonNullElse(this.songs, "empty"));

        return result.toString();

    }
}
