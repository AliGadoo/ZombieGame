package Game;

import Game.Players.Player;
import Texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.BitSet;

public class AnimEventListener extends AnimationListener{

    public static final int MAX_WIDTH = 100, MAX_HEIGHT = 100; // set max height and width to translate sprites using integers
    public static final double End_of_screen = MAX_WIDTH - 4;
    public static final double start_of_screen = 4 ;
    Player player1;
    int player1X = 10 , player1Y = 50;
    public static String[] textureNames = {"Player1//P1move0.png"};
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public static int[] textures = new int[textureNames.length];

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClearColor(1.0f, 0f, 0f, 1.0f);    //This Will Clear The Background Color To Black
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
        player1 = new Player(player1X,player1Y);
        player1.drawPlayer(gl , player1.getX() , player1.getY() ,0,10,10);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
    public void handleKeyPress() {
        if (isKeyPressed(KeyEvent.VK_LEFT)) {
                if (player1.getX() > start_of_screen) {
                    player1X--;
                }
            }
            if (isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (player1.getX() < End_of_screen ) {
                    player1X++;
                }
            }
        if (isKeyPressed(KeyEvent.VK_UP)) {
            if (player1.getY() < End_of_screen ) {
                player1Y++;
            }
        }
        if (isKeyPressed(KeyEvent.VK_DOWN)) {
            if (player1.getY() > start_of_screen) {
                player1Y--;
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
