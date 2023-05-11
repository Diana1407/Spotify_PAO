package Services;

import Entities.*;

import java.util.Scanner;

public class AppService {
    private AlbumService albumService = AlbumService.getInstance();
    private ArtistService artistService = ArtistService.getInstance();
    private PlayerService playerService = PlayerService.getInstance();
    private PlaylistService playlistService = PlaylistService.getInstance();
    private PremiumUserService premiumUserService = PremiumUserService.getInstance();
    private SongService songService = SongService.getInstance();

    private static AppService instance;
    private Scanner scanner = new Scanner(System.in);
    private AppService(){}
    public static AppService getInstance()
    {
        if(instance == null)
        {
            instance = new AppService();
        }
        return instance;
    }

    public void printOptions1(){
        System.out.println(" 0 - Show All");
        System.out.println(" 1 - Show by Id");
        System.out.println(" 2 - Add");
        System.out.println(" 3 - Update");
        System.out.println(" 4 - Delete");
        System.out.println(" 5 - Exit");
    }

    public void printOptions2(){
        System.out.println(" 0 - Show All");
        System.out.println(" 1 - Show by Name");
        System.out.println(" 2 - Add");
        System.out.println(" 3 - Update");
        System.out.println(" 4 - Delete");
        System.out.println(" 5 - Exit");
    }
    public void printOptionsPlaylist(){
        System.out.println(" 0 - Show All");
        System.out.println(" 1 - Show by Id");
        System.out.println(" 2 - Add Playlist");
        System.out.println(" 3 - Update");
        System.out.println(" 4 - Delete");
        System.out.println(" 5 - Add Song to playlist");
        System.out.println(" 6 - Exit");
    }
    public void printMenu()
    {
        System.out.println("0 - User");
        System.out.println("1 - Artist");
        System.out.println("2 - Playlist");
        System.out.println("3 - Album");
        System.out.println("4 - Song");
        System.out.println("5 - Exit");
    }

    public void Menu() throws ArtistService.ArtistNotFoundException, AlbumService.AlbumNotFoundException, SongService.SongNotFoundException {
        while(true)
        {
            printMenu();
            int op;
            try{
                op = scanner.nextInt();
            }catch (Exception e)
            {
                System.out.println("Choose a number from 0 to 5!");
                op = scanner.nextInt();
            }
            if(op == 0)
                userMenu();
            if(op == 1)
                artistMenu();
            if(op == 2)
            playlistMenu();
            if(op == 3)
                albumMenu();
            if(op == 4)
                songMenu();
            if(op == 5)
                break;

        }
    }

