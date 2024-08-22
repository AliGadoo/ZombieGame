package Game.Zombies;
import Game.AnimEventListener;

import javax.media.opengl.GL;

import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;

public class Zombie {

    private double x, y;
    private double direction;

    public Zombie(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void  move(double value){
        x-=value;
    }

    public  void DrawZombie(GL gl, double x, double y, int index, float xScale, float yScale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, AnimEventListener.textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated( x/(MAX_WIDTH/2.0) - 1, y/(MAX_HEIGHT/2.0) - 1, 0);
        gl.glScaled(0.01*xScale, 0.01*yScale, 1);
        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1f, -1f,-1f);

        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1f, -1f,-1f);

        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1f, 1f,-1f);

        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1f, 1f,-1f);

        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
}
