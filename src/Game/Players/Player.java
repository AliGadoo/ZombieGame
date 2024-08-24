package Game.Players;

import Game.AnimEventListener;
import Sound.Sound;

import javax.media.opengl.GL;

import java.util.ArrayList;

import static Game.AnimEventListener.MAX_HEIGHT;
import static Game.AnimEventListener.MAX_WIDTH;

public class Player {

    private double x, y;
    private ArrayList<Bullet> bullets;
    public int MAX_BULLETS = 10;
    public int counterShots = 0;
    private long lastShotTime = 0;
    private int delayTime = 400;
    private int score = 0;
    public int health = 3;
    Sound sound = new Sound();

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

    public int getScore() {
        return score;
    }

    public ArrayList<Bullet> getBullets () {
        return bullets;
    }

    public void  setX(double x) {
        this.x = x;
    }

    public void  setY(double y) {
        this.y = y;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean playerIsDead () {
        return health <= 0;
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (counterShots >= MAX_BULLETS) {
            System.out.println("need to reload1");
            sound.setFile(5);
            sound.play();
            return;
        }
        if (currentTime - lastShotTime >= delayTime) {
            bullets.add(new Bullet(x+3, y-2.5));
            sound.setFile(4);
            sound.play();
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
            bullets.get(i).drawBullet(gl , 3 , 3);
        }
    }
   public void getDamaged(){
      health =  health-1;
    }

}
