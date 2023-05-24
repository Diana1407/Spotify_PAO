import Entities.*;
import Services.*;

import Config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu m = Menu.getInstance();
        m.runMenu();
    }

}

//    public static void main(String[] args) throws ArtistService.ArtistNotFoundException, AlbumService.AlbumNotFoundException, SongService.SongNotFoundException {
//
//        //AppService appService = AppService.getInstance();
//        SongService songService = SongService.getInstance();
//        ArtistService artistService = ArtistService.getInstance();
//        AlbumService albumService = AlbumService.getInstance();
//        PlaylistService playlistService = PlaylistService.getInstance();
//        PremiumUserService premiumUserService = PremiumUserService.getInstance();
//        Artist artist = new Artist(5, "artist@mail.com", "artist", "artistpa55", 8);
//        artistService.addArtist(artist);
//        Album album = new Album(7, "title", 5, "realease", artist);
//        albumService.addAlbum(album);
//        Song song = new Song(2,"nume", artist, album, 10, "pop");
//        songService.addSongs(song);
//        Song song2 = new Song(2,"anume", artist, album, 10, "pop");
//        songService.addSongs(song2);
//        PremiumUser user = new PremiumUser(4, "email@mail.com", "dianasicristiana", "userpa55", "premium",5);
//        premiumUserService.addPremiumUser(user);
//        Playlist playlist = new Playlist(4,"Primul meu playlist", 13 , true, user, songService.getSongs() );
//        playlistService.addPlaylist(playlist);
//        ///artistService.printArtist(artist);
//
//        AppService appService = AppService.getInstance();
//        appService.Menu();
//
//
//        DatabaseConfig.closeDatabaseConnection();
//    }

//        Connection conn = null;
//        try {
//            // Step 1: Load the JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Step 2: Establish the connection
//            String url = "jdbc:mysql://localhost:3306/spotify";
//            String user = "root";
//            String password = "user";
//            conn = DriverManager.getConnection(url, user, password);
//            System.out.println("Connected successfully to the database!");
//        } catch (SQLException e) {
//            System.err.println("Failed to connect to the database.");
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            System.err.println("Could not find the JDBC driver class.");
//            e.printStackTrace();
//        } finally {
//            // Step 3: Close the connection
//            try {
//                if (conn != null) {
//                    conn.close();
//                    System.out.println("Connection closed successfully.");
//                }
//            } catch (SQLException e) {
//                System.err.println("Failed to close the database connection.");
//                e.printStackTrace();
//            }
//        }
//
//    }
// }