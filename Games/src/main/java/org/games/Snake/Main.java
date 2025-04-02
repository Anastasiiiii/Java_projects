package org.games.Snake;

import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {
        final JFrame frame = new JFrame("Press Space Bar to Begin Game");
        frame.setSize(WIDTH, HEIGHT);
        SnakeGame game = new SnakeGame(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); //User cannot resize the frame

        frame.add(game);
        frame.setVisible(true);
        frame.pack(); //Resizes the frame to fit the preferred sizes and layouts of its components.
        game.startGame();
    }


}