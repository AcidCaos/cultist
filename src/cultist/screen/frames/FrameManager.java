package cultist.screen.frames;

import cultist.Handler;
import cultist.sound.Sound;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class FrameManager {
    
    private Handler handler;
    
    private Frame dynamicVisible;
    public InventoryFrame inventoryFrame;
    
    public FrameManager(Handler handler) {
        this.handler = handler;
        this.dynamicVisible = null;
        
        this.inventoryFrame = new InventoryFrame(handler);
    }
    
    public void tick() {
        
        // INVENTORY
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_E)){
            if(dynamicVisible == null) {
                dynamicVisible = inventoryFrame; 
                Sound.open_menu.play();
            } 
            else if(dynamicVisible == inventoryFrame) {
                dynamicVisible = null;
                Sound.open_menu.play();
            }
        }
        
        // TICK current
        if(dynamicVisible != null) dynamicVisible.tick();
    }

    public void render(Graphics g) {
        //inGameInfoFrame.render(g);
        // inventoryFrame.render(g);
        if(dynamicVisible != null) dynamicVisible.render(g);
    }

}
