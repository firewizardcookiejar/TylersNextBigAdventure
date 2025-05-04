package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SIZE
    final int originalTileSize = 16; //16x16 bit graphics
    final int scale = 3;  // <-- change this to adjust size of tile
    final int tileSize = originalTileSize * scale; 

    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // change to adjust game resoulution

    final int screenWidth = tileSize * maxScreenCol; 
    final int screenHeight = tileSize * maxScreenRow; //makes 768x576 game panel

    Thread gameThread;


    public GamePanel()  {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //no idea what this does but apparently improves performance
    }

    public void startGameThread()  {

        gameThread = new Thread(this);
        gameThread.start(); //starts thread and calls run
    }

    @Override
    public void run()  {

        while(gameThread != null)  {

            //UPDATE stuff like character position 
            update();

            //DRAW all stuff again :-) LOOOOP
            repaint(); //calls paintComponent
        }
    }
    public void update()  {

    }
    public void paintComponent(Graphics g)  {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();
    }

}
