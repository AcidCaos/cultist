package cultist.tiles;

import java.awt.image.BufferedImage;

public class SolidTile extends Tile{
    
    public SolidTile(int id, BufferedImage texture) {
        super(id, texture);
    }
    
    public boolean isSolid() {
        return true;
    }
}
