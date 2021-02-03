package cultist.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Assets {
    
    public static final int CELL_WIDTH = 8; // in pixels
    public static final int CELL_HEIGHT = 8;
    
    public static final int SHEET_CELL_WIDTH = 32; // in cells
    public static final int SHEET_CELL_HEIGHT = 32;
    
    public static SpriteSheet sheet;
    
    // Animations
    
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    
    // Tiles
    
    public static BufferedImage grass, bricks;
    
    // Entities
    
    public static BufferedImage rock, tree;

    public static void init() {
        BufferedImage s = null;
        try { s = ImageIO.read(new File("res/sprites/sheet.png")); }
        catch (IOException ex) { System.exit(1); }
        sheet = new SpriteSheet(s, CELL_WIDTH, CELL_HEIGHT);
        sheet.transparency();
        
        // Animations
        
        player_down  = new BufferedImage[2];
        player_up    = new BufferedImage[2];
        player_left  = new BufferedImage[2];
        player_right = new BufferedImage[2];
        
        player_down[0] = sheet.crop(0,0, 1,1);
        player_down[1] = sheet.crop(0,1, 1,1);
        player_up[0] = sheet.crop(1,0, 1,1);
        player_up[1] = sheet.crop(1,1, 1,1);
        player_left[0] = sheet.crop(3,0, 1,1);
        player_left[1] = sheet.crop(3,1, 1,1);
        player_right[0] = sheet.crop(2,0, 1,1);
        player_right[1] = sheet.crop(2,1, 1,1);
        
        // Tiles
        
        grass = sheet.crop(0,4, 1,1);
        bricks = sheet.crop(1,4, 1,1);
        
        // Entities
        
        rock = sheet.crop(0,16, 1,1);
        tree = sheet.crop(0, 14, 1, 2);
        
    }
}
