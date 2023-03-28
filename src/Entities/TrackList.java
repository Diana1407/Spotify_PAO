package Entities;

import java.util.List;

public abstract class TrackList {
    protected int id;
    protected String title;
    protected int duration;
    protected List<Song> songs;

    public TrackList(){}

    public TrackList(TrackList ob)
    {
        this.id = ob.id;
        this.title = ob.title;
        this.duration = ob.duration;
        this.songs = ob.songs;
    }

    public TrackList(int id, String title, int duration, List<Song> songs)
    {
        this.id = id;
        this.title=title;
        this.duration = duration;
        this.songs = songs;
    }


    public int getId(){
        return id;
    }
    public void setID(int id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Song> getSongs(){
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public abstract String toString();
}
