package org.sovon9.demo.entity;

import org.sovon9.demo.GamePanel;
import org.sovon9.demo.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends  Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        // gamePanel.screenWidth/2 gives center of the screen position for the player
        // but the 80x80 tiles top left corner position is the center so we need to subtract half tile size
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        // solid area inside player
        solidArea = new Rectangle(8,16, 32, 32);

        setDefaultValues();
        getPlayerImages();
    }
    public void setDefaultValues()
    {
        // default pixel positions on the screen where player image will appear when window open on game start
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        // moves player to player position +or- 4 positions
        speed = 4;
        direction = "up";
    }

    /**
     * loads player images
     */
    public void getPlayerImages()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/back1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/back2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/left1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/right1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/player/down2.png"));
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public void update()
    {
        if(keyHandler.keyW || keyHandler.keyA || keyHandler.keyS || keyHandler.keyD) {
            if (keyHandler.keyD) {

                direction = "right";
            } else if (keyHandler.keyW) {
                // cause here x,y=0,0 starts with top left corner of the screen so any y movement towards downwards in y increase
                direction = "up";
            } else if (keyHandler.keyA) {

                direction = "left";
            } else if (keyHandler.keyS) {

                direction = "down";
            }

            // collision checking
            collisionOn = false;
            gamePanel.checker.checkTile(this);
            // if collision is false player can move
            if(!collisionOn)
            {
                switch (direction)
                {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void paint(Graphics2D graphics2D)
    {
//        graphics2D.setColor(Color.GREEN);
//        graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage bufferedImage = null;
        switch(direction)
        {
            case "up":
                if (spriteNum == 1)
                    bufferedImage = up1;
                else
                    bufferedImage = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    bufferedImage = down1;
                else
                    bufferedImage = down2;
                break;
            case "left":
                if (spriteNum == 1)
                    bufferedImage = left1;
                else
                    bufferedImage = left2;
                break;
            case "right":
                if (spriteNum == 1)
                    bufferedImage = right1;
                else
                    bufferedImage = right2;
                break;
            default:
                break;
        }
        graphics2D.drawImage(bufferedImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
