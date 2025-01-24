/**
   This is a class that allows two clients to connect to a server. 
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


public class GameServer {
    private ServerSocket serverSocket; 
    private int numPlayers; 
    private int maxPlayers; 

    private Socket p1Socket; 
    private Socket p2Socket; 
    private ReadFromClient p1ReadRunnable; 
    private ReadFromClient p2ReadRunnable; 
    private WriteToClient p1WriteRunnable; 
    private WriteToClient p2WriteRunnable; 

    private int p1GameState; 
    private int p2GameState; 
    
    // The GameServer() constructor that instantiates the number of players, game states for both players, and accepts client connections.
    public GameServer() {
        System.out.println("---- GAME SERVER ----");
        numPlayers = 0; 
        maxPlayers = 2; 

        p1GameState = 0; 
        p2GameState = 0;

        try {
            serverSocket = new ServerSocket(63888);

        } catch(IOException ex) {
            System.out.println("IOException from GameServer constructor");

        }
    }

    // A method that creates a Socket object whenever a client accepts. Constructs the necessary Input and OutputStreams as well as the necessary Runnables per player. 
    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");

            while(numPlayers < maxPlayers) {
                Socket s = serverSocket.accept();
                
                DataInputStream serverIn = new DataInputStream(s.getInputStream());
                DataOutputStream serverOut = new DataOutputStream(s.getOutputStream());

                numPlayers++; 
                serverOut.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers, serverIn);
                WriteToClient wtc = new WriteToClient(numPlayers, serverOut);

                if(numPlayers == 1) {
                    p1Socket = s; 
                    p1ReadRunnable = rfc; 
                    p1WriteRunnable = wtc;

                } else {
                    p2Socket = s; 
                    p2ReadRunnable = rfc; 
                    p2WriteRunnable = wtc; 

                    p1WriteRunnable.sendStartMessage();
                    p2WriteRunnable.sendStartMessage();

                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();

                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                    
                }
            }

            System.out.println("We now have 2 players. No longer accepting connections.");

        } catch(IOException ex) {
            System.out.println("IOException from acceptConnections()");
        }
    }

    // A private class that reads an integer from either client to update the game states for each player. 
    private class ReadFromClient implements Runnable {
        private int playerID; 
        private DataInputStream SReadIn; 

        public ReadFromClient(int pid, DataInputStream ri) {
            playerID = pid; 
            SReadIn = ri; 
            System.out.println("    RFC" + playerID + " Runnable created");
        }

        @Override
        public void run() {
            try {
                
                while(true) {
                    if(playerID == 1) {
                        p1GameState = SReadIn.readInt();
                        
                    } else {
                        p2GameState = SReadIn.readInt();
                        
                    }
                }

            } catch(IOException ex) {
                System.out.println("IOException from ReadFromClient run()");
            }
        }
    }

    // A private class that sends an integer to both clients to update the game states for each player. 
    private class WriteToClient implements Runnable {
        private int playerID; 
        private DataOutputStream SWriteOut; 

        public WriteToClient(int pid, DataOutputStream wo) {
            playerID = pid; 
            SWriteOut = wo; 
            System.out.println("    WFC" + playerID + " Runnable created");
        }

        @Override
        public void run() {
            try {

                while(true) {
                    if(playerID == 1) {
                        SWriteOut.writeInt(p2GameState);
                        SWriteOut.flush();
                        
                    } else {
                        SWriteOut.writeInt(p1GameState);
                        
                    }

                    try {
                        Thread.sleep(25); 

                    } catch(InterruptedException ex) {
                        System.out.println("InterruptedException from WriteToClient run()");
                    }
                }

            } catch(IOException ex) {
                System.out.println("IOException from WriteToClient run()");
            }
        }
        
        // A method that sends a message to both players that shall initialize their respective GUIs. 
        public void sendStartMessage() {
            try {
                SWriteOut.writeUTF("We now have 2 players. Good luck!");
            
            } catch(IOException ex) {
                System.out.println("IOException from WriteToClient sendStartMessage()");
            }
        }
    }

    // The main method of GameServer that allows client-server connections. 
    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
