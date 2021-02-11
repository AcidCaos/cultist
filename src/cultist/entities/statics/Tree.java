package cultist.entities.statics;

import cultist.Handler;
import cultist.gfx.Assets;
import cultist.items.Item;
import cultist.tiles.Tile;
import java.awt.Graphics;

public class Tree extends StaticEntity{
    
    private boolean isDead = false;
    
    public Tree(Handler handler, float x, float y, boolean dead) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
        this.isDead = dead;
        
        hitbox.x = 2;
        hitbox.y = 5;
        hitbox.width = 4;
        hitbox.height = 4;
    }
    
    public boolean isDead() {
        return isDead;
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(isDead ? Assets.dead_tree : Assets.tree, (int) (x - handler.getCamera().getxOffset()), (int) (y - Tile.TILEHEIGHT - handler.getCamera().getyOffset()), width, height, null);
    }

    @Override
    public void interact() {
        
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodLog.createNew((int)x, (int)y));
    }
    
}
