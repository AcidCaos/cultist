package cultist.gfx;

import cultist.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Font {
    
    private static final int CHARACTER_WIDTH = Assets.CELL_WIDTH;
    private static final int CHARACTER_HEIGHT = Assets.CELL_HEIGHT;
    
    private static String chars = "" +
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " +
        "0123456789.,!?'\"-+=/\\%()<>:;     ";
    
    private static void renderText(Graphics g, String msg, int x, int y, int size, boolean center, boolean setColor, Color textColor){
        int SHEET_FONT_Y = 30;
        int SHEET_FONT_WIDTH = Assets.SHEET_CELL_WIDTH;
        msg = msg.toUpperCase();
        //g.setColor(Color.red);
        for (int i = 0; i < msg.length(); i++) {
            int ix = chars.indexOf(msg.charAt(i));
            if (ix >= 0) {
                int xx = x + i * CHARACTER_WIDTH / size;
                if(center) xx = xx - (CHARACTER_WIDTH / size * msg.length())/2;
                BufferedImage character = Assets.sheet.crop(ix % SHEET_FONT_WIDTH, SHEET_FONT_Y + ix / SHEET_FONT_WIDTH, 1, 1);
                // Does may work if the color is set to black: it may break the background
                if(setColor && textColor != new Color(255,255,255)){ 
                    Utils.replaceColor(character, new Color(255,255,255), textColor);
                    g.drawImage( character,xx, y, CHARACTER_WIDTH / size, CHARACTER_HEIGHT / size, null);
                    Utils.replaceColor(character, textColor, new Color(255,255,255)); // Text back original color
                } else {
                    g.drawImage( character,xx, y, CHARACTER_WIDTH / size, CHARACTER_HEIGHT / size, null);
                }
            }
        }
    }
    
    public static void render(Graphics g, String msg, int x, int y, int size, boolean center) {
        renderText(g,msg,x,y,size,center,false, null);
    }
      
    public static void renderColor(Graphics g, String msg, int x, int y, int size, boolean center, Color c) {
        renderText(g,msg,x,y,size,center,true,c);
    }
}
