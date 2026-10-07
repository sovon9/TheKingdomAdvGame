package org.sovon9.demo.tile;

import org.sovon9.demo.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tiles;

    // holds the tile numbers form the map file in a 2d array
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10]; // means we are going to create 10 types of tiles

        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        // loads the map array index file from map folder
        loadMap("/org/sovon9/demo/map/world.txt");
    }

    /**
     * method that loads tile images
     */
    public void getTileImage()
    {
        try
        {
            tiles[0]=new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/tiles/grass.png"));

            tiles[1]=new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/tiles/wall.png"));
            tiles[1].collision = true;

            tiles[2]=new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/tiles/water.png"));

            tiles[3]=new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/tiles/earth.png"));

            tiles[4]=new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/tiles/tree.png"));
            tiles[4].collision = true;

            tiles[5]=new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/org/sovon9/demo/tiles/sand.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * reading the map as a array index format where 0 means tile[0] image and respectively 1,2.. means other image tiles
     * @param path from where the map file needs to be loaded
     */
    public void loadMap(String path)
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int row =0;
            int col =0;
            while(row<gamePanel.maxWorldRow && col<gamePanel.maxWorldCol)
            {
                String line = reader.readLine();
                while(col<gamePanel.maxWorldCol)
                {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol)
                {
                    col=0;
                    row++;
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * draws the image in window using map tile numbers
     * @param graphics2D object from Gamepanel required to draw images in window
     */
    public void paint(Graphics2D graphics2D)
    {
        // row and column are the pointers which are incremented to traverse through the whole screen
        int worldRow=0;
        int worldColumn=0;

        while(worldRow<gamePanel.maxWorldRow && worldColumn<gamePanel.maxWorldCol)
        {
//            graphics2D.drawImage(tiles[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            int tileNum = mapTileNum[worldColumn][worldRow];

            int worldX = worldColumn * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX+gamePanel.tileSize> gamePanel.player.worldX-gamePanel.player.screenX && worldX-gamePanel.tileSize< gamePanel.player.worldX+gamePanel.player.screenX
                    && worldY+gamePanel.tileSize> gamePanel.player.worldY-gamePanel.player.screenY && worldY-gamePanel.tileSize< gamePanel.player.worldY+gamePanel.player.screenY) {
                graphics2D.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
            worldColumn++; // as we are painting towards right we are incrementing it
            if(worldColumn==gamePanel.maxWorldCol)
            {
                worldRow++; // need to start painting from next row
                worldColumn=0;
            }
        }
    }

}
