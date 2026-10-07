```
public void setDefaultValues()
{

x = 100;
y = 100;

}
```
These 100, 100 coordinates are not referring to tile grid positions, but to pixel positions on the screen.

Understanding the 100,100 Position

Pixels vs Tiles:

A tile is 80x80 pixels, so when you talk about "tiles," you're referring to blocks of space that are 80 pixels wide and 80 pixels tall.

The player position (x = 100, y = 100) is in pixel coordinates, not tile coordinates.

So, when you set x = 100 and y = 100, the player is placed at the 100th pixel on the x-axis and the 100th pixel on the y-axis. This would be somewhere within the second tile (since 100 pixels is greater than 80 but less than 160).

**Tile-Based Movement**

When you move the player (using the keyHandler), you move the player by a fixed amount of pixels (in your case, 4 pixels per update), and each update could move the player by a fraction of the tile's size.

For example:

If the player presses the right arrow (keyD), the player will move 4 pixels to the right:
```
if (keyHandler.keyD) {
x += speed; // 4 pixels
}
```
If the player moves enough times, they’ll eventually move into the next tile (the next 80x80 pixel region).

**Tile-Based Coordinate System with Player Position:**
Tile Grid (80x80 pixels per tile)
```
+------------------+------------------+------------------+------------------+
|      Tile (0,0)  |      Tile (1,0)  |      Tile (2,0)  |      Tile (3,0)  |
|     0,0-79,79    |    80,0-159,79   |   160,0-239,79   |   240,0-319,79   |
+------------------+------------------+------------------+------------------+
|      Tile (0,1)  |      Tile (1,1)  |      Tile (2,1)  |      Tile (3,1)  |
|    0,80-79,159   |  80,80-159,159   | 160,80-239,159   | 240,80-319,159   |
+------------------+------------------+------------------+------------------+
|      Tile (0,2)  |      Tile (1,2)  |      Tile (2,2)  |      Tile (3,2)  |
|    0,160-79,239  |  80,160-159,239  | 160,160-239,239  | 240,160-319,239  |
+------------------+------------------+------------------+------------------+
```
