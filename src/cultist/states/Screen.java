package cultist.states;

import cultist.Handler;
import java.awt.Graphics;

public abstract class Screen {
    
    // Static
    private static Screen currentScreen = null;
    
    public static void setScreen(Screen state) {
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
