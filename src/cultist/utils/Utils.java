package cultist.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    
    public static String loadFileNoIntro (String path) {
        String ret = "";
        Path pa = Path.of(path);
        try { ret = Files.readString(pa); } catch (IOException ex) { System.exit(1); }
        ret = ret.replace("\n", " ");
        ret = ret.replace("\r", " ");
        return ret;
    }
    
    public static void replaceColor(BufferedImage image, Color oldColor, Color newColor) {
        for (int y=0; y<image.getHeight(); y++)
            for (int x=0; x<image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                if (color == oldColor.getRGB()) image.setRGB(x, y, newColor.getRGB());
            }
    }
}
