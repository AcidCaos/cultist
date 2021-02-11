package cultist.screen;

import cultist.Handler;
import cultist.inventory.Inventory;
import cultist.worlds.World;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameScreen extends Screen {
    
    private World world;
    
    public GameScreen(Handler handler){
        super(handler);
        
        world = new World(handler, "res/maps/map.txt");
        
        handler.setWorld(world);
        //handler.getGame().getCamera().move(0, 0);
    }

    @Override
    public void tick() {
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ESCAPE))
            Screen.setScreen(handler.getGame().escapeScreen);
        world.tick();
        
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_E)){
            Inventory aux = handler.getWorld().getEntityManager().getPlayer().getInventory();
            aux.setShown(!aux.getShown());
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    
    public World getWorld() {
        return world;
    }
}
