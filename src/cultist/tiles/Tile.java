package cultist.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    // Static
    
    public static Tile[] tiles = new Tile[256];
    
    public static Tile grass = new GrassTile(0);
    public static Tile brick = new BrickTile(1);
    
    // Class
    
    public static final int TILEWIDTH = 8;
    public static final int TILEHEIGHT = 8;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick() {
    
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    public boolean isSolid(){
        return false;
    }
    
    public int getId(){
        return id;
    }
}