    public void userMenu() {
        while(true) {
            printOptions1();
            int op;
            try {
                op = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Choose a number from 0 to 5");
                op = scanner.nextInt();
            }
            if (op == 0) {
                for (int i = 0; i < premiumUserService.getPremiumUsers().size(); i++) {
                    System.out.println(premiumUserService.getPremiumUsers().get(i).toString());
                }
            } else if (op == 1) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                System.out.println(premiumUserService.getPremiumUserById(i).toString());
            } else if (op == 2) {
                PremiumUser premiumUser = premiumUserService.readPremiumUser();
                premiumUserService.addPremiumUser(premiumUser);
            } else if (op == 3) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                PremiumUser premiumUser = premiumUserService.readPremiumUser();
                premiumUserService.updatePremiunUser(i, premiumUser);
            } else if (op == 4) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                PremiumUser premiumUser = premiumUserService.getPremiumUserById(i);
                premiumUserService.deletePremiumUser(premiumUser);
            } else if (op == 5) {
                break;
            }
        }
    }

    public void artistMenu() throws ArtistService.ArtistNotFoundException {
        while(true) {
            printOptions2();
            int op;
            try {
                op = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Choose a number from 0 to 5");
                op = scanner.nextInt();
            }
            if (op == 0) {
                for (int i = 0; i < artistService.getArtists().size(); i++) {
                    System.out.println(artistService.getArtists().get(i).toString());
                }
            } else if (op == 1) {
                String s;
                try {
                    s = scanner.next();
                    Artist rez = artistService.getArtistByName(s);
                } catch (ArtistService.ArtistNotFoundException e){
                    System.out.println("The artist you searched for does not exist!!");
                    s = scanner.next();
                }
                System.out.println(artistService.getArtistByName(s).toString());
            } else if (op == 2) {
                Artist artist = artistService.readArtist();
                artistService.addArtist(artist);
            }else if (op == 3) {
                String s;
                try {
                    s = scanner.next();
                    Artist rez = artistService.getArtistByName(s);
                } catch (ArtistService.ArtistNotFoundException e){
                    System.out.println("The artist you searched for does not exist!");
                    s = scanner.next();
                }
                Artist artist = artistService.getArtistByName(s);
                artistService.deleteArtist(artist);
            } else if (op == 4) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                Artist artist = artistService.readArtist();
                artistService.updateArtist(i, artist);
            } else if (op == 5) {
                break;
            }
        }
    }


    public void albumMenu() throws AlbumService.AlbumNotFoundException {
        while(true) {
            printOptions1();
            int op;
            try {
                op = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Choose a number from 0 to 5");
                op = scanner.nextInt();
            }
            if (op == 0) {
                for (int i = 0; i < albumService.getAlbums().size(); i++) {
                    System.out.println(albumService.getAlbums().get(i).toString());
                }
            } else if (op == 1) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                System.out.println(albumService.getAlbumById(i).toString());
            } else if (op == 2) {
                Album album = albumService.readAlbum();
                albumService.addAlbum(album);
            } else if (op == 3) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                Album album= albumService.readAlbum();
                albumService.updateAlbum(i, album);
            } else if (op == 4) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                Album album = albumService.getAlbumById(i);
                albumService.deleteAlbum(album);
            } else if (op == 5) {
                break;
            }
        }
    }

    public void songMenu() throws SongService.SongNotFoundException {
        while(true) {
            printOptions2();
            int op;
            try {
                op = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Choose a number from 0 to 5");
                op = scanner.nextInt();
            }
            if (op == 0) {
                for (int i = 0; i < songService.getSongs().size(); i++) {
                    System.out.println(songService.getSongs().get(i).toString());
                }
            } else if (op == 1) {
                String s;
                try {
                    s = scanner.next();
                    Song rez = songService.getSongByName(s);
                } catch (SongService.SongNotFoundException e){
                    System.out.println("The song you searched for does not exist!");
                    s = scanner.next();
                }
                System.out.println(songService.getSongByName(s).toString());
            } else if (op == 2) {
                Song song = songService.readSong();
                songService.addSongs(song);
            }else if (op == 3) {
                String s;
                try {
                    s = scanner.next();
                    Song rez = songService.getSongByName(s);
                } catch (SongService.SongNotFoundException e){
                    System.out.println("The song you searched for does not exist!");
                    s = scanner.next();
                }
                Song song = songService.getSongByName(s);
                songService.deleteSongByName(song);
            } else if (op == 4) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                Song song = songService.readSong();
                songService.updateSong(i, song);
            } else if (op == 5) {
                break;
            }
        }
    }

    public void playlistMenu() throws SongService.SongNotFoundException {
        while(true) {
            printOptionsPlaylist();
            int op;
            try {
                op = scanner.nextInt();
            }
            catch(Exception e){
                System.out.println("Choose a number from 0 to 5");
                op = scanner.nextInt();
            }
            if (op == 0) {
                for (int i = 0; i < playlistService.getPlaylists().size(); i++) {
                    System.out.println(playlistService.getPlaylists().get(i).toString());
                }
            } else if (op == 1) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                System.out.println(playlistService.getPlaylistById(i).toString());
            } else if (op == 2) {
                Playlist playlist = playlistService.readPlaylist();
                playlistService.addPlaylist(playlist);
            } else if (op == 3) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                Playlist playlist = playlistService.readPlaylist();
                playlistService.updatePlaylist(i, playlist);
            } else if (op == 4) {
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
                Playlist playlist = playlistService.getPlaylistById(i);
                playlistService.deletePlaylist(playlist);
            } else if (op == 5) {

                System.out.println("Provide playlist id to add song to");
                int i;
                try {
                    i = scanner.nextInt();
                } catch (Exception e){
                    System.out.println("Wrong id!");
                    i = scanner.nextInt();
                }
               /// Playlist playlist = playlistService.getPlaylistById(i);

                System.out.println("Provide the name of the song you want to add");
                String name = scanner.next();
                Song song = songService.getSongByName(name);

                playlistService.addSongToPlaylist(i, song);

            } else if (op==6) {
                break;
            }
            }
        }



    //meniu doar pt user fara drept de acces, gen nu face crud pe nimic, doar asc muzica

}