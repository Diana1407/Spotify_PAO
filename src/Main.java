import Entities.Album;
import Entities.Artist;
import Entities.Song;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Artist artist = new Artist(5, "pula", "pula2", "pula4", 8);
        List<Song> s = new ArrayList<>();
        Album album = new Album(7, "string", 5, s, "serdas", artist);
        Song song = new Song(2,"nume", artist, album, 10, "pula");

        System.out.println(song.toString());
    }

}