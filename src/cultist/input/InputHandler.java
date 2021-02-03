package cultist.input;

import cultist.Handler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    
    private Handler handler;
    
    private boolean[] keys;
    
    public boolean any_key;
    
    public boolean up, left, down, right; // WASD
    public boolean interact; // E or SPACE
    public boolean attack; // Q or ENTER
    public boolean escape; // <esc>
    
    public InputHandler(Handler handler) {
        keys = new boolean[256];
        this.handler = handler;
    }
    
    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        
        interact = keys[KeyEvent.VK_E] || keys[KeyEvent.VK_SPACE];
        attack = keys[KeyEvent.VK_Q] || keys[KeyEvent.VK_ENTER];
        escape = keys[KeyEvent.VK_ESCAPE];
        
        if (keys[KeyEvent.VK_F3]) {System.out.println("DEBUG ON"); handler.DEBUG = true;}
        if (keys[KeyEvent.VK_F4]) {System.out.println("DEBUG OFF"); handler.DEBUG = false;}
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
}
