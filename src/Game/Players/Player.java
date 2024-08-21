package Game.Players;

import Game.AnimEventListener;

import javax.media.opengl.GL;

import java.util.ArrayList;

import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;

public class Player {

    private double x, y;
    private ArrayList<Bullet> bullets;
    private int MAX_BULLETS = 10;
    private int counterShots = 0;
    private long lastShotTime = 0;
    private int delayTime = 400;

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
        long currentTime = System.currentTimeMillis();
        if (counterShots >= MAX_BULLETS) {
            System.out.println("need to reload1");
            return;
        }
        if (currentTime - lastShotTime >= delayTime) {
            bullets.add(new Bullet(x, y));
            lastShotTime = currentTime;
            counterShots++;
        }
    }

    public void reload() {
        counterShots = 0;
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
