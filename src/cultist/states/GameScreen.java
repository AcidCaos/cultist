package cultist.states;

import cultist.Handler;
import cultist.worlds.World;
import java.awt.Graphics;

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
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
