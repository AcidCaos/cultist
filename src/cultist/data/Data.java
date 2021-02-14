package cultist.data;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.entities.creatures.Player;
import cultist.entities.statics.StaticEntity;
import cultist.inventory.Inventory;
import cultist.items.Item;
import cultist.tiles.Tile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Data {
    
    // MAP
    
    public static Object[] loadMap(String path, Handler handler) {
        
        // OBJECTS RETURNED
        int WIDTH, HEIGHT;
        int SPAWN_X, SPAWN_Y;
        int[][] TILE_IDS;
        ArrayList<Entity> ENTITIES = new ArrayList<>();
        
        System.out.print("Loading map from file : " + path + " ... ");
        
        String file = loadFileNoIntro(path);
        
        String[] section = file.split(";\\s*\\[[a-zA-Z\\s]*\\]\\s*");
        //System.out.println("Section len = " + section.length);
        String map_size = section[1];
        String player_spawn = section[2];
        String tiles = section[3];
        String entities = section[4];
        // String items = section[5];
        
        //for (int i = 0; i < section.length; i++)
        //    System.out.println("==" + i +"============\n" + section[i]);
        
        // MAP SIZE
        String[] aux = map_size.split("\\s+");
        WIDTH = Integer.parseInt(aux[0]);
        HEIGHT = Integer.parseInt(aux[1]);
        
        // PLAYER SPAWN
        aux = player_spawn.split("\\s+");
        SPAWN_X = Integer.parseInt(aux[0]);
        SPAWN_Y = Integer.parseInt(aux[1]);
        
        // TILES
        TILE_IDS = new int[WIDTH][HEIGHT];
        String[] array_ids = tiles.split("\\s+");
        for (int y = 0; y < HEIGHT; y++)
            for (int x = 0; x < WIDTH; x++) {
                TILE_IDS[x][y] = Integer.parseInt(array_ids[(x + y * WIDTH)]);
            }
        
        // ENTITIES
        String[] entity = entities.split("\\s+;\\s+");
        for (int i = 0; i < entity.length; i++) {
            String[] split = entity[i].split("\\s+");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            String entityName = split[2];
            
            StaticEntity stEn = StaticEntity.getStaticEntityFromNameID(entityName, x, y, handler);
            ENTITIES.add(stEn);
            /*if      (entityName.equals("tree")) ENTITIES.add(new Tree(handler, 8*x, 8*y, false));
            else if (entityName.equals("dead_tree")) ENTITIES.add(new Tree(handler, 8*x, 8*y, true));
            else if (entityName.equals("rock")) ENTITIES.add(new Rock(handler, 8*x, 8*y));*/
        }
        
        System.out.println("Done.");
        
        return new Object[] {WIDTH, HEIGHT, SPAWN_X, SPAWN_Y, TILE_IDS, ENTITIES};
    }
    
    public static void saveMap(String path, Object[] param) {
        System.out.print("Saving map to file : " + path + " ... ");
        
        int WIDTH = (int) param[0];
        int HEIGHT = (int) param[1];
        int SPAWN_X = (int) param[2];
        int SPAWN_Y = (int) param[3];
        int[][] TILE_IDS = (int[][]) param[4];
        ArrayList<Entity> ENTITIES = (ArrayList<Entity>) param[5]; // Player is inside here. Should not be added in map save.
        
        String save = "";
        // map size
        save += "; [map size]\n";
        save += WIDTH + " " + HEIGHT + "\n";
        // player spawn
        save += "; [player spawn]\n";
        save += SPAWN_X + " " + SPAWN_Y + "\n";
        // tiles
        save += "; [tiles]\n";
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                save += TILE_IDS[x][y] + " ";
            }
            save += "\n";
        }
        // entities
        save += "; [entities]\n";
        for ( Entity e : ENTITIES)
            if (e.getClass() != Player.class)
                save += (int) e.getX() / Tile.TILEWIDTH + " " + (int) e.getY() / Tile.TILEHEIGHT + " " + StaticEntity.getNameIDFromNameIDFromStaticEntity( (StaticEntity)e ) + " ;\n";
        save += "; [items]\n";
        saveFile(path, save);        
        System.out.println("Done.");
    }
    
    // PLAYER PROGRESS
    
    public static Object[] loadProgress(String path, Handler handler){
        float PLAYER_X, PLAYER_Y;
        ArrayList<Entity> ENTITIES = new ArrayList<>();
        Inventory INVENTORY = new Inventory(handler);
        
        System.out.print("Loading progress from file : " + path + " ... ");
        
        String file = loadFileNoIntro(path);
        
        String[] section = file.split(";\\s*\\[[a-zA-Z\\s]*\\]\\s*");
        String player_position = section[1];
        String player_inventory = section[2];
        String entities = section[3];
        
        // PLAYER POSITION
        String[] aux = player_position.split("\\s+");
        PLAYER_X = Float.parseFloat(aux[0]);
        PLAYER_Y = Float.parseFloat(aux[1]);
        
        // MAP ENTITIES
        String[] entity = entities.split("\\s+;\\s+");
        for (int i = 0; i < entity.length; i++) {
            String[] split = entity[i].split("\\s+");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            String entityName = split[2];
            
            StaticEntity stEn = StaticEntity.getStaticEntityFromNameID(entityName, x, y, handler);
            ENTITIES.add(stEn);
        }
        
        // INVENTORY ITEMS
        if (player_inventory.contains(";")) { // Otherwise it's empty
            String[] item = player_inventory.split("\\s+;\\s+");
            for (int i = 0; i < item.length; i++) {
                String[] split = item[i].split("\\s+");
                int id = Integer.parseInt(split[0]);
                int quantity = Integer.parseInt(split[1]);

                INVENTORY.addItem(id, quantity);
            }
        }
        
        
        System.out.println("Done.");
        
        Player PLAYER = new Player(handler, (int) PLAYER_X, (int) PLAYER_Y);
        Object[] arg = new Object[]{PLAYER, ENTITIES, INVENTORY};
        return arg;
    }
    
    public static void saveProgress(String path, Object[] param) {
        Player PLAYER = (Player) param[0];
        ArrayList<Entity> ENTITIES = (ArrayList<Entity>) param[1];
        ArrayList<Item> INVENTORY_ITEMS = (ArrayList<Item>) param[2];
        
        
        float playerX = PLAYER.getX();
        float playerY = PLAYER.getY();
        
        
        String save = "";
        // player position
        save += "; [player float position]\n";
        save += playerX + " " + playerY + "\n";
        // player inventory TODO 
        save += "; [player inventory as id quantity]\n";
        for(Item i : INVENTORY_ITEMS){
            save += i.getId() + " " + i.getCount() + " ;\n";
        }
        // entities
        save += "; [entities]\n";
        for ( Entity e : ENTITIES)
            if (e.getClass() != Player.class)
                save += (int) e.getX() / Tile.TILEWIDTH + " " + (int) e.getY() / Tile.TILEHEIGHT + " " + StaticEntity.getNameIDFromNameIDFromStaticEntity( (StaticEntity)e ) + " ;\n";
        saveFile(path, save);
        System.out.println("Done.");
        
    }
    
    // FILES
    
    public static boolean existsDir(String dir) {
        File f = new File(dir);
        return f.exists();
    }
    
    public static boolean existsFile(String file) {
        File f = new File(file);
        return f.exists();
    }
    
    public static boolean deleteFile(String file) {
        File f = new File(file); 
        return f.delete();
    }
    
    public static boolean makeDir(String dir) {
        File f = new File(dir);
        return f.mkdir();
    }
    
    public static boolean renameFile(String o, String n) {
        File oldFile = new File(o);
        File newFile = new File(n);
        if (newFile.exists()) return false;
        return oldFile.renameTo(newFile);
    }
    
    // PRIVATE
    
    private static String loadFileNoIntro (String path) {
        String ret = "";
        Path pa = Path.of(path);
        try { ret = Files.readString(pa); } catch (IOException ex) { System.exit(1); }
        ret = ret.replace("\n", " ");
        ret = ret.replace("\r", " ");
        return ret;
    }
    
    private static void saveFile (String path, String content) {
        Path pa = Path.of(path);
        try {
            Files.writeString(pa, content);
        } catch (IOException ex) {}
    }
    
}
