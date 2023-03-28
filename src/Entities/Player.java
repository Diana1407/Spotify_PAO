package Entities;

import java.util.Queue;

public class Player {
    private int id;
    private boolean state;
    private Song currentSong;
    private Queue<Song> queue;
    //il vom face priority queue???

    public Player(){}

    public Player(int id, boolean state, Song currentSong, Queue<Song> queue)
    {
        this.id = id;
        this.state = state;
        this.currentSong = currentSong;
        this.queue = queue;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean getState()
    {
        return  state;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }

    public Song getCurrentSong()
    {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong)
    {
        this.currentSong = currentSong;
    }

    public Queue<Song> getQueue()
    {
        return queue;
    }

    public void setQueue(Queue<Song> queue)
    {
        this.queue = queue;
    }

    @Override
    public String toString(){
        String result = "Player id: " + id + "\n";
        result += "State: " + state + "\n";
        result += "Current Song: " + currentSong + "\n";
        if(queue != null) {
            for (Song song : queue) {
                result += song.toString();
            }
        }
        result += "\n---------------------------------------------------------------------------------------------------------------------------\n";
        return result;
    }



}
