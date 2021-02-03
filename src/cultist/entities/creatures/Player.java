package cultist.entities.creatures;

import cultist.Handler;
import cultist.gfx.Animation;
import cultist.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    
    // Animations
    private Animation downAnim, upAnim, leftAnim, rightAnim;
    
    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        
        hitbox.x = 1;
        hitbox.y = 0;
        hitbox.width = 6;
        hitbox.height = 8;
        
        downAnim = new Animation(500, Assets.player_down);
        upAnim = new Animation(500, Assets.player_up);
        leftAnim = new Animation(500, Assets.player_left);
        rightAnim = new Animation(500, Assets.player_right);
    }
    
    @Override
    public void tick() {
        // Animation
        downAnim.tick();
        upAnim.tick();
        leftAnim.tick();
        rightAnim.tick();
        
        // Move
        getKeyboard();
        move();
        handler.getGame().getCamera().centerOnEntity(this);
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
        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getCamera().getxOffset()),
                (int) (y - handler.getCamera().getyOffset()),
                getWidth(), getHeight(), null);
        
        if (handler.DEBUG) renderHitbox(g);
    }
    
    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) return leftAnim.getCurrentFrame();
        else if (xMove > 0) return rightAnim.getCurrentFrame();
        else if (yMove < 0) return upAnim.getCurrentFrame();
        else if (yMove > 0) return downAnim.getCurrentFrame();
        return downAnim.getCurrentFrame();
    }
}
