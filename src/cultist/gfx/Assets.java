package cultist.gfx;

import cultist.Game;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Assets {
    
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    
    public static BufferedImage grass, rock, tree;

    public static void init() {
        BufferedImage s = null;
        try { s = ImageIO.read(new File("res/sprites/sheet.png")); } 
        catch (IOException ex) { System.exit(1); }
        SpriteSheet sheet = new SpriteSheet(s, WIDTH, HEIGHT);
        sheet.transparency();
        
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
        
        grass = sheet.crop(0,4, 1,1);
        rock = sheet.crop(1,4, 1,1);
        tree = sheet.crop(0, 14, 1, 2);
        
    }
}
