package cultist.worlds;

import cultist.Handler;
import cultist.data.Data;
import cultist.entities.Entity;
import cultist.entities.EntityManager;
import cultist.entities.creatures.Player;
import cultist.gfx.Font;
import cultist.inventory.Inventory;
import cultist.items.Item;
import cultist.items.ItemManager;
import cultist.screen.frames.FrameManager;
import cultist.tiles.Tile;
import java.awt.Graphics;
import java.util.ArrayList;

public class World {
    
    // Map and savedGame
    private String mapPath;
    private String savePath;
    
    
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tile_ids;
    
    // Entities
    private EntityManager entityManager;
    
    // Item
    private ItemManager itemManager;
    
    public World(Handler handler, String path) {
        this.handler = handler;
        this.mapPath = path;
        
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        itemManager = new ItemManager(handler);
        
        // loadMap();
        
    }
    
    public void loadMap(){
        loadWorldMap(mapPath);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    
    public void loadProgress(String path){
        this.savePath = path;
        loadWorldProgress(path);
    }
    
    public void saveProgress(){
        saveWorldProgress(savePath);
    }
    
    public void tick() {
        Tile.tickTiles();
        // Items
        itemManager.tick();
        // Entities (including player)
        entityManager.tick();

        handler.getGame().getCamera().centerOnEntity(entityManager.getPlayer());
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
        
        // Items below entities
        itemManager.render(g);
        // Entities
        entityManager.render(g);
        // Debug info
        if (handler.DEBUG) renderDebugInfo(g);
    }
    
    private void renderDebugInfo(Graphics g) {
        
        // Items
        for (Item i : getItemManager().getItems()) {
            i.renderPickBounds(g);
            i.renderItemInfo(g);
            
        }
        
        // Entities
        for (Entity e : getEntityManager().getEntities()) {
            e.renderHitbox(g);
            e.renderEntityInfo(g);
            
        }
        
        // Screen info
        Player player = handler.getWorld().getEntityManager().getPlayer();
        int playerCenterX = (int) player.getX() + (int) player.getWidth() / 2;
        int playerCenterY = (int) player.getY() + (int) player.getHeight() / 2;
        String playerPos = playerCenterX + "," + playerCenterY + " - (" + playerCenterX/8 + "," + playerCenterY/8 + ")";
        int health = (int) player.getHealth();
        int speed = (int) player.getSpeed();
        int strength = (int) player.getStrength();
        int attackRange = (int) player.getAttackRange();
        
        Font.render(g, "fps: " + handler.getFPS() + " tps: " + handler.getTPS() + " - show/hide debug: F3", 0, 0, 2, false);
        Font.render(g, playerPos, 0, 1*4, 2, false);
        
        Font.render(g, "health: " + health, 0, handler.getHeight() - 3*4, 2, false);
        Font.render(g, "speed: " + speed, 0, handler.getHeight() - 2*4, 2, false);
        Font.render(g, "attack: " + strength + "(r2: " + attackRange + ")", 0, handler.getHeight() - 1*4, 2, false);
        
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) return Tile.grass;
        Tile t = Tile.tiles[tile_ids[x][y]];
        if (t == null) return Tile.grass;
        return t;
    }
    
    private void loadWorldMap(String path) {
        
        Object[] ret = Data.loadMap(path, handler);
        width = (int) ret[0];
        height = (int) ret[1];
        spawnX = (int) ret[2];
        spawnY = (int) ret[3];
        tile_ids = (int[][]) ret[4];
        entityManager.setEntities( (ArrayList<Entity>) ret[5]);
        entityManager.addEntity(entityManager.getPlayer()); // The player is also an entity: must be added, or won't be ticked nor rendered
        
        // OLD CODE: (now is in 'Data'. Used in both World and Editor classes)
    }
    
    private void loadWorldProgress(String path){
        Object[] ret = Data.loadProgress(path, handler);
        
        Player player = (Player) ret[0];
        ArrayList<Entity> entities = (ArrayList<Entity>) ret[1];
        Inventory inventory = (Inventory) ret[2];
        
        entityManager.setEntities(entities);
        entityManager.setPlayer(player);
        entityManager.addEntity(entityManager.getPlayer()); // The player is also an entity: must be added, or won't be ticked nor rendered
        entityManager.getPlayer().setInventory(inventory);
        
    }
    
    private void saveWorldProgress(String path){
        int random_int = (int)(Math.random() * (99999 - 10000 + 1) + 10000);
        Data.renameFile(path, path + "-" + random_int + ".old");
        
        Object[] arg = new Object[]{entityManager.getPlayer(), entityManager.getEntities(), entityManager.getPlayer().getInventory().getInventoryItems()};
        Data.saveProgress(path, arg);
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
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public ItemManager getItemManager() {
        return itemManager;
    }
    
}
