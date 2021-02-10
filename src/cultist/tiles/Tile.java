package cultist.tiles;

import cultist.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    // Static
    
    public static Tile[] tiles = new Tile[256];
    
    public static Tile grass = new FloorTile(0, Assets.grass);
    public static Tile red_grass = new FloorTile(1, Assets.red_grass);
    public static Tile blue_grass = new FloorTile(2, Assets.blue_grass);
    
    public static Tile path = new FloorTile(5, Assets.grass);
    
    public static Tile brick = new SolidTile(10, Assets.bricks);
    
    // Class
    
    public static final int TILEWIDTH = 8;
    public static final int TILEHEIGHT = 8;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(int id, BufferedImage texture) {
        this.id = id;
        this.texture = texture;
        
        this.texture = texture;
        
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
    
    public static Tile byId(int id){
        return tiles[id];
    }
    
    public static int getSize(){
        return tiles.length;
    }
}
