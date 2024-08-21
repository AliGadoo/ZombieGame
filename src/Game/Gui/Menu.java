package Game.Gui;

import javax.media.opengl.GL;

import static Game.AnimEventListener.textureNames;
import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;
import  Game.AnimEventListener;


import Game.AnimEventListener;

public class Menu {

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

    public  void drawMenu(GL gl )
{

    AnimEventListener animEventListener = new AnimEventListener();
    animEventListener.drawBackground(gl);
    animEventListener.drawSprite(gl, x, y, indexMenu[0],20 , 10);
    animEventListener.drawSprite(gl, x, y-12, indexMenu[1],20 , 10);
    animEventListener.drawSprite(gl, x, y-24, indexMenu[2],20 , 10);
    animEventListener.drawSprite(gl, x, y-36, indexMenu[3],20 , 10);

}



}
