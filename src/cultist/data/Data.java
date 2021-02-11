package cultist.data;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.entities.statics.Rock;
import cultist.entities.statics.StaticEntity;
import cultist.entities.statics.Tree;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Data {
    
    public static Object[] loadMap(String path, Handler handler) {
        
        // OBJECTS RETURNED
        int WIDTH, HEIGHT;
        int SPAWN_X, SPAWN_Y;
        int[][] TILE_IDS;
        ArrayList<Entity> ENTITIES = new ArrayList<>();
        
        
        System.out.println("Loading map from file : " + path + " ... ");
        
        String file = loadFileNoIntro(path);
        
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
        
        // TODO
        
        System.out.println("Done.");
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

}
