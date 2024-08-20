package Game.Players;

import Game.AnimEventListener;

import javax.media.opengl.GL;

import java.util.ArrayList;

import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;

public class Player {

    private double x, y;
    private ArrayList<Bullet> bullets;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.bullets = new ArrayList<>();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void  setX(double x) {
        this.x = x;
    }

    public void  setY(double y) {
        this.y = y;
    }

    public boolean playerIsDead (int health) {
        return health <= 0;
    }

    public void shoot() {
        bullets.add(new Bullet(x,y));
    }

    public void updateBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.moveBullet();
            if (bullet.getX() < 0 || bullet.getX() > MAX_WIDTH || bullet.getY() < 0 || bullet.getY() > MAX_HEIGHT) {
                bullets.remove(i);
                i--;
            }
        }
    }
    public void drawBullets(GL gl) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).drawBullet(gl , 10 , 10);
        }
    }
}
