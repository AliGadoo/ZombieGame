package Game;

import Game.Gui.Menu;
import Game.Players.Bullet;
import Game.Players.Player;
import Game.Zombies.Zombie;
import Texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public class AnimEventListener extends AnimationListener{

    public static final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers
    public static final double End_of_x = MAX_WIDTH - 4;
    public static final double start_of_x = 25 ;
    public static final double End_of_Y = MAX_HEIGHT - 29;
    public static final double start_of_y = 12 ;


    int player1X = 30 , player1Y = 34;
    Player player1 = new Player(player1X,player1Y);
    int player2X = 30 , player2Y = 68;
    Player player2 = new Player(player2X,player2Y);
    int p1AnimationIndex=0;
    int p2AnimationIndex = 20;
    boolean isMultiPlayer = true;

    double xPosition = 0, yPosition = 0;
    int whatdraw = 0;
    public static String[] textureNames = {
            "Player1//P1move0.png", "Player1//P1move1.png", "Player1//P1move2.png", "Player1//P1move3.png", "Player1//P1move4.png",
            "Player1//P1move5.png", "Player1//P1move6.png", "Player1//P1move7.png", "Player1//P1move8.png", "Player1//P1move9.png",
            "Player1//P1move10.png", "Player1//P1move11.png", "Player1//P1move12.png", "Player1//P1move13.png", "Player1//P1move14.png",
            "Player1//P1move15.png", "Player1//P1move16.png", "Player1//P1move17.png", "Player1//P1move18.png", "Player1//P1move19.png",

            "Player2//P2move0.png", "Player2//P2move1.png", "Player2//P2move2.png", "Player2//P2move3.png", "Player2//P2move4.png",
            "Player2//P2move5.png", "Player2//P2move6.png", "Player2//P2move7.png", "Player2//P2move8.png", "Player2//P2move9.png",
            "Player2//P2move10.png", "Player2//P2move11.png", "Player2//P2move12.png", "Player2//P2move13.png", "Player2//P2move14.png",
            "Player2//P2move15.png", "Player2//P2move16.png", "Player2//P2move17.png", "Player2//P2move18.png", "Player2//P2move19.png",

            "Bullet.png",// index 40
            "Zombie//Blood.png",// 41
            "Zombie//Zmove0.png","Zombie//Zmove1.png","Zombie//Zmove2.png","Zombie//Zmove3.png","Zombie//Zmove4.png"
            ,"Zombie//Zmove5.png","Zombie//Zmove6.png","Zombie//Zmove7.png","Zombie//Zmove8.png","Zombie//Zmove9.png"
            ,"Zombie//Zmove10.png","Zombie//Zmove11.png","Zombie//Zmove12.png","Zombie//Zmove13.png","Zombie//Zmove14.png"
            ,"Zombie//Zmove15.png","Zombie//Zmove16.png"
            ,"Menu//PLAYBUTTON.png","Menu//SETTINGS.png","Menu//HOW  PLAY.png","Menu//EXITBUTTON.png",
            "Menu//SINGLE PLAYER.png","Menu//MULITI PLAYERS .png",
            "Menu//soundOnWhite.png" ,"Menu//soundOffWhite.png",
            "Menu//BACKBUTTON.png",
            "heart.png","Night.png" , // index 69
            "Digits//0.png","Digits//1.png","Digits//2.png","Digits//3.png","Digits//4.png","Digits//5.png","Digits//6.png","Digits//7.png","Digits//8.png","Digits//9.png", "Digits//slash.png",
            "Menu//background.png"
    };

    int[] player1Move = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16, 17, 18, 19},
            player2Move = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39},
            zombieMove  ={42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58};
    Menu menu = new Menu();
    boolean mute = false;
    ArrayList<Zombie> zombies =new ArrayList<>();
    int zombieAnimationIndex=0;

    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public static int[] textures = new int[textureNames.length];


    public void drawSprite(GL gl, double x, double y, int index, float xScale, float yScale){

        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, AnimEventListener.textures[index]);    // Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(x / (MAX_WIDTH / 2.0) - 1, y / (MAX_HEIGHT / 2.0) - 1, 0);
        gl.glScaled(0.01 * xScale, 0.01 * yScale, 1);
        //System.out.println(x +" " + y);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void drawBackground(GL gl){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length-1]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }

    public void DrawDigits(GL gl ,double x, double y, int digit, float xScale, float yScale){
        if(digit >=10){
            int rightDigit = digit % 10;
            drawSprite(gl, x+2 , y , 70 + rightDigit , xScale , yScale);
            drawSprite(gl, x , y , 70 + digit/10 , xScale , yScale);
        }else {
            drawSprite(gl, x , y , 70 + digit , xScale , yScale);
        }
    }

    public boolean isColliding(double x1 , double y1 , double radius1 , double x2 , double y2 , double radius2) {
        double dx = x2-x1;
        double dy = y2-y1;
        double distanceSquare = Math.pow(dx , 2) + Math.pow(dy,2);
        return distanceSquare <= Math.pow(radius1 + radius1 , 2);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(1.0f, 1f, 0f, 1.0f);    //This Will Clear The Background Color To Black
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA, // Internal Texel Format,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA, // External format from image,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels() // Image data
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }


    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        handleKeyPress();

    switch (whatdraw) {
        case 0:
            menu.drawMenu(gl);
            if (mute == false) {

//                menu.playsound("StartSound.mp3");
//                menu.mediaPlayer.setMute(false);

            } else {
//                menu.mediaPlayer.setMute(true);
            }


            break;
        case 1:
            drawSprite(gl, 50, 50, 69, 100, 100);
            zombieAnimationIndex++;
            zombieAnimationIndex %= 17;
            p1AnimationIndex %= player1Move.length;

            player1.updateBullets();
            drawSprite(gl, player1.getX(), player1.getY(), p1AnimationIndex, 10, 10);
            player1.drawBullets(gl);


            for (int i = 0; i < player1.health; i++) {
                drawSprite(gl, 2 + i * 5, 95, 68, 3, 3);
            }

            // max bullets
            DrawDigits(gl , 9 , 90 , player1.MAX_BULLETS, 2,3);

            // slash sign
            gl.glPushMatrix();
            gl.glTranslated(6.5/ (MAX_WIDTH / 2.0) - 1, 90 / (MAX_HEIGHT / 2.0) - 1, 0);
            gl.glRotated(-30, 0, 0, 1);
            gl.glTranslated(-(6.5 / (MAX_WIDTH / 2.0) - 1), -(90 / (MAX_HEIGHT / 2.0) - 1), 0);
            drawSprite(gl, 6.5, 90, 80, 1, 4);
            gl.glPopMatrix();

            // player bullets
            if(player1.MAX_BULLETS - player1.counterShots <=9 ){
                DrawDigits(gl , 3 , 90 , player1.MAX_BULLETS - player1.counterShots, 2,3);

            }else {
                DrawDigits(gl , 2 , 90 , player1.MAX_BULLETS - player1.counterShots, 2,3);
            }


            drawSprite(gl , 13 , 90 , 40 , 3,4);
            drawSprite(gl , 14 , 90 , 40 , 3,4);
            drawSprite(gl , 15 , 90 , 40 , 3,4);

            if (zombies.isEmpty()) {
                int yz = (int) (Math.random() * 65) + 10;
                int xz = (int) (Math.random() * 15) + 100;
                Zombie zombie = new Zombie(xz, yz);
                zombies.add(0, zombie);
            }
            Zombie zombie= zombies.get(0);
            if (zombie.getX() > 20) {
                zombie.DrawZombie(gl, zombie.getX(), zombie.getY(), zombieMove[zombieAnimationIndex], 10, 10);
                zombie.move(1);
            } else {
                zombies.remove(0);

            }

            zombieHitsPlayer(zombie , player1);
            bulletHitsZombie(zombie, player1);

            if(isMultiPlayer){
                player2.updateBullets();
                drawSprite(gl, player2.getX(), player2.getY(), p2AnimationIndex, 10, 10);
                player2.drawBullets(gl);
                p2AnimationIndex %= player2Move.length;

                for (int i = 0; i < player2.health; i++) {
                    drawSprite(gl, MAX_WIDTH - 2 - i * 5, 95, 68, 3, 3);
                }

                // max bullets
                DrawDigits(gl , 93 , 90 , player2.MAX_BULLETS, 2,3);

                // slash sign
                gl.glPushMatrix();
                gl.glTranslated(91/ (MAX_WIDTH / 2.0) - 1, 90 / (MAX_HEIGHT / 2.0) - 1, 0);
                gl.glRotated(-30, 0, 0, 1);
                gl.glTranslated(-(91 / (MAX_WIDTH / 2.0) - 1), -(90 / (MAX_HEIGHT / 2.0) - 1), 0);
                drawSprite(gl, 91, 90, 80, 1, 4);
                gl.glPopMatrix();

                // player bullets
                if(player2.MAX_BULLETS - player2.counterShots <=9 ){
                    DrawDigits(gl , 88 , 90 , player2.MAX_BULLETS - player2.counterShots, 2,3);

                }else {
                    DrawDigits(gl , 87 , 90 , player2.MAX_BULLETS - player2.counterShots, 2,3);
                }

                drawSprite(gl , 99 , 90 , 40 , 3,4);
                drawSprite(gl , 98 , 90 , 40 , 3,4);
                drawSprite(gl , 97 , 90 , 40 , 3,4);

                zombieHitsPlayer(zombie,player2);
                bulletHitsZombie(zombie, player2);
            }
            break;

    }
    }

    private void bulletHitsZombie(Zombie zombie, Player player) {
        for (int i = 0; i < player.getBullets().size(); i++) {
            Bullet bullet = player.getBullets().get(i);
            if(isColliding(bullet.getX() , bullet.getY() , 2 , zombie.getX() , zombie.getY() , 3)){
                zombies.remove(zombie);
                player.getBullets().remove(bullet);
                player.setScore(player.getScore() + 1);
            }

        }
    }

    private void zombieHitsPlayer (Zombie zombie, Player player) {
        if (isColliding( player.getX(), player.getY(), 3,zombie.getX(), zombie.getY(), 3)) {
            player.getDamaged();
            zombies.remove(zombie);
            if (player.playerIsDead()) {
                System.out.println("Player is dead! Game over.");
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
    public void handleKeyPress() {
        if (isKeyPressed(KeyEvent.VK_A)) {
                if (player1.getX() > start_of_x) {
                    player1.setX(--player1X);
                    p1AnimationIndex++;
                }
            }
            if (isKeyPressed(KeyEvent.VK_D)) {
                if (player1.getX() < End_of_x ) {
                    player1.setX(++player1X);
                    p1AnimationIndex++;
                }
            }
        if (isKeyPressed(KeyEvent.VK_W)) {
            if (player1.getY() < End_of_Y ) {
                player1.setY(++player1Y);
                p1AnimationIndex++;
            }
        }
        if (isKeyPressed(KeyEvent.VK_S)) {
            if (player1.getY() > start_of_y) {
                player1.setY(--player1Y);
                p1AnimationIndex++;
            }
        }
        if (isKeyPressed(KeyEvent.VK_Z)) {
            player1.shoot();
        }
        if(isKeyPressed(KeyEvent.VK_X)){
            player1.reload();
        }

        if(player2 != null){
            if (isKeyPressed(KeyEvent.VK_LEFT)) {
                if (player2.getX() > start_of_x) {
                    player2.setX(--player2X);
                    p2AnimationIndex++;
                }
            }
            if (isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (player2.getX() < End_of_x ) {
                    player2.setX(++player2X);
                    p2AnimationIndex++;
                }
            }
            if (isKeyPressed(KeyEvent.VK_UP)) {
                if (player2.getY() < End_of_Y ) {
                    player2.setY(++player2Y);
                    p2AnimationIndex++;
                }
            }
            if (isKeyPressed(KeyEvent.VK_DOWN)) {
                if (player2.getY() > start_of_y) {
                    player2.setY(--player2Y);
                    p2AnimationIndex++;
                }
            }
            if (isKeyPressed(KeyEvent.VK_SPACE)) {
                player2.shoot();
            }
            if(isKeyPressed(KeyEvent.VK_M)){
                player2.reload();
            }
        }
    }

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        double x = e.getX();
        double y = e.getY();

        System.out.println(x+" "+y);

        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
        System.out.println(width+" "+height);

        xPosition = (int) ((x / width) * 100);
        yPosition = ((int) ((y / height) * 100));
        yPosition = 100 - yPosition;

        System.out.println("x "+xPosition+" y "+yPosition);
        if (whatdraw == 0) {

            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 30 && yPosition <= 40) {
                System.exit(0);
            }
            if (xPosition >= 92.5 && xPosition <= 97.5 && yPosition >= 92.5 && yPosition <= 97.5) {
                if (mute == true) {
                    mute = false;
                } else {
                    mute = true;
                }
                if (menu.mute == 6) {
                    menu.mute = 7;
                } else {
                    menu.mute = 6;
                }
            }

            if (xPosition >= 40 && xPosition <= 60 && yPosition >= 65 && yPosition <= 75) {
                whatdraw = 1;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
