package cultist.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

public class SpriteSheet {
    
    private BufferedImage sheet;
    private int spriteWidth;
    private int spriteHeight;
    
    public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight) {
        this.sheet = sheet;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }
    
    private static void replaceColor(BufferedImage image, Color oldColor, Color newColor) {
        for (int y=0; y<image.getHeight(); y++)
            for (int x=0; x<image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                if (color == oldColor.getRGB()) image.setRGB(x, y, newColor.getRGB());
            }
    }
    
    public void transparency(){
        replaceColor(sheet, new Color(214,127,255), new Color(0,0,0,0)); // Inside
        replaceColor(sheet, new Color(107,63,127), new Color(0,0,0,0)); // Border
    }
    
    public BufferedImage crop(int xTile, int yTile, int nTilesWidth, int nTilesHeight) {
        return sheet.getSubimage(xTile * spriteWidth, yTile * spriteHeight, nTilesWidth * spriteWidth, nTilesHeight * spriteHeight);
    }
}
