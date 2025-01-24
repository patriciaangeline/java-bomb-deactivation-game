/**
   This is a class that asks the player to write the necessary IP Address and port number so that they can connect to the server.
   It contains the necessary threads for input and output streams to be received and sent respectively. 
  
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

import java.io.*;
import java.net.*;
import java.util.*;

public class Player {
    private Socket clientSocket; 
    private int playerID;
   
    private GameFrame gameFrame; 

    private ReadFromServer rfsRunnable; 
    private WriteToServer wtsRunnable; 


    // The Player constructor that instantiates gameFrame and calls the methods connectToServer before setting up its GUI. 
    public Player() {
        System.out.println("---- CLIENT ----");

        gameFrame = new GameFrame(1920, 1080);
        connectToServer();
        gameFrame.setUpGUI();

    }

    // A method allows the player to connect to the server and assigns a player number to their respective GUIs. 
    private void connectToServer() {
        try {
            
            Scanner console = new Scanner(System.in);
            System.out.println("IP Address: ");
            String ipAddress = console.nextLine();
            System.out.println("Port Number: ");
            int portNumber = Integer.parseInt(console.nextLine());
            clientSocket = new Socket(ipAddress, portNumber); //portNumber must be 63888

            DataInputStream clientIn = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());

            playerID = clientIn.readInt();
            gameFrame.playerID = playerID;
            System.out.println("Connected to server as Player #" + playerID);

            if(playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }

            rfsRunnable = new ReadFromServer(clientIn);
            wtsRunnable = new WriteToServer(clientOut);
            rfsRunnable.waitForStartMessage();
            
        } catch(IOException ex) {
            System.out.println("IOException from connectToServer()");
        }
    }

    // A private class that reads an integer from the server and sets this integer as the current game state. 
    private class ReadFromServer implements Runnable {
        private DataInputStream CReadIn; 

        public ReadFromServer(DataInputStream ri) {
            CReadIn = ri; 
            System.out.println("    RFS Runnable created");
        }

        @Override
        public void run() {
            try {
                
                while(true) {

                    int state = CReadIn.readInt();
                    
                    if(state != -1) {

                        gameFrame.getGameCanvas().setCurrentState(state);
                    }
                }
            } catch(IOException ex) {
                System.out.println("IOException from ReadFromServer run()");
            }
        }


        // A method that waits for a String to be sent from GameServer so that the read and write threads can start. 
        public void waitForStartMessage() {
            try {
                String startMessage = CReadIn.readUTF();
                System.out.println("Message from server: " + startMessage);

                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();

            } catch(IOException ex) {
                System.out.println("IOException from ReadFromServer waitForStartMessage()");
            }
        }
    }

    // A private class that writes an integer to the server which shall serve as the player's current state and will be mirrored to the other player's gameState. 
    private class WriteToServer implements Runnable {
        private DataOutputStream CWriteOut; 

        public WriteToServer(DataOutputStream wo) {
            CWriteOut = wo; 
            System.out.println("    WTS Runnable created");
        }

        @Override
        public void run() {
            try {
                
                while(true) {
                    GameCanvas checkCanvas = gameFrame.getGameCanvas();
                    
                    if(checkCanvas != null) { 
                        int currentState = gameFrame.getGameCanvas().getCurrentState();
                        CWriteOut.writeInt(currentState);
                        CWriteOut.flush();               

                    }
                    
                    try {
                        Thread.sleep(25); 
                    
                    } catch(InterruptedException ex) {
                        System.out.println("InterruptedException form WriteToServer run()");
                    }
                }

            } catch(IOException ex) {
                System.out.println("IOException from WriteToServer run()");
            }
        }
    }
}
