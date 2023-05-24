package Entities;

import java.util.List;

public class Playlist extends TrackList {
    private boolean privacy;
    private int ownerId;
    private List<Song> songs;

    public Playlist(){}

    public Playlist(int id, String title, int duration, boolean privacy, int ownerId, List<Song> songs)
    {
        super(id, title, duration);
        this.privacy = privacy;
        this.ownerId = ownerId;
        this.songs = songs;
    }

    public boolean getPrivacy(){
        return privacy;
    }

    public void setPrivacy(boolean privacy)
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
        String privacyname;
        if(privacy)
            privacyname="private";
        else privacyname="public";


        StringBuilder result = new StringBuilder("Playlist name: " + this.title + '\n' + "Duration: " + this.duration + '\n');
        // +"Owner UserName: " + this.owner.getUsername() + ". This playlist is: " + privacyname + '\n' + "The tracklist is: " + '\n';

        for (Song song : songs) {
            result.append(song.toString());
        }

        return result.toString();


    }
}
