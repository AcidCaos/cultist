package cultist.gfx;

import cultist.utils.Utils;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    
    private BufferedImage sheet;
    private int spriteWidth;
    private int spriteHeight;
    
    public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight) {
        this.sheet = sheet;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }
    
    public void transparency(){
        Utils.replaceColor(sheet, new Color(214,127,255), new Color(0,0,0,0)); // Inside
        Utils.replaceColor(sheet, new Color(107,63,127), new Color(0,0,0,0)); // Border
    }
    
    public BufferedImage crop(int xTile, int yTile, int nTilesWidth, int nTilesHeight) {
        return sheet.getSubimage(xTile * spriteWidth, yTile * spriteHeight, nTilesWidth * spriteWidth, nTilesHeight * spriteHeight);
    }
}
