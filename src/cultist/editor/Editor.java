package cultist.editor;

import cultist.Handler;
import cultist.entities.EntityManager;
import cultist.entities.creatures.Player;
import cultist.entities.statics.Rock;
import cultist.entities.statics.Tree;
import cultist.gfx.Font;
import cultist.items.ItemManager;
import cultist.screen.Screen;
import cultist.tiles.Tile;
import cultist.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Editor {
    
    String mapPath;
    
    private int CAMERA_VEL = 3;
    
    private Handler handler;
    private int mapWidth, mapHeight;
    private int spawnX, spawnY;
    private int[][] tile_ids;
    
    // Entities
    private EntityManager entityManager;
    
    // Item
    private ItemManager itemManager;
    
    // Selectors
    enum Place {TILE, STATIC_ENTITIES, CREATURES, SPAWNPOINT}
    Place type_placing;
    int selected_tile_id;
    
    // EDITOR
    
    private int currentTileX = 0;
    private int currentTileY = 0;
    
    public Editor(Handler handler, String path) {
        this.mapPath = path;
        this.handler = handler;
        
        type_placing = Place.TILE;
        selected_tile_id = 1;
        
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        itemManager = new ItemManager(handler);
        
        loadWorld(path);
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        
    }
    
    public void tick() {
                
        // itemManager.tick();
        // entityManager.tick();
        
        float yOffset = handler.getGame().getCamera().getyOffset();
        float xOffset = handler.getGame().getCamera().getxOffset();
        
        // Camera movement
        
        if(handler.getInputHandler().up) handler.getGame().getCamera().setyOffset(yOffset - CAMERA_VEL);
        if(handler.getInputHandler().down) handler.getGame().getCamera().setyOffset(yOffset + CAMERA_VEL);
        if(handler.getInputHandler().left) handler.getGame().getCamera().setxOffset(xOffset - CAMERA_VEL);
        if(handler.getInputHandler().right) handler.getGame().getCamera().setxOffset(xOffset + CAMERA_VEL);
        
        // Check if change type of placement
        
        if      (handler.getInputHandler().keyJustPressed(KeyEvent.VK_T)) type_placing = Place.TILE;
        else if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_E)) type_placing = Place.STATIC_ENTITIES;
        else if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_P)) type_placing = Place.SPAWNPOINT;
        else if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_C)) type_placing = Place.CREATURES;
        
        // Check if change thing to place
        
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ADD) || handler.getInputHandler().keyJustPressed(KeyEvent.VK_RIGHT)){
            if      (type_placing == Place.TILE) selected_tile_id = (selected_tile_id + 1) % Tile.getSize(); // Tile.getSize() = 256
            else if (type_placing == Place.STATIC_ENTITIES) ;
            else if (type_placing == Place.SPAWNPOINT) ;
            else if (type_placing == Place.CREATURES) ;
        }
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_SUBTRACT) || handler.getInputHandler().keyJustPressed(KeyEvent.VK_LEFT)){
            if      (type_placing == Place.TILE) selected_tile_id = (selected_tile_id + Tile.getSize() - 1) % Tile.getSize();
            else if (type_placing == Place.STATIC_ENTITIES) ;
            else if (type_placing == Place.SPAWNPOINT) ;
            else if (type_placing == Place.CREATURES) ;
        }
        
        // Check Mouse position
        
        yOffset = handler.getGame().getCamera().getyOffset();
        xOffset = handler.getGame().getCamera().getxOffset();
        
        int mousePosX_inGamePixels = handler.getMouseHandler().getMouseX() / handler.getGame().getScale();
        int mousePosY_inGamePixels = handler.getMouseHandler().getMouseY() / handler.getGame().getScale();
        
        // Get TILE pointed by the mouse 
        
        currentTileX = (mousePosX_inGamePixels + (int)xOffset) / Tile.TILEWIDTH;
        currentTileY = (mousePosY_inGamePixels + (int)yOffset) / Tile.TILEHEIGHT;
        
        if (currentTileX < 0) currentTileX = 0;
        if (currentTileY < 0) currentTileY = 0;
        
        if (currentTileX >= mapWidth) currentTileX = mapWidth - 1;
        if (currentTileY >= mapHeight) currentTileY = mapHeight - 1;
        
        // Check if Save button
        
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            saveWorld();
            Screen.setScreen(handler.getGame().homeScreen);
        }
        
        checkMouseButtonClicked();
    }
    
    private void checkMouseButtonClicked() {
        if (handler.getMouseHandler().isLeftPressed()) {
            
            if (type_placing == Place.TILE) {
                Tile selectedTile = Tile.byId(selected_tile_id);
                if (selectedTile != null) tile_ids[currentTileX][currentTileY] = selected_tile_id;
            }
            else if (type_placing == Place.STATIC_ENTITIES) {
                
            }
            else if (type_placing == Place.SPAWNPOINT) {
                
            }
            else if (type_placing == Place.CREATURES) {
                
            }
        }
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
        
        renderMouseSelect(g);
        
        // Debug info
        renderEditorInfo(g);
    }
    
    private void renderMouseSelect(Graphics g) {
        
        Font.render(g, "point: " + currentTileX + ", " + currentTileY, 0, 4, 2, false);
        
        g.setColor(Color.white);
        g.drawRect(Tile.TILEWIDTH * currentTileX - (int) handler.getCamera().getxOffset(), Tile.TILEHEIGHT * currentTileY - (int) handler.getCamera().getyOffset(), Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }
    
    private void renderEditorInfo(Graphics g) {
        
        // Top left
        Font.render(g, "fps: " + handler.getFPS() + " tps: " + handler.getTPS(), 0, 0, 2, false);
        
        
        // Top right (selector)
        g.setColor(Color.black);
        g.fillRect(handler.getWidth() - 30, 0, 30, 20);
        
        String current = "";
        if (type_placing == Place.TILE) current = "T";
        else if (type_placing == Place.STATIC_ENTITIES) current = "E";
        else if (type_placing == Place.SPAWNPOINT) current = "P";
        else if (type_placing == Place.CREATURES) current = "C";
        
        Font.render(g, current + ":", handler.getWidth() - 24, 5, 2, false);
        if (type_placing == Place.TILE) {
            Tile selectedTile = Tile.byId(selected_tile_id);
            if (selectedTile != null) selectedTile.render(g, handler.getWidth() - 10, 3 );
            else Font.render(g, "?", handler.getWidth() - 10, 3, 1, false);
            Font.render(g, "id=" + selected_tile_id, handler.getWidth() - 26, 14, 3, false);
        }
        else if (type_placing == Place.STATIC_ENTITIES) {}
        else if (type_placing == Place.SPAWNPOINT) {}
        else if (type_placing == Place.CREATURES) {}

        // Bottom
        
        Font.render(g, "(" + current + ") T:tile E:entity P:spawn C:creature  ", 0, handler.getHeight() - 4, 2, false);
        
        
    }
    
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) return Tile.grass;
        Tile t = Tile.tiles[tile_ids[x][y]];
        if (t == null) return Tile.grass;
        return t;
    }
    
    private void loadWorld(String path) {
        
        System.out.print("Loading map from file : " + path + " ... ");
        
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
        
        System.out.println("Done.");
    }
    
    private void saveWorld() {
        String savePath = "new-" + mapPath;
        System.out.print("Saving map to file : " + savePath + " ... ");
        System.out.println("Done.");
    }

    
    
    
    
    
    
    
    
    public int getWidth() {
        return mapWidth;
    }

    public void setWidth(int width) {
        this.mapWidth = width;
    }

    public int getHeight() {
        return mapHeight;
    }

    public void setHeight(int height) {
        this.mapHeight = height;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public ItemManager getItemManager() {
        return itemManager;
    }
    
}
