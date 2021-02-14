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
    
    public static BufferedImage grass, grass_bush, grass_flower, grass_plant;
    public static BufferedImage red_grass, red_grass_bush, red_grass_flower, red_grass_plant;
    public static BufferedImage blue_grass, blue_grass_bush, blue_grass_flower, blue_grass_plant;
    
    public static BufferedImage cliff_Q, cliff_W, cliff_E, cliff_D, cliff_C, cliff_X, cliff_Z, cliff_A, cliff_S, cliff_floor, cliff, cliff_alt;
    
    public static BufferedImage fence_Q, fence_W, fence_E, fence_hold, fence_C, fence_broken, fence_Z, fence_A, fence_alone;
    
    public static BufferedImage grass_path_init_L, grass_path_init_R, grass_path_init_U, grass_path_init_D;
    public static BufferedImage grass_path_V, grass_path_H;
    public static BufferedImage grass_path_T_U, grass_path_T_D, grass_path_T_L, grass_path_T_R;
    public static BufferedImage grass_path_turn_Q, grass_path_turn_W, grass_path_turn_S, grass_path_turn_A;
    public static BufferedImage grass_path_cross;
    
    public static BufferedImage gray_bricks, pink_bricks;
    
    public static BufferedImage[] water;
    
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
        
        for (int i = 0; i < 4; i++) player_down[i] = sheet.crop(i, 0, 1,1);
        for (int i = 0; i < 4; i++) player_right[i] = sheet.crop(i, 1, 1,1);
        for (int i = 0; i < 4; i++) player_left[i] = sheet.crop(i, 2, 1,1);
        for (int i = 0; i < 4; i++) player_up[i] = sheet.crop(i, 3, 1,1);
        
        hit_up = new BufferedImage[4];
        hit_down = new BufferedImage[4];
        hit_left = new BufferedImage[4];
        hit_right = new BufferedImage[4];
        
        for (int i = 0; i < 4; i++) hit_up[i] = sheet.crop(8 + i, 3, 1,1);
        for (int i = 0; i < 4; i++) hit_down[i] = sheet.crop(8 + i, 0, 1,1);
        for (int i = 0; i < 4; i++) hit_left[i] = sheet.crop(8 + i, 2, 1,1);
        for (int i = 0; i < 4; i++) hit_right[i] = sheet.crop(8 + i, 1, 1,1);
        
        // Tiles
        
        grass = sheet.crop(0,4, 1,1);
        grass_bush = sheet.crop(1,4, 1,1);
        grass_flower = sheet.crop(2,4, 1,1);
        grass_plant = sheet.crop(3,4, 1,1);
        
        red_grass = sheet.crop(0,5, 1,1);
        red_grass_bush = sheet.crop(1,5, 1,1);
        red_grass_flower = sheet.crop(2,5, 1,1);
        red_grass_plant = sheet.crop(3,5, 1,1);
        
        blue_grass = sheet.crop(0,6, 1,1);
        blue_grass_bush = sheet.crop(1,6, 1,1);
        blue_grass_flower = sheet.crop(2,6, 1,1);
        blue_grass_plant = sheet.crop(3,6, 1,1);
        
        cliff_Q = sheet.crop(5,4, 1,1); // Q W E
        cliff_W = sheet.crop(6,4, 1,1); // A S D
        cliff_E = sheet.crop(7,4, 1,1); // Z X C
        cliff_D = sheet.crop(7,5, 1,1);
        cliff_C = sheet.crop(7,6, 1,1);
        cliff_X = sheet.crop(6,6, 1,1);
        cliff_Z = sheet.crop(5,6, 1,1);
        cliff_A = sheet.crop(5,5, 1,1);
        cliff_S = sheet.crop(6,5, 1,1);
        cliff_floor = sheet.crop(5,7, 1,1);
        cliff = sheet.crop(6,7, 1,1);
        cliff_alt = sheet.crop(7,7, 1,1);
        
        water = new BufferedImage[2];
        water[0] = sheet.crop(0,7, 1,1);
        water[1] = sheet.crop(1,7, 1,1);
        
        fence_Q = sheet.crop(9,4, 1,1);
        fence_W = sheet.crop(10,4, 1,1);
        fence_E = sheet.crop(11,4, 1,1);
        fence_hold = sheet.crop(11,5, 1,1);
        fence_C = sheet.crop(11,6, 1,1);
        fence_broken = sheet.crop(10,6, 1,1);
        fence_Z = sheet.crop(9,6, 1,1);
        fence_A = sheet.crop(9,5, 1,1);
        fence_alone = sheet.crop(10,5, 1,1);
        
        grass_path_init_L = sheet.crop(15,4, 1,1);
        grass_path_init_R = sheet.crop(14,6, 1,1);
        grass_path_init_U = sheet.crop(15,7, 1,1);
        grass_path_init_D = sheet.crop(15,5, 1,1);
        grass_path_V = sheet.crop(14,5, 1,1);
        grass_path_H = sheet.crop(13,4, 1,1);
        grass_path_turn_Q = sheet.crop(12,4, 1,1);
        grass_path_turn_W = sheet.crop(14,4, 1,1);
        grass_path_turn_S = sheet.crop(13,5, 1,1);
        grass_path_turn_A = sheet.crop(12,5, 1,1);
        grass_path_T_U = sheet.crop(12,6, 1,1);
        grass_path_T_D = sheet.crop(13,7, 1,1);
        grass_path_T_L = sheet.crop(12,7, 1,1);
        grass_path_T_R = sheet.crop(13,6, 1,1);
        grass_path_cross = sheet.crop(15,6, 1,1);
        
        
        gray_bricks = sheet.crop(4,4, 1,1);
        
        // Entities
        
        rock = sheet.crop(2,8, 1,1);
        
        tree = sheet.crop(0, 8, 1, 2);
        dead_tree = sheet.crop(1, 8, 1, 2);
        
        // Items
        
        wood_log = sheet.crop(0, 10, 1,1);
        yellow_book = sheet.crop(2, 13, 1,1);
        
    }
}
