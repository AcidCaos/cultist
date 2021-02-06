package cultist.input;

import cultist.Handler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    
    private Handler handler;
    
    private boolean[] keys, justPressed, cantPress;
    
    public boolean any_key;
    
    public boolean up, left, down, right; // WASD
    public boolean inventory; // E
    public boolean interact; // F or SPACE
    public boolean attack; // Q or ENTER
    public boolean escape; // <esc>
    
    public InputHandler(Handler handler) {
        keys = new boolean[256];
        justPressed = new boolean[256];
        cantPress = new boolean[256];
        
        this.handler = handler;
    }
    
    public void tick() {
        
        for (int i = 0; i < justPressed.length; i++){
            if (cantPress[i] && !keys[i])
                cantPress[i] = false;
            else if (justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]) 
                justPressed[i] = true;
        }
        
        // MOVE
        
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        
        // ENTITY ACTIONS
        
        inventory = keys[KeyEvent.VK_E];
        interact = keys[KeyEvent.VK_F] || keys[KeyEvent.VK_SPACE];
        attack = keys[KeyEvent.VK_Q] || keys[KeyEvent.VK_ENTER];
        
        // GAME CONTROL
        
        escape = keys[KeyEvent.VK_ESCAPE];
        
        // DEBUG MODE
        
        if (keyJustPressed(KeyEvent.VK_F3)) handler.DEBUG = !handler.DEBUG;
        
    }

    public boolean keyJustPressed(int k) {
        return justPressed[k];
    }
    
    @Override
    public void keyPressed(KeyEvent k) {
        any_key = true;
        keys[k.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent k) {
        any_key = false;
        keys[k.getKeyCode()] = false;
    }
    
    @Override
    public void keyTyped(KeyEvent k) { }
    
    public void setKeyStat (int key, boolean stat) {
        keys[key] = stat;
    }
}
