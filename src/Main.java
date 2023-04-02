import Entities.Album;
import Entities.Artist;
import Entities.Song;
import Services.AlbumService;
import Services.AppService;
import Services.ArtistService;
import Services.SongService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ArtistService.ArtistNotFoundException, AlbumService.AlbumNotFoundException, SongService.SongNotFoundException {

//        Artist artist = new Artist(5, "artist@mail.com", "artist", "artistpa55", 8);
//        List<Song> s = new ArrayList<>();
//        Album album = new Album(7, "title", 5, s, "realease", artist);
//        Song song = new Song(2,"nume", artist, album, 10, "pop");
//
//        System.out.println(song);

        AppService appService = AppService.getInstance();
        appService.Menu();
    }

}