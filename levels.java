package Game.Gui;

import javax.media.opengl.GL;
import java.net.URL;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import static Game.AnimEventListener.MAX_WIDTH;

//import javafx.util.Duration;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
import Game.AnimEventListener;

public class levels {

    int x = MAX_WIDTH/2, y = MAX_WIDTH/2-10;
    int indexplay[] = {91,92,93,94};


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


    public  void drawlevels(GL gl ) {

        AnimEventListener animEventListener = new AnimEventListener();
        animEventListener.drawBackground(gl);

        animEventListener.drawSprite(gl, x, y+30, indexplay[0],20 , 10);
        animEventListener.drawSprite(gl, x, y+18, indexplay[1],20 , 10);
        animEventListener.drawSprite(gl, x, y+6, indexplay[2],20 , 10);
        animEventListener.drawSprite(gl, x, y-6, indexplay[3],20 , 10);

    }

}
