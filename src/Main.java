import Entities.*;
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
//        PremiumUser user = new PremiumUser(4, "email@mail.com", "dianasicristiana", "userpa55", "premium",5);
//        Playlist playlist = new Playlist(4,"Primul meu playlist", 13 , s,  true, user );

        AppService appService = AppService.getInstance();
        appService.Menu();
    }

}