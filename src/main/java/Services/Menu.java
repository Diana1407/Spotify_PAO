package Services;


import javax.swing.*;
import java.util.Scanner;

public class Menu {
    private static Menu instance = null;
    private static final MainService service = new MainService();

    private Menu()
    {
        service.configureTables();
        service.loadData();
    }

    public static Menu getInstance()
    {
        if(instance == null)
            instance = new Menu();

        return instance;
    }

    private void showMenu1()
    {
        System.out.println("-----------------------------------");
        System.out.println("Choose an action:");
        System.out.println("1: Add new artist");
        System.out.println("2: Print all artists");
        System.out.println("3: Print artists sorted alphabetically");
        System.out.println("4: Print artists by a given id");
        System.out.println("5: Update artist followers by a given id");
        System.out.println("6: Delete artist by a given id");
        System.out.println("-----------------------------------");
        System.out.println("7: Add new premiumUser");
        System.out.println("8: Print all premiumUsers");
        System.out.println("9: Print premiumUser by a given id");
        System.out.println("10: Update premiumUser plan by a given id");
        System.out.println("11: Delete premiumUser by a given id");
        System.out.println("-----------------------------------");
        System.out.println("12: Add new album");
        System.out.println("13: Print all albums");
        System.out.println("14: Print album by a given id");
        System.out.println("15: Delete album by a given id");
        System.out.println("-----------------------------------");
        System.out.println("16: Add new playlist");
        System.out.println("17: Print all playlists");
        System.out.println("18: Print playlist by a given id");
        System.out.println("19: Update playlist privacy by a given id");
        System.out.println("20: Delete playlist by a given id");
        System.out.println("-----------------------------------");
        System.out.println("21: Add new song");
        System.out.println("22: Print all songs");
        System.out.println("23: Print song by a given id");
        System.out.println("24: Delete song by a given id");
        System.out.println("-----------------------------------");
        System.out.println("25: Add song to playlist");
        System.out.println("26: Play a song!");
        System.out.println("-----------------------------------");
        System.out.println("0: Exit");
        System.out.println("-----------------------------------");
    }

    private void showMenu2()
    {
        //System.out.println("Id-ul cantecului pe care vreti sa il ascultati: ");

        service.GUI();
    }

    public void runMenu()
    {
        Scanner scan = new Scanner(System.in);
        int optt;

        System.out.println("Daca doriti sa ascultati ceva apasti 2, altfel 1!");
        optt = scan.nextInt();
        scan.nextLine();

        if(optt == 1)
        {
            showMenu1();
            String ok;
            int opt;
            do
            {
                opt = scan.nextInt();
                scan.nextLine();

                if(opt == 0)
                {
                    service.closeConnection();
                    System.out.println("Close Program!");
                }

                if(opt == 1)
                {
                    service.addArtist();
                    System.out.println("-----------------------------------");
                }

                if(opt == 2)
                {
                    service.printArtists();
                    System.out.println("-----------------------------------");
                }

                if(opt == 3)
                {
                    service.printSortedArtists();
                    System.out.println("-----------------------------------");
                }

                if(opt == 4)
                {
                    service.printArtistById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 5)
                {
                    service.updateArtist();
                    System.out.println("-----------------------------------");
                }

                if(opt == 6)
                {
                    service.deleteArtistById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 7)
                {
                    service.addPremiumUser();
                    System.out.println("-----------------------------------");
                }

                if(opt == 8)
                {
                    service.printPremiumUsers();
                    System.out.println("-----------------------------------");
                }

                if(opt == 9)
                {
                    service.printPremiumUserById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 10)
                {
                    service.updatePremiumUser();
                    System.out.println("-----------------------------------");
                }

                if(opt == 11)
                {
                    service.deletePremiumUserById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 12)
                {
                    service.addAlbum();
                    System.out.println("-----------------------------------");
                }

                if(opt == 13)
                {
                    service.printAlbums();
                    System.out.println("-----------------------------------");
                }

                if(opt == 14)
                {
                    service.printAlbumById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 15)
                {
                    service.deleteAlbumById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 16)
                {
                    service.addPlaylist();
                    System.out.println("-----------------------------------");
                }

                if(opt == 17)
                {
                    service.printPlaylists();
                    System.out.println("-----------------------------------");
                }

                if(opt == 18)
                {
                    service.printPlaylistById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 19)
                {
                    service.updatePlaylist();
                    System.out.println("-----------------------------------");
                }

                if(opt == 20)
                {
                    service.deletePlaylistById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 21)
                {
                    service.addSong();
                    System.out.println("-----------------------------------");
                }

                if(opt == 22)
                {
                    service.printSongs();
                    System.out.println("-----------------------------------");
                }

                if(opt == 23)
                {
                    service.printSongById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 24)
                {
                    service.deleteSongById();
                    System.out.println("-----------------------------------");
                }

                if(opt == 25)
                {
                    service.addSongToPlaylist();
                    System.out.println("-----------------------------------");
                }


                if(opt != 0)
                {
                    System.out.println("Do u want another action? yes/no");
                    ok = scan.nextLine();
                    ok = ok.toLowerCase();

                    if(ok.equals("yes"))
                        showMenu1();

                    else
                    {
                        opt = 0;
                        service.closeConnection();
                        System.out.println("Close Program!");
                        scan.close();
                    }
                }
            } while (opt != 0);
        }

        else
        {
            showMenu2();
        }
    }
}

























