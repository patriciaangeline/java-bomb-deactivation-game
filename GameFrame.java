/**
    This is a class responsible for creating the JFrame for the GUI. 
    It contains functions such as transitiontoGameplayState() and transitiontoWinorOverState() to swap the canvases to their respective frames.

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
import javax.swing.*;

public class GameFrame {
    private int width; 
    private int height; 
    private int i;

    private JFrame gameFrame; 
    private GameCanvas gameCanvas;
    private ModuleCanvas moduleCanvas;
    private OverCanvas overCanvas; 
    private WinCanvas winCanvas;
    public int playerID; 

    // The GameFrame constructor that instantiates the necessary canvases using the arguments passed in for width and height. 
    public GameFrame(int w, int h) {
        width = w; 
        height = h; 

        gameFrame = new JFrame();
        gameCanvas = new GameCanvas(width, height);
        moduleCanvas = new ModuleCanvas(width, height);
        overCanvas = new OverCanvas(width, height);
        winCanvas = new WinCanvas(width, height);

    }

    // The setupGUI() method is responsible for customizing the JFrame and adding gameCanvas to the frame. 
    public void setUpGUI() {
        Container cp = gameFrame.getContentPane();
        cp.add(gameCanvas, BorderLayout.CENTER);

        gameFrame.setTitle("Player #" + playerID +  "_No Human Being Detonates");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.add(gameCanvas);
        gameFrame.pack();
        gameFrame.setVisible(true);

        transitiontoGameplayState();
        transitiontoWinorOverState();

    }

    // The transitiontoGameplayState() method constantly checks for the current gameState, starts the music, and adds moduleCanvas. 
    public void transitiontoGameplayState() {
        while(true) {
            int currentState = gameCanvas.getCurrentState();

            if(currentState == gameCanvas.titleState) {
                
                try {
                    Thread.sleep(5000);
                
                    
                } catch(InterruptedException ex) {
                    System.out.println("InterruptedException from transitiontoGameplay()");
                } 
            }

            if(currentState == gameCanvas.gameplayState) {
                String musicfilePath = "In the Hall of the Mountain King.wav";
                MusicPlayer musicObject = new MusicPlayer();
                musicObject.playMusic(musicfilePath);

                gameFrame.getContentPane().removeAll();
                gameFrame.repaint();
                gameFrame.revalidate();
                break;
            }
            
        }
        
        moduleCanvas.setUpModules();
        Container cp2 = gameFrame.getContentPane();
        cp2.add(moduleCanvas, BorderLayout.CENTER);
        cp2.repaint();
        cp2.revalidate();
    }
    
    // The transitiontoWinorOverState() method constantly checks for the current gameState and the time left from bombTimer, and adds necessary canvases (winCanvas or overCanvas) based on whether or not the player has accomplished the modules. 
    public void transitiontoWinorOverState() {
        while(true) {
            int currentState = gameCanvas.getCurrentState();
            
            if(currentState == gameCanvas.gameplayState) {
                SimonSays simonModule = moduleCanvas.getSimonSays();
                boolean simonWinState = simonModule.getWinLoseState();
                
                Keypad keypadModule = moduleCanvas.getKeypad();
                boolean keypadWinState = keypadModule.getWinLoseState();
                
                
                BombTimer timerBomb = moduleCanvas.getBombTimer();
                int zeroMinutes = timerBomb.getMinutesLeft();
                int zeroSeconds = timerBomb.getSecondsLeft();

                if((simonWinState == true) && (keypadWinState == true)) {
                    gameCanvas.gameState = gameCanvas.congratulationsState;
                    gameFrame.getContentPane().removeAll();
                    gameFrame.repaint();
                    gameFrame.revalidate();
                    break;

                } else if((zeroMinutes == 0 && zeroSeconds == 0) || moduleCanvas.strikesModule.playerloses == true) {
                    gameCanvas.gameState = gameCanvas.gameoverState;
                    gameFrame.getContentPane().removeAll();
                    gameFrame.repaint();
                    gameFrame.revalidate();
                    break;
                }

                try {
                    Thread.sleep(5000);

                } catch(InterruptedException ex) {
                    System.out.println("InterruptedException from transitiontoOverState()");
                }
            }
            
        }
        
        if(gameCanvas.gameState == gameCanvas.gameoverState) {
            Container cp3 = gameFrame.getContentPane();
            cp3.add(overCanvas, BorderLayout.CENTER);
            cp3.repaint();
            cp3.revalidate();
        }

        if(gameCanvas.gameState == gameCanvas.congratulationsState) {
            Container cp4 = gameFrame.getContentPane();
            cp4.add(winCanvas, BorderLayout.CENTER);
            cp4.repaint();
            cp4.revalidate();
        }
    }
    
    // Getter method so that gameCanvas can be accessed by other classes 
    public GameCanvas getGameCanvas() {
        return gameCanvas; 
    }
}