package cultist.entities.statics;

import cultist.Handler;
import cultist.gfx.Assets;
import cultist.tiles.Tile;
import java.awt.Graphics;

public class Tree extends StaticEntity{

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
        
        hitbox.x = 2;
        hitbox.y = 5;
        hitbox.width = 4;
        hitbox.height = 4;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getCamera().getxOffset()), (int) (y - handler.getCamera().getyOffset()), width, height, null);
    }

    @Override
    public void interact() {
        
    }

    @Override
    public void die() {
        
    }
    
}
