/**
   This is a class that declares the game states for the game. 
   It draws the necessary components for the title state with the aid of paintComponent and methods from KeyListener.
  
    @author Sophia Avielle Gregorio (223019) & Patricia Angeline Tan (226189)
    @version May 15, 2023
**/

/*
    I have not discussed the Java language code in my program
    with anyone other than my instructor or the teaching assistants
    assigned to this course.

    I have not used Java language code obtained from another student,
    or any other unauthorized source, either modified or unmodified.

    If any Java language code or documentation used in my program
    was obtained from another source, such as a textbook or website,
    that has been clearly noted with a proper citation in the comments
    of my program.
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class GameCanvas extends JComponent implements KeyListener {
    private int width; 
    private int height;

    // Game States
    public int gameState; 
    public final int titleState = 0; 
    public final int mechanicsState = 1; 
    public final int gameplayState = 2; 
    public final int gameoverState = 3;
    public final int congratulationsState = 4; 

    // Pages
    private TitleComponents titlePage; 

    // GameCanvas constructor takes in arguments for width and height for resizing. It is focusable so that it can detect keystrokes. 
    public GameCanvas(int w, int h) {
        width = w; 
        height = h; 

        gameState = titleState; 
        titlePage = new TitleComponents();
       
        addKeyListener(this);
        setFocusable(true);

        setPreferredSize(new Dimension(width, height));
    }


    // Method overriding of paintComponent to draw the graphics necessary for titleState. 
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double background = new Rectangle2D.Double(0, 0, width, height);
        g2d.setPaint(Color.BLACK);
        g2d.fill(background);

        if(gameState == titleState) {
            titlePage.draw(g2d);

        } 
    }

    // Method overriding of the KeyListener methods to transition from titleState to gameplayState. 
    @Override
    public void keyTyped(KeyEvent e) {}
    
    // Gets the keycode to determine the next steps to be done. 
    @Override
    public void keyPressed(KeyEvent e) {
        int getKeyPressed = e.getKeyCode();
        if(gameState == titleState) {

            if(getKeyPressed == KeyEvent.VK_DOWN) {
                titlePage.currentOption++; 
                repaint();
                
                if(titlePage.currentOption >= titlePage.getOptionsLength()) {
                    titlePage.currentOption = 0; 
                    repaint();
                }
            }

            if(getKeyPressed == KeyEvent.VK_UP) {
                titlePage.currentOption--; 
                repaint();
                
                if(titlePage.currentOption < 0) {
                    titlePage.currentOption = titlePage.getOptionsLength()-1; 
                    repaint();
                }
            }

            if(getKeyPressed == KeyEvent.VK_ENTER) {
                if(titlePage.currentOption == 0) {
                    gameState = gameplayState; 

                } else if(titlePage.currentOption == 1) {
                    System.exit(0);
                }
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // Getter methods to access and change the gameState. 
    public int getCurrentState() { 
        return gameState;
    }

    public void setCurrentState(int n) { 
        gameState = n; 
    }

}
