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
    
    public static BufferedImage[] hit_up;
    public static BufferedImage[] hit_down;
    public static BufferedImage[] hit_left;
    public static BufferedImage[] hit_right;
    
    // Tiles
    
    public static BufferedImage grass, red_grass, blue_grass;
    public static BufferedImage bricks;
    
    // Entities
    
    public static BufferedImage rock;
    public static BufferedImage tree, dead_tree;
    
    // Items
    
    public static BufferedImage wood_log;
    public static BufferedImage yellow_book;

    public static void init() {
        BufferedImage s = null;
        try { s = ImageIO.read(new File("res/sprites/sheet.png")); }
        catch (IOException ex) { System.exit(1); }
        sheet = new SpriteSheet(s, CELL_WIDTH, CELL_HEIGHT);
        sheet.transparency();
        
        // Animations
        
        player_down  = new BufferedImage[4];
        player_up    = new BufferedImage[4];
        player_left  = new BufferedImage[4];
        player_right = new BufferedImage[4];
        
        for (int i = 0; i < 4; i++)
            player_down[i] = sheet.crop(i, 0, 1,1);
        for (int i = 0; i < 4; i++)
            player_right[i] = sheet.crop(i, 1, 1,1);
        for (int i = 0; i < 4; i++)
            player_left[i] = sheet.crop(i, 2, 1,1);
        for (int i = 0; i < 4; i++)
            player_up[i] = sheet.crop(i, 3, 1,1);
        
        hit_up = new BufferedImage[4];
        hit_down = new BufferedImage[4];
        hit_left = new BufferedImage[4];
        hit_right = new BufferedImage[4];
        
        for (int i = 0; i < 4; i++)
            hit_up[i] = sheet.crop(8 + i, 3, 1,1);
        for (int i = 0; i < 4; i++)
            hit_down[i] = sheet.crop(8 + i, 0, 1,1);
        for (int i = 0; i < 4; i++)
            hit_left[i] = sheet.crop(8 + i, 2, 1,1);
        for (int i = 0; i < 4; i++)
            hit_right[i] = sheet.crop(8 + i, 1, 1,1);
        
        // Tiles
        
        grass = sheet.crop(0,4, 1,1);
        red_grass = sheet.crop(0,5, 1,1);
        blue_grass = sheet.crop(0,6, 1,1);
        
        bricks = sheet.crop(4,4, 1,1);
        
        // Entities
        
        rock = sheet.crop(2,8, 1,1);
        
        tree = sheet.crop(0, 8, 1, 2);
        dead_tree = sheet.crop(1, 8, 1, 2);
        
        // Items
        
        wood_log = sheet.crop(0, 10, 1,1);
        yellow_book = sheet.crop(2, 13, 1,1);
        
    }
}
