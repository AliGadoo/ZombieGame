package Game.Gui;

import javax.media.opengl.GL;
import java.net.URL;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import static Game.AnimEventListener.textureNames;
import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;

//import javafx.util.Duration;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
import Game.AnimEventListener;

public class Menu {
//   public MediaPlayer mediaPlayer;
    boolean isSoundPlaying;
    public int mute = 6;


int x = MAX_WIDTH/2, y = MAX_WIDTH/2-10;
int indexMenu [] = {59,60,61,62,63,64,65,66,67,68};

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public  void drawMenu(GL gl ) {

        AnimEventListener animEventListener = new AnimEventListener();
        animEventListener.drawBackground(gl);
//        animEventListener.drawSprite(gl, x, y, indexMenu[0],20 , 10);
        animEventListener.drawSprite(gl, x, y+30, indexMenu[4],20 , 10);
        animEventListener.drawSprite(gl, x, y+18, indexMenu[5],20 , 10);
        animEventListener.drawSprite(gl, x, y+6, indexMenu[2],20 , 10);
        animEventListener.drawSprite(gl, x, y-6, indexMenu[3],20 , 10);
        animEventListener.drawSprite(gl, MAX_WIDTH-5, MAX_WIDTH-5, indexMenu[mute],5 , 5);




    }


    public void playsound(String s) {
        playsound(s, false);
    }
    public void playsound(String s, boolean stopCurrent) {
        try {
            URL soundURL = getClass().getResource(s);
            if (soundURL == null) {
                System.out.println("Sound file not found: " + s);
                return;
            }

//            JFXPanel j = new JFXPanel(); // Initialize JavaFX
//            System.out.println(stopCurrent);
//            System.out.println(mediaPlayer);
//            System.out.println(isSoundPlaying);
//            if (isSoundPlaying && stopCurrent) {
//                mediaPlayer.stop();
//            }

//            if (mediaPlayer == null || stopCurrent || !isSoundPlaying) {
//                Media soundTrack = new Media(soundURL.toURI().toString());
//                mediaPlayer = new MediaPlayer(soundTrack);
//                mediaPlayer.play();
//                isSoundPlaying = true;
////
//                mediaPlayer.setOnEndOfMedia(() -> {
//                    isSoundPlaying = false;
//                    mediaPlayer.stop();
////                    System.out.println("Stopped");
//                });
//            }

//            if (mediaPlayer.getTotalDuration() != null && mediaPlayer.currentTimeProperty().get() != null) {
//                System.out.println("Remaining Duration: " +
//                        mediaPlayer.getTotalDuration().subtract(mediaPlayer.currentTimeProperty().get()));
//            }

//            System.out.println("MediaPlayer Status: " + mediaPlayer.getStatus());
//            mediaPlayer.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sound stopped due to an error.");
        }
    }


}
