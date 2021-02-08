package cultist.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    
    /*
    public static String loadFile (String path) {
        String ret = "";
        Path pa = Path.of(path);
        try { ret = Files.readString(pa); } catch (IOException ex) { System.exit(1); }
        return ret;
    }
    */
    
    public static String loadFileNoIntro (String path) {
        String ret = "";
        Path pa = Path.of(path);
        try { ret = Files.readString(pa); } catch (IOException ex) { System.exit(1); }
        ret = ret.replace("\n", " ");
        ret = ret.replace("\r", " ");
        return ret;
    }
}
