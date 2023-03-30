package Entities;

import java.util.List;

public class Playlist extends TrackList {
    private boolean privacy;
    private User owner;

    public Playlist(){}

    public Playlist(int id, String title, int duration, List<Song> songs, boolean privacy, User owner)
    {
        super(id, title, duration, songs);
        this.privacy = privacy;
        this.owner = owner;
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
