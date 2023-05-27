package Services;

import CRUD.SongCRUD;
import Entities.Song;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Listener {

    public static void openListener() {
        MainService.open.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseClicked(MouseEvent arg0) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Scanner scan = new Scanner(System.in);
                            int songId;
                            System.out.println("Introduce the id of the song u want to play: ");
                            songId = scan.nextInt();
//                            scan.nextLine();

                            Song song = SongCRUD.getInstance().getSongById(songId);
                            //File file = new File("C:\\Users\\diana\\OneDrive\\Documents\\Facultate\\Anul II, Sem II\\PAO\\billie_bossa_nova.wav");
                            File file = new File(song.getLink());
                            Logic.openFile(file);
                        } catch (UnsupportedAudioFileException e) {
                            System.out.println("Unsupported File Format");
                        } catch (IOException e) {
                            System.out.println("He is a dead JIM :(");
                        } catch (LineUnavailableException e) {
                            System.out.println("He is a dead JIM :(");
                        }

                    }
                });

                thread.start();
            }
        });

    }

    public static void stopListener() {
        MainService.stop.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseClicked(MouseEvent arg0) {

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {

                        Logic.stopSong();

                    }
                });

                thread.start();
            }
        });

    }

}
