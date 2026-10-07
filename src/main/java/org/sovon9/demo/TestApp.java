package org.sovon9.demo;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

public class TestApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adv");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }

    public static void main(String[] args) {
        launch();
    }
}
