package Services;/*
 * Class which contains the various button listeners
 * Author : Karthik Abinav; CS10B057
 */

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
    /*
     * Services.Listener for the open button
     *
     * @params:void
     *
     * @return:void
     */
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

    /*
     * Services.Listener for the stop button
     *
     * @params:void
     *
     * @return:void
     */
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
