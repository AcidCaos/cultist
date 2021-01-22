package cultist.gfx;

import cultist.Game;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Assets {
    
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    
    public static BufferedImage everisin, player;

    public static void init() {
        BufferedImage s = null;
        try { s = ImageIO.read(Game.class.getResource("/sheet.png")); } 
        catch (IOException ex) { System.exit(1); }
        SpriteSheet sheet = new SpriteSheet(s, WIDTH, HEIGHT);
        sheet.transparency();
        everisin = sheet.crop(0,0, 32,32);
        player = sheet.crop(0,0, 1,1);
        
    }
}
