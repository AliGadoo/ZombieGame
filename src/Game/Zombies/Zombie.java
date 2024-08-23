package Game.Zombies;
import Game.AnimEventListener;

import javax.media.opengl.GL;

import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;

public class Zombie {

    private double x, y;
    private double direction;
    private Blood blood;

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

    public void  move(double px, double py , double speed){
       double angle =Math.atan2(py-y,px-x);
       direction = Math.toDegrees(angle);
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;

    }
    public void Move2P(double player1X, double player1Y, double player2X, double player2Y, double speed) {

        double distancePlayer1 = calculateDistance(player1X, player1Y);
        double distancePlayer2 = calculateDistance(player2X, player2Y);

        if (distancePlayer1 >= distancePlayer2||Math.abs(distancePlayer1-distancePlayer2)<10) {
            move(player2X, player2Y, speed);
        } else {
            move(player1X, player1Y, speed);
        }
    }
    private double calculateDistance(double px, double py) {
        double dx = px - x;
        double dy = py - y;
        return dx * dx + dy * dy;
    }

    public  void DrawZombie(GL gl, double x, double y, int index, float xScale, float yScale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, AnimEventListener.textures[index]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated( x/(MAX_WIDTH/2.0) - 1, y/(MAX_HEIGHT/2.0) - 1, 0);
        gl.glRotated(direction+180,0,0,1);
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
