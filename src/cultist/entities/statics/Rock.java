package cultist.entities.statics;

import cultist.Handler;
import cultist.gfx.Assets;
import cultist.tiles.Tile;
import java.awt.Graphics;

public class Rock extends StaticEntity{

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        
        hitbox.x = 1;
        hitbox.y = 3;
        hitbox.width = 6;
        hitbox.height = 4;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }
    
    @Override
    public void interact() {
        
    }

    @Override
    public void die() {
        
    }
    
}
