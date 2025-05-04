package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //lets it close
        window.setResizable(false); //disables changing window size
        window.setTitle("Tyler Blevins");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); //puts frame in middle
        window.setVisible(true);

        gamePanel.startGameThread();
        

    }

}
