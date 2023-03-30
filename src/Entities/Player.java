package Entities;

import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private boolean state;
    private Song currentSong;
    private Queue<Song> queue;
    //il vom face priority queue???

    public Player(){
        this.queue = new LinkedList<>();
    }

    public Player(boolean state, Song currentSong, Queue<Song> queue)
    {
        this.state = state;
        this.currentSong = currentSong;
        this.queue = queue;
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
        String result ="State: " + state + "\n";
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
