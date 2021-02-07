package cultist.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {
    public static final Sound playerHurt = new Sound("/playerhurt.wav");
    public static final Sound playerDeath = new Sound("/death.wav");
    public static final Sound monsterHurt = new Sound("/monsterhurt.wav");
    public static final Sound test = new Sound("/test.wav");
    public static final Sound pickup = new Sound("/pickup.wav");
    public static final Sound bossdeath = new Sound("/bossdeath.wav");
    public static final Sound craft = new Sound("/craft.wav");
    
    // To play
    //Sound.test.play();

    private AudioClip clip;

    private Sound(String name) {
        URL url = null;
        try { url = new URL("file:res/sound" + name); } catch (MalformedURLException ex) { System.out.println("SOUND URL ERROR");}
        if (url != null) clip = Applet.newAudioClip(url);
    }

    public void play() {
        clip.play();
        new Thread() {
            public void run() {
                clip.play();
            }
        }.start();
    }
}
