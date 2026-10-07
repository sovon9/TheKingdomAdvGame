package org.sovon9.demo.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    // player position on world map
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
