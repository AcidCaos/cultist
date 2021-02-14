package cultist.screen;

import cultist.Handler;
import cultist.inventory.Inventory;
import cultist.screen.frames.FrameManager;
import cultist.worlds.World;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameScreen extends Screen {
    
    private World world;
    // Frame manager
    private FrameManager frameManager;
    
    public GameScreen(Handler handler){
        super(handler);
        
        world = new World(handler, "res/maps/map.txt");
        handler.setWorld(world);
        
        frameManager = new FrameManager(handler);
    }

    @Override
    public void tick() {
        
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ESCAPE))
            Screen.setScreen(handler.getGame().escapeScreen, true);
        
        world.tick();
        // in-game Frames
        frameManager.tick();
        
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        // in-game Frames
        frameManager.render(g);
    }
    
    public World getWorld() {
        return world;
    }
}
