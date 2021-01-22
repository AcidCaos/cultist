package cultist.states;

import cultist.Handler;
import cultist.entities.creatures.Player;
import cultist.worlds.World;
import java.awt.Graphics;

public class GameState extends State {
    
    private Player player;
    private World world;
    
    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/world/world.txt");
        handler.setWorld(world);
        player = new Player(handler, 8, 8);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
