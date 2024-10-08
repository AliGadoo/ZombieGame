package Game;


import Game.Gui.Menu;
import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;

public class Animation extends JFrame {

    public static void main(String[] args) {

        new Animation();
    }


    public Animation() {
        GLCanvas glcanvas;
        Animator animator;

        AnimationListener listener = new AnimEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(24);
        animator.add(glcanvas);
        animator.start();

        setTitle("Zombie game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        glcanvas.requestFocus();
    }
}
