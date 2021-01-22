package cultist.entities.creatures;

import cultist.Handler;
import cultist.gfx.Assets;
import java.awt.Graphics;

public class Player extends Creature{
    
    private Handler handler;
       
    public Player(Handler handler, int x, int y) {
        super(x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        this.handler = handler;
    }
    
    @Override
    public void tick() {
        if(handler.getGame().getInputHandler().up) setY(getY() - getSpeed());
        if(handler.getGame().getInputHandler().down) setY(getY() + getSpeed());
        if(handler.getGame().getInputHandler().left) setX(getX() - getSpeed());
        if(handler.getGame().getInputHandler().right) setX(getX() + getSpeed());
    }
    
    @Override
    public void render(Graphics g) {
        // System.out.println("tic tic" + x + " " + y);
        g.drawImage(Assets.player, (int) getX(), (int) getY(), getWidth(), getHeight(), null);
    }
}
