package Entities;

import java.util.List;

public abstract class TrackList {
    protected int id;
    protected String title;
    protected int duration;

    public TrackList(){}

    public TrackList(TrackList ob)
    {
        this.id = ob.id;
        this.title = ob.title;
        this.duration = ob.duration;
    }

    public TrackList(int id, String title, int duration)
    {
        this.id = id;
        this.title=title;
        this.duration = duration;
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

    @Override
    public abstract String toString();
}
