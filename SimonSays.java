/**
    This is the class for the simonsays module puzzle. This is where all the functionality needed for the simonsays puzzle resides.
    The entire module is made by the help of https://www.youtube.com/watch?v=_YOgn2VgXOU&t=1788s

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SimonSays extends JPanel implements ActionListener, ModuleTemplate{

    public int flashed, ticks, dark, i, u, indexPattern, k;
    public ArrayList<Integer> currentPattern;

    //contains the panels, the randomizer, the timer.
    public JPanel backpanel, testpanel;
    public Random random;
    public Timer numtimer;

    //all of the images for the colors
    public ImageIcon redimage;
    public ImageIcon reddark;
    public ImageIcon yellowimage;
    public ImageIcon yellowdark;
    public ImageIcon blueimage;
    public ImageIcon bluedark;
    public ImageIcon greenimage;
    public ImageIcon greendark;
    
    //booleans of reading various states such as winning, losing and striking
    public boolean makePattern, pressed, SimonStrike, struck, winLoseState;

    //buttons
    public JButton red, green, yellow, blue;

    public SimonSays(){
        start();

        indexPattern = 0;
        dark = 2;
        flashed = 0;
        ticks = 0;
        u = 0;
        k = 0;

        random = new Random();

        makePattern = true;
        SimonStrike = false;
        pressed = false;
        struck = false;
        winLoseState = false;
        
        setLayout(new GridLayout(1,1,10,10));
        backpanel = new JPanel();
        backpanel.setBackground(Color.GRAY);
        backpanel.setLayout(new GridLayout(2,2,10,10));

        numtimer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e){
                //this is for the functionality of simon
                
                ticks++;
                flashed = 0;

                if(u == 1){
                    struck = true;
                }


                if(dark >=0){
                    dark--;
                }

                if(makePattern){
                    if(dark <= 0){
                        if(indexPattern >= currentPattern.size()){ //this adds to the sequence when the patternmaking is on
                            flashed = random.nextInt(40) % 4 + 1;
                            currentPattern.add(flashed);
                            indexPattern = 0;
                            makePattern = false;

                        }else{
                            if(currentPattern.size() == 6){ //this is the amount of round that the person has to endure in order to win. 6 rounds are needed 
                                winLoseState = true;
                                numtimer.stop();
                                backpanel.remove(red);
                                backpanel.remove(blue);
                                backpanel.remove(green);
                                backpanel.remove(yellow);
                                backpanel.setBackground(Color.GREEN);
                            }
                            flashed = currentPattern.get(indexPattern);
                            indexPattern++;
                        }
                        dark = 2;
                    }
                }else if(indexPattern == currentPattern.size()){
                    makePattern = true;
                    indexPattern = 0;
                    dark = 2;
                }
            
                //next set of if statements are for the flashing
                if(flashed == 2){
                    red.setIcon(redimage);
                }else{
                    red.setIcon(reddark);
                }
        
                if(flashed == 4){
                    yellow.setIcon(yellowimage);
                        
                    
                }else{
                    yellow.setIcon(yellowdark);
                        
                }
        
                if(flashed == 1){
                    green.setIcon(greenimage);
                        
                }else{
                    green.setIcon(greendark);
                    
                }
        
                if(flashed == 3){
                    blue.setIcon(blueimage);
                        
                }else{
                    blue.setIcon(bluedark);
                        
                }

			}
            
		});
        
        //the images used for the buttons
        redimage = new ImageIcon("Modules/SimonSaysImages/red.png");
        reddark = new ImageIcon("Modules/SimonSaysImages/reddark.png");
        yellowimage = new ImageIcon("Modules/SimonSaysImages/yellow.png");
        yellowdark = new ImageIcon("Modules/SimonSaysImages/yellowdark.png");
        greenimage = new ImageIcon("Modules/SimonSaysImages/green.png");
        greendark = new ImageIcon("Modules/SimonSaysImages/greendark.png");
        blueimage = new ImageIcon("Modules/SimonSaysImages/blue.png");
        bluedark = new ImageIcon("Modules/SimonSaysImages/darkblue.png");
        
        numtimer.start();
        setBackground(Color.GRAY);
        setUpComponents();
        
        backpanel.add(red);
        backpanel.add(blue);
        backpanel.add(green);
        backpanel.add(yellow);
    
        add(backpanel);
    
     } 
    //this method restarts the entire simonsays phase as if it were a new game
    public void start(){
        indexPattern = 0;
        currentPattern = new ArrayList<Integer>();
        SimonStrike = false;
        u++;
    }


    public void setUpComponents(){ //sets up the buttons for their functionality.

    red = new JButton(redimage);
    red.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                red.setIcon(reddark);
                red.setIcon(redimage);

                //this is the pressed red in order to start the game
                if(k == 0){
                    start();
                    k++;
                }
                
                
                if(currentPattern.get(indexPattern) == 1){
                    indexPattern++;
                }else{
                    SimonStrike = true;
                }
                if(SimonStrike){
                    start();
                }
                ticks = 1;
        }
    });   

    yellow = new JButton(yellowimage);
    yellow.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
            yellow.setIcon(yellowdark);
            yellow.setIcon(yellowimage);
            
            if(currentPattern.get(indexPattern) == 2){
                indexPattern++;
            } else {
                SimonStrike = true;
            }
            
            if(SimonStrike){
                start();
            }
            ticks = 1;
        }  
    });      

    green = new JButton(greenimage);
    green.addActionListener(new ActionListener(){  
        
        public void actionPerformed(ActionEvent e){  
            green.setIcon(greendark);
            green.setIcon(greenimage);
            
            if(currentPattern.get(indexPattern) == 3){
                indexPattern++;
            } else {
                SimonStrike = true;
            }
            
            if(SimonStrike){
                start();
            }
            ticks = 1;
        }  
    });  

    blue = new JButton(blueimage);
    blue.addActionListener(new ActionListener(){  
        
        public void actionPerformed(ActionEvent e){  
            blue.setIcon(bluedark);
            blue.setIcon(blueimage);
            
            if(currentPattern.get(indexPattern) == 4){
                indexPattern++;
            } else {
                SimonStrike = true;
            }
            
            if(SimonStrike){
                start();
            } 
            ticks = 1;
            
        }  
    });  

    }

    @Override
    public void actionPerformed(ActionEvent e) {} //to quell the actionevent import    

    public boolean getWinLoseState() { 
        return winLoseState; 
    }
}
