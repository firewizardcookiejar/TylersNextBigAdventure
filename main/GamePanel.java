package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {

    

    //SCREEN SIZE
    final int originalTileSize = 16; //16x16 bit graphics
    final int scale = 3;  // <-- change this to adjust size of tile
    public final int tileSize = originalTileSize * scale; 

    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // change to adjust game resoulution

    final int screenWidth = tileSize * maxScreenCol; 
    final int screenHeight = tileSize * maxScreenRow; //makes 768x576 game panel

    int fps = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);

    // Default player Position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel()  {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //no idea what this does but apparently improves performance
        this.addKeyListener(keyH);
        this.setFocusable(true); //allows window to be "focused" to receive input
    }

    public void startGameThread()  {

        gameThread = new Thread(this);
        gameThread.start(); //starts thread and calls run
    }

    @Override
    public void run()  {

        double drawInterval = 1000000000/fps; //1billion nano seconds in one second and we divide by our desired frames per second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)  {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval; //every loop we add last time to delta and when delta hits 1 it updates
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
            update();   //updates information like character location
            repaint();  //draws all the new information again
            delta --;
            drawCount ++;
            }

            if(timer >= 1000000000){
                System.out.println("fps: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update()  {

       player.update();
    }
    public void paintComponent(Graphics g)  {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }

}
