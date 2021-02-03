package cultist.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    
    private boolean[] keys;
    public boolean up, down, left, right; // , attack, interact, menu;
    
    public boolean any_key;
    
    public InputHandler() {
        keys = new boolean[256];
    }
    
    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
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
