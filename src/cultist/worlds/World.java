package cultist.worlds;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.entities.EntityManager;
import cultist.entities.creatures.Player;
import cultist.entities.statics.Rock;
import cultist.entities.statics.Tree;
import cultist.gfx.Font;
import cultist.items.Item;
import cultist.items.ItemManager;
import cultist.tiles.Tile;
import cultist.utils.Utils;
import java.awt.Graphics;

public class World {
    
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
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        itemManager = new ItemManager(handler);
        
        loadWorld(path);
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        
    }
    
    public void tick() {
        itemManager.tick();
        entityManager.tick();
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
    
    private void loadWorld(String path) {
        
        String file = Utils.loadFileNoIntro(path);
        
        String[] section = file.split(";\\s*\\[[a-zA-Z\\s]*\\]\\s*");
        System.out.println("Section len = " + section.length);
        String map_size = section[1];
        String player_spawn = section[2];
        String tiles = section[3];
        String entities = section[4];
        // String items = section[5];
        
        for (int i = 0; i < section.length; i++)
            System.out.println("==" + i +"============\n" + section[i]);
        
        // MAP SIZE
        String[] aux = map_size.split("\\s+");
        setWidth(Integer.parseInt(aux[0]));
        setHeight(Integer.parseInt(aux[1]));
        
        // PLAYER SPAWN
        aux = player_spawn.split("\\s+");
        spawnX = Integer.parseInt(aux[0]);
        spawnY = Integer.parseInt(aux[1]);
        
        // TILES
        tile_ids = new int[getWidth()][getHeight()];
        String[] array_ids = tiles.split("\\s+");
        for (int y = 0; y < getHeight(); y++)
            for (int x = 0; x < getWidth(); x++) {
                tile_ids[x][y] = Integer.parseInt(array_ids[(x + y * getWidth())]);
            }
        
        // ENTITIES
        String[] entity = entities.split("\\s+;\\s+");
        for (int i = 0; i < entity.length; i++) {
            String[] split = entity[i].split("\\s+");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            String ent = split[2];
            if      (ent.equals("tree")) entityManager.addEntity(new Tree(handler, 8*x, 8*y, false));
            else if (ent.equals("dead_tree")) entityManager.addEntity(new Tree(handler, 8*x, 8*y, true));
            else if (ent.equals("rock")) entityManager.addEntity(new Rock(handler, 8*x, 8*y));
        }
        
        // ITEMS
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
