package cultist.worlds;

import cultist.Handler;
import cultist.tiles.Tile;
import cultist.utils.Utils;
import java.awt.Graphics;

public class World {
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tile_ids;
    
    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        int x_start = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH);
        int x_end = (int) Math.min(getWidth(), (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int y_start = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT);
        int Y_end = (int) Math.min(getHeight(), (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        
        for (int y = y_start; y < Y_end; y++)
            for (int x = x_start; x < x_end; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getCamera().getyOffset()));
            }
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) return Tile.grass;
        Tile t = Tile.tiles[tile_ids[x][y]];
        if (t == null) return Tile.grass;
        return t;
    }
    
    private void loadWorld(String path) {
        
        String file = Utils.loadFileNoIntro(path);
        String[] parts = file.split("\\s+");
        
        setWidth(Integer.parseInt(parts[0]));
        setHeight(Integer.parseInt(parts[1]));
        spawnX = Integer.parseInt(parts[2]);
        spawnY = Integer.parseInt(parts[3]);
        
        tile_ids = new int[getWidth()][getHeight()];
        
        for (int y = 0; y < getHeight(); y++)
            for (int x = 0; x < getWidth(); x++) {
                tile_ids[x][y] = Integer.parseInt(parts[ 4 + (x + y * getWidth())]);
            }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
}
