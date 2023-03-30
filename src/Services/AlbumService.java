package Services;

import Entities.Album;
import Entities.Song;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlbumService {
    private List<Album> albums = new ArrayList<>();
    private static AlbumService instance;

    private AlbumService(){}

    public static AlbumService getInstance(){
        if(instance == null)
        {
            instance = new AlbumService();
        }
        return instance;
    }
    public static class AlbumNotFoundException extends Exception {
        public AlbumNotFoundException(String message) {
            super(message);
        }
    }

    public List<Album> getAlbums(){
        List<Album> albums1 = new ArrayList<>();
        albums1.addAll(this.albums);
        return albums1;
    }

    public Album getAlbumById(int id) throws AlbumNotFoundException
    {
        Album album = new Album();
        for(int i=0; i< this.albums.size(); i++)
        {
            if(this.albums.get(i).getId() == id)
            {
                album = this.albums.get(i);
            }
        }
        if(album == null)
            throw new AlbumNotFoundException("Album not found with ID:" + id);
        return album;
    }

    public void updateAlbum(int id, Album album)
    {
        for(int i =0; i<this.albums.size(); i++)
            if(this.albums.get(i).getId() == id)
            {
               this.albums.remove(i);
               this.albums.add(id, album);
               break;
            }
    }

    public void addAlbum(Album album)
    {
        this.albums.add(album);
    }

    public void deleteAlbumsById(int id)
    {
        for(int i=0; i< this.albums.size(); i++){
            if(this.albums.get(i).getId() == id)
            {
                this.albums.remove(i);
                break;
            }
        }
    }
    public void deleteAlbum (Album album){
        for(int i=0; i<this.albums.size(); i++)
        {
            if(this.albums.get(i).equals(album)){
                this.albums.remove(i);
                break;
            }
        }
    }
    public Album readAlbum() throws ParseException{
        Scanner scanner = new Scanner(System.in);
        Album album = new Album();
        System.out.println("Id");
        try{
            album.setID(scanner.nextInt());
        } catch (Exception e){
            System.out.println("Provide int:");
            album.setID(scanner.nextInt());
        }

        System.out.println("Title");
        album.setTitle(scanner.next());

        System.out.println("Duration");
        album.setDuration(Integer.parseInt(scanner.next()));

        System.out.println("Number of songs");
        int nr;
        nr = scanner.nextInt();
        System.out.println("Tracklist");
        List<Song> arr = new ArrayList<>();
        SongService songService = SongService.getInstance();

        for(int i=0; i<nr ; i++)
        {
            Song song = songService.readSong();
            arr.add(song);
        }
        album.setSongs(arr);

        return album;


    }

}
