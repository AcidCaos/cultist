package cultist.screen;

import cultist.Handler;
import cultist.sound.Sound;
import java.awt.Graphics;

public abstract class Screen {
    
    // Static
    private static Screen currentScreen = null;
    
    public static void setScreen(Screen state) {
        currentScreen = state;
    }
    
    public static void setScreen(Screen state, boolean sound) {
        if (sound) Sound.nextScreen.play();
        currentScreen = state;
    }
    
    public static Screen getScreen() {
        return currentScreen;
    }
    
    // Class
    
    protected Handler handler;
    
    public Screen(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();
    
    public abstract void render(Graphics g);
}
