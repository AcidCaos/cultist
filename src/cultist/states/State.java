package cultist.states;

import cultist.Handler;
import java.awt.Graphics;

public abstract class State {
    
    private static State currentState = null;
    
    public static void setState(State state) {
        currentState = state;
    }
    
    public static State getState() {
        return currentState;
    }
    
    // Class
    
    public State(Handler handler) {
        
    }

    public abstract void tick();
    
    public abstract void render(Graphics g);
}
