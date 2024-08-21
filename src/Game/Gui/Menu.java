package Game.Gui;

import javax.media.opengl.GL;
import java.net.URL;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static Game.AnimEventListener.textureNames;
import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;
import  Game.AnimEventListener;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
import Game.AnimEventListener;

public class Menu {
    private MediaPlayer mediaPlayer;
    boolean isSoundPlaying;

int x = MAX_WIDTH/2, y = MAX_WIDTH/2+20;
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
        animEventListener.drawSprite(gl, x, y, indexMenu[0],20 , 10);
        animEventListener.drawSprite(gl, x, y-12, indexMenu[1],20 , 10);
        animEventListener.drawSprite(gl, x, y-24, indexMenu[2],20 , 10);
        animEventListener.drawSprite(gl, x, y-36, indexMenu[3],20 , 10);
//        playsound("Score.mp3");
        playsound("Score.mp3",true);

    }
    public void playsound(String s) {
        playsound(s, false);
    }
    public void playsound(String s, boolean stopCurrent) {
        try {
            URL soundURL = getClass().getResource(s);
//            in = new FileInputStream(new File(soundURL.toURI()));
//            AudioStream audios = new AudioStream(in);
//            AudioPlayer.player.start(audios);
            JFXPanel j = new JFXPanel();
//            String uri = new File(s).toURI().toString();

            if(mediaPlayer!=null && stopCurrent)
                mediaPlayer.stop();

            if(mediaPlayer==null || stopCurrent || !isSoundPlaying)
            {
                Media soundTrack = new Media(soundURL.toURI().toString());
                mediaPlayer = new MediaPlayer(soundTrack);
                mediaPlayer.play();
                isSoundPlaying = true;
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        isSoundPlaying = false;
                        System.out.println("Stopped");
                    }
                });
            }
            System.out.println(mediaPlayer.getTotalDuration().subtract(mediaPlayer.currentTimeProperty().get()));
            System.out.println(mediaPlayer.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("sound stoped");
        }
    }


}
