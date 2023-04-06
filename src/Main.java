import Entities.*;
import Services.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ArtistService.ArtistNotFoundException, AlbumService.AlbumNotFoundException, SongService.SongNotFoundException {

        //AppService appService = AppService.getInstance();
        SongService songService = SongService.getInstance();
        ArtistService artistService = ArtistService.getInstance();
        AlbumService albumService = AlbumService.getInstance();
        PlaylistService playlistService = PlaylistService.getInstance();
        PremiumUserService premiumUserService = PremiumUserService.getInstance();
        Artist artist = new Artist(5, "artist@mail.com", "artist", "artistpa55", 8);
        artistService.addArtist(artist);
        Album album = new Album(7, "title", 5, songService.getSongs(), "realease", artist);
        albumService.addAlbum(album);
        Song song = new Song(2,"nume", artist, album, 10, "pop");
        songService.addSongs(song);
        Song song2 = new Song(2,"anume", artist, album, 10, "pop");
        songService.addSongs(song2);
        PremiumUser user = new PremiumUser(4, "email@mail.com", "dianasicristiana", "userpa55", "premium",5);
        premiumUserService.addPremiumUser(user);
        Playlist playlist = new Playlist(4,"Primul meu playlist", 13 , songService.getSongs(),  true, user );
        playlistService.addPlaylist(playlist);
        ///artistService.printArtist(artist);

        AppService appService = AppService.getInstance();
        appService.Menu();
    }

}