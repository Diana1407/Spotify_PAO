package Entities;

import CRUD.PremiumUserCRUD;

import java.util.List;

public class Playlist extends TrackList {
    private String privacy;
    private int ownerId;
    private List<Song> songs;

    public Playlist(){}

    public Playlist(int id, String title, int duration, String privacy, int ownerId, List<Song> songs)
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

    public List<Song> getSongs(){
        return songs;
    }
    public void setSongs(List<Song> songs){
        this.songs = songs;
    }

    @Override
    public String toString() {
        PremiumUser user = PremiumUserCRUD.getInstance().getPremiumUserById(ownerId);
        StringBuilder result = new StringBuilder("Playlist name: " + this.title + '\n' + "Duration: " + this.duration + '\n'
         +"Owner UserName: " + user.getUsername() + '\n' + "This playlist is: " + this.privacy + '\n' + "The tracklist is: " + '\n');

        if(this.songs != null)
        {
            for (Song song : this.songs) {
                result.append(song.toString());
            }
        }
        else
            result.append("empty");

        return result.toString();


    }
}
