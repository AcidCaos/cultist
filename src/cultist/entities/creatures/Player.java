package cultist.entities.creatures;

import cultist.Handler;
import cultist.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;

public class Player extends Creature{
    
    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        
        hitbox.x = 1;
        hitbox.y = 0;
        hitbox.width = 6;
        hitbox.height = 8;
    }
    
    @Override
    public void tick() {
        getKeyboard();
        move();
        // handler.getGame().getCamera().centerOnEntity(this);
    }
    
    public void getKeyboard() {
        xMove = 0;
        yMove = 0;
        
        if(handler.getGame().getInputHandler().up) yMove = -speed;
        if(handler.getGame().getInputHandler().down) yMove = +speed;
        if(handler.getGame().getInputHandler().left) xMove = -speed;
        if(handler.getGame().getInputHandler().right) xMove = +speed;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player,
                (int) (x - handler.getCamera().getxOffset()),
                (int) (y - handler.getCamera().getyOffset()),
                getWidth(), getHeight(), null);
        
        if (handler.DEBUG) {
            g.setColor(Color.red);
            g.drawRect((int) (x + hitbox.x - handler.getCamera().getxOffset()),
                    (int) (y + hitbox.y - handler.getCamera().getyOffset()),
                    (int) (hitbox.width),
                    (int) (hitbox.height));
        }
    }
}
