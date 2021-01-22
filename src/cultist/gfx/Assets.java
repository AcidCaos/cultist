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
    
    public static BufferedImage player;
    
    public static BufferedImage grass, rock;

    public static void init() {
        BufferedImage s = null;
        /*try { s = ImageIO.read(Game.class.getResource("/sprites/sheet.png")); } 
        catch (IOException ex) { System.exit(1); }*/
        try { s = ImageIO.read(new File("res/sprites/sheet.png")); } 
        catch (IOException ex) { System.exit(1); }
        SpriteSheet sheet = new SpriteSheet(s, WIDTH, HEIGHT);
        sheet.transparency();
        
        player = sheet.crop(0,0, 1,1);
        
        grass = sheet.crop(0,4, 1,1);
        rock = sheet.crop(1,4, 1,1);
        
    }
}
