package org.sovon9.demo;

import org.sovon9.demo.entity.Player;
import org.sovon9.demo.tile.Tile;
import org.sovon9.demo.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; // means 16x16 is the pixels
    final int scale = 5;
    public final int tileSize = originalTileSize*scale; // 80x80
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1280 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 960

    // world settings
    public final int maxWorldRow = 50;
    public final int maxWorldCol = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    public CollisionChecker checker;

    public Thread gameThread;

    public KeyHandler keyHandler = new KeyHandler();

    public int playerPosX=100;
    public int playerPosY=100;
    public int speed = 4;

    public int FPS = 60;

    public Player player;

    public TileManager tileManager;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        //keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player = new Player(this, keyHandler);
        tileManager = new TileManager(this);
        checker = new CollisionChecker(this);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread!=null)
        {
            //System.out.println("While is running");
            //update info such as character position
            update();
            // draw the screen with updated info
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                double remainingTimeMiliSec = remainingTime/1000000;
                if(remainingTimeMiliSec>0)
                {
                    Thread.sleep((long) remainingTimeMiliSec);
                }
                nextDrawTime +=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * update info such as character position
     */
    public void update()
    {
//        if(keyHandler.keyD)
//        {
//            playerPosX+=speed;
//        }
//        else if(keyHandler.keyW)
//        {
//            playerPosY-=speed; // cause here x,y=0,0 starts with top left corner of the screen so any y movement towards downwards in y increase
//        }
//        else if(keyHandler.keyA)
//        {
//            playerPosX-=speed;
//        }
//        else if(keyHandler.keyS)
//        {
//            playerPosY+=speed;
//        }

        player.update();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
//        graphics2D.setColor(Color.GREEN);
//        graphics2D.fillRect(playerPosX, playerPosY, tileSize, tileSize);
        tileManager.paint(graphics2D);// drawing tile before player draw as otherwise it will cover player image
        player.paint(graphics2D); // drae the player
        graphics2D.dispose();
    }
}
