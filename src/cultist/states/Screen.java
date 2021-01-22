package cultist.states;

import cultist.Handler;
import java.awt.Graphics;

public abstract class Screen {
    
    // Static
    private static Screen currentState = null;
    
    public static void setState(Screen state) {
        currentState = state;
    }
    
    public static Screen getState() {
        return currentState;
    }
    
    // Class
    
    protected Handler handler;
    
    public Screen(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();
    
    public abstract void render(Graphics g);
}
