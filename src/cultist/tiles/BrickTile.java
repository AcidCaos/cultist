package cultist.tiles;

import cultist.gfx.Assets;

public class BrickTile extends Tile{
    
    public BrickTile(int id) {
        super(Assets.bricks, id);
    }
    
    public boolean isSolid() {
        return true;
    }
}
