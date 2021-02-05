package cultist.gfx;

import java.awt.Color;
import java.awt.Graphics;

public class Font {
    
    private static final int CHARACTER_WIDTH = Assets.CELL_WIDTH;
    private static final int CHARACTER_HEIGHT = Assets.CELL_HEIGHT;
    
    private static String chars = "" +
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " +
        "0123456789.,!?'\"-+=/\\%()<>:;     ";
    
    public static void render(Graphics g, String msg, int x, int y, int size, boolean center) {
        int SHEET_FONT_Y = 30;
        int SHEET_FONT_WIDTH = Assets.SHEET_CELL_WIDTH;
        msg = msg.toUpperCase();
        //g.setColor(Color.red);
        for (int i = 0; i < msg.length(); i++) {
            int ix = chars.indexOf(msg.charAt(i));
            if (ix >= 0) {
                int xx = x + i * CHARACTER_WIDTH / size;
                if(center) xx = xx - (CHARACTER_WIDTH / size * msg.length())/2;
                g.drawImage(Assets.sheet.crop(ix % SHEET_FONT_WIDTH, SHEET_FONT_Y + ix / SHEET_FONT_WIDTH, 1, 1),
                        xx, y, CHARACTER_WIDTH / size, CHARACTER_HEIGHT / size, null);
            }
        }
    }
    
    
}
