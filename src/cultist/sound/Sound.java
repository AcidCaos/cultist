package cultist.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {
    public static final Sound pick_item = new Sound("/pick.wav");
    public static final Sound hit_break = new Sound("/break.wav");
    public static final Sound open_menu = new Sound("/pheew.wav");
    public static final Sound menu_select = new Sound("/menu.wav");
    public static final Sound nextScreen = new Sound("/screen.wav");
    
    // To play
    //Sound.test.play();

    private AudioClip clip;

    private Sound(String name) {
        URL url = null;
        try { url = new URL("file:res/sound" + name); } catch (MalformedURLException ex) { System.out.println("SOUND URL ERROR");}
        if (url != null) clip = Applet.newAudioClip(url);
        System.out.println("Sound Loaded");
    }
    
    public static void load() {
    }

    public void play() {
        new Thread() {
            public void run() {
                clip.play();
            }
        }.start();
    }
}
