package Services;

import Entities.Playlist;
import Entities.PremiumUser;
import Entities.Song;
import Entities.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistService {
    private List<Playlist> playlists = new ArrayList<>();
    private static PlaylistService instance;

    private PlaylistService() {
    }

    public static PlaylistService getInstance() {
        if (instance == null) {
            instance = new PlaylistService();
        }
        return instance;
    }

    public List<Playlist> getPlaylists() {
        List<Playlist> playlists1 = new ArrayList<>();
        playlists1.addAll(this.playlists);
        return playlists1;
    }

    public Playlist getPlaylistById(int id) {
        Playlist playlist = new Playlist();
        for (int i = 0; i < this.playlists.size(); i++) {
            if (this.playlists.get(i).getId() == id) {
                playlist = this.playlists.get(i);
            }
        }
        return playlist;
    }

    public void updatePlaylist(int id, Playlist playlist)
    {
        for(int i=0; i<this.playlists.size(); i++)
        {
            if(this.playlists.get(i).getId() == id)
            {
                this.playlists.remove(i);
                this.playlists.add(id,playlist);
                break;
            }
        }
    }

    public void addSongToPlaylist(Playlist playlist, Song song)
    {
        List<Song> aux= playlist.getSongs();
        aux.add(song);
        playlist.setSongs(aux);
    }

    public void addPlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    public void deletePlaylistById(int id){
        for(int i=0;i<this.playlists.size(); i++)
        {
            if(this.playlists.get(i).getId() == id)
            {
                this.playlists.remove(i);
                break;
            }
        }
    }
    public void deletePlaylist (Playlist playlist){
        for(int i=0; i<this.playlists.size();i++)
        {
            if(this.playlists.get(i).equals(playlist))
            {
                this.playlists.remove(i);
                break;
            }
        }
    }
    public Playlist readPlaylist(){
        Scanner scanner = new Scanner(System.in);
        ///SongService songservice = SongService.getInstance();
       Playlist playlist = new Playlist();
       System.out.println("Id");

       try{
           playlist.setID(scanner.nextInt());
       } catch (Exception e){
           System.out.println("Provide id");
           playlist.setID(scanner.nextInt());
       }

        System.out.println("Title");
        playlist.setTitle(scanner.next());

        System.out.println("Duration");
        playlist.setDuration(Integer.parseInt(scanner.next()));

        System.out.println("Number of songs ");
        int nr;
        nr = scanner.nextInt();
        System.out.println("Tracklist ");
        SongService songService = SongService.getInstance();
        List<Song> arr = new ArrayList<>();
        for(int i=0; i<nr ; i++)
        {

            Song song = songService.readSong();
            arr.add(song);
        }
        playlist.setSongs(arr);

        System.out.println("Privacy ");
        String priv = scanner.next();
        if(priv=="private" || priv == "Private")
            playlist.setPrivacy(true);
        else playlist.setPrivacy(false);

        System.out.println("Owner");
        User owner = PremiumUserService.getInstance().readPremiumUser();
        playlist.setOwner(owner);

        return playlist;

    }
}
