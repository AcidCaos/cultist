package cultist.tiles;

import cultist.gfx.Animation;
import static cultist.tiles.Tile.TILEHEIGHT;
import static cultist.tiles.Tile.TILEWIDTH;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class WaterTile extends Tile{
    
    int anim_velocity_ms = 1500;
    Animation animation;
    
    public WaterTile(int id, BufferedImage[] textures) {
        super(id, textures[0]);
        animation = new Animation(anim_velocity_ms, textures);
    }
    
    @Override
    public boolean isSolid() {
        return true; // No swimming yet
    }
    
    @Override
    public void tick(){
        animation.tick();
    }
    
    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(animation.getCurrentFrame(), x, y, TILEWIDTH, TILEHEIGHT, null);
    }
}
