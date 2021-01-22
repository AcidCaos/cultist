package cultist.states;

import cultist.Handler;
import cultist.entities.creatures.Player;
import cultist.worlds.World;
import java.awt.Graphics;

public class GameScreen extends Screen {
    
    private Player player;
    private World world;
    
    public GameScreen(Handler handler){
        super(handler);
        
        world = new World(handler, "res/maps/map.txt");
        player = new Player(handler, 8, 8);
        
        handler.setWorld(world);
        //handler.getGame().getCamera().move(0, 0);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
        handler.getGame().getCamera().centerOnEntity(player);
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
