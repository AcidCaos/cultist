package cultist.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Utils {
    
    public static void replaceColor(BufferedImage image, Color oldColor, Color newColor) {
        for (int y=0; y<image.getHeight(); y++)
            for (int x=0; x<image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                if (color == oldColor.getRGB()) image.setRGB(x, y, newColor.getRGB());
            }
    }
}
