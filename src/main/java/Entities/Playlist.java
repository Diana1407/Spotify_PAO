package Entities;

import java.util.List;

public class Playlist extends TrackList {
    private boolean privacy;
    private User owner;
    private List<Song> songs;

    public Playlist(){}

    public Playlist(int id, String title, int duration, boolean privacy, User owner, List<Song> songs)
    {
        super(id, title, duration);
        this.privacy = privacy;
        this.owner = owner;
        this.songs = songs;
    }

    public boolean getPrivacy(){
        return privacy;
    }

    public void setPrivacy(boolean privacy)
    {
        this.privacy = privacy;
    }

    public User getOwner(){
        return owner;
    }
    public void setOwner(User owner){
        this.owner = owner;
    }

    public List<Song> getSongs(){
        return songs;
    }
    public void setSongs(List<Song> songs){
        this.songs = songs;
    }

    @Override
    public String toString() {
        String privacyname;
        if(privacy==true)
            privacyname="private";
        else privacyname="public";


        String result = "Playlist name: " + this.title + '\n' + "Duration: " + this.duration + '\n' +
                "Owner UserName: " + this.owner.getUsername() + ". This playlist is: " + privacyname + '\n' + "The tracklist is: " + '\n';

        for(int i=0;i<songs.size();i++)
        {
            result += songs.get(i).toString();
        }


        return result;


    }
}
