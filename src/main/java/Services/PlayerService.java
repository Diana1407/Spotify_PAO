package Services;

import Entities.Player;
import Entities.Song;

import java.util.Queue;

public class PlayerService {
    private Player player;

    private static PlayerService instance;

    private PlayerService(){}

    public static PlayerService getInstance()
    {
        if(instance == null)
        {
            instance = new PlayerService();
        }
        return instance;
    }

    public void play(Song song) {
        this.player.setCurrentSong(song);
        this.player.setState(true);
        System.out.println("Now playing: " + song.getName());
    }

    public void pause() {
        this.player.setState(false);
        System.out.println("Pausing playback");
    }

    public void quit() {
        System.out.println("Stopping playback");
        this.player.setState(false);
        this.player.setCurrentSong(null);
        Queue<Song> aux = this.player.getQueue();
        aux.clear();
        this.player.setQueue(aux);
    }

    public void addToQueue(Song song) {
        System.out.println("Adding " + song.getName() + " to the queue");
        Queue<Song> aux = this.player.getQueue();
        aux.offer(song);
        this.player.setQueue(aux);
    }

    public void next() {
        if (this.player.getQueue().isEmpty()) {
            System.out.println("End of queue");
            this.player.setCurrentSong(null);
        }
        Queue<Song> q = this.player.getQueue();
        Song nextSong = q.poll();
        System.out.println("Playing next song: " + nextSong.getName());
        play(nextSong);
    }
}
