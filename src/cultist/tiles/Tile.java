package cultist.tiles;

import cultist.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    // Static
    
    public static Tile[] tiles = new Tile[256];
    
    public static Tile grass = new FloorTile(0, Assets.grass);
    public static Tile grass_bush = new FloorTile(1, Assets.grass_bush);
    public static Tile grass_flower = new FloorTile(2, Assets.grass_flower);
    public static Tile grass_plant = new FloorTile(3, Assets.grass_plant);
    
    public static Tile red_grass = new FloorTile(4, Assets.red_grass);
    public static Tile red_grass_bush = new FloorTile(5, Assets.red_grass_bush);
    public static Tile red_grass_flower = new FloorTile(6, Assets.red_grass_flower);
    public static Tile red_grass_plant = new FloorTile(7, Assets.red_grass_plant);
    
    public static Tile blue_grass = new FloorTile(8, Assets.blue_grass);
    public static Tile blue_grass_bush = new FloorTile(9, Assets.blue_grass_bush);
    public static Tile blue_grass_flower = new FloorTile(10, Assets.blue_grass_flower);
    public static Tile blue_grass_plant = new FloorTile(11, Assets.blue_grass_plant);
    
    public static Tile cliff_Q = new SolidTile(12, Assets.cliff_Q);
    public static Tile cliff_W = new SolidTile(13, Assets.cliff_W);
    public static Tile cliff_E = new SolidTile(14, Assets.cliff_E);
    public static Tile cliff_D = new SolidTile(15, Assets.cliff_D);
    public static Tile cliff_C = new SolidTile(16, Assets.cliff_C);
    public static Tile cliff_X = new SolidTile(17, Assets.cliff_X);
    public static Tile cliff_Z = new SolidTile(18, Assets.cliff_Z);
    public static Tile cliff_A = new SolidTile(19, Assets.cliff_A);
    public static Tile cliff_S = new SolidTile(20, Assets.cliff_S);
    public static Tile cliff_floor = new FloorTile(21, Assets.cliff_floor);
    public static Tile cliff = new SolidTile(22, Assets.cliff);
    public static Tile cliff_alt = new SolidTile(23, Assets.cliff_alt);
    
    public static Tile water = new WaterTile(24, Assets.water);
    
    public static Tile fence_Q = new SolidTile(25, Assets.fence_Q);
    public static Tile fence_W = new SolidTile(26, Assets.fence_W);
    public static Tile fence_E = new SolidTile(27, Assets.fence_E);
    public static Tile fence_hold = new SolidTile(28, Assets.fence_hold);
    public static Tile fence_C = new SolidTile(29, Assets.fence_C);
    public static Tile fence_broken = new SolidTile(30, Assets.fence_broken);
    public static Tile fence_Z = new SolidTile(31, Assets.fence_Z);
    public static Tile fence_A = new SolidTile(32, Assets.fence_A);
    public static Tile fence_alone = new SolidTile(33, Assets.fence_alone);
    
    public static Tile grass_path_init_L = new FloorTile(34, Assets.grass_path_init_L);
    public static Tile grass_path_init_R = new FloorTile(35, Assets.grass_path_init_R);
    public static Tile grass_path_init_U = new FloorTile(36, Assets.grass_path_init_U);
    public static Tile grass_path_init_D = new FloorTile(37, Assets.grass_path_init_D);
    public static Tile grass_path_V = new FloorTile(38, Assets.grass_path_V);
    public static Tile grass_path_H = new FloorTile(39, Assets.grass_path_H);
    public static Tile grass_path_turn_Q = new FloorTile(40, Assets.grass_path_turn_Q);
    public static Tile grass_path_turn_W = new FloorTile(41, Assets.grass_path_turn_W);
    public static Tile grass_path_turn_S = new FloorTile(42, Assets.grass_path_turn_S);
    public static Tile grass_path_turn_A = new FloorTile(43, Assets.grass_path_turn_A);
    public static Tile grass_path_T_U = new FloorTile(44, Assets.grass_path_T_U);
    public static Tile grass_path_T_D = new FloorTile(45, Assets.grass_path_T_D);
    public static Tile grass_path_T_L = new FloorTile(46, Assets.grass_path_T_L);
    public static Tile grass_path_T_R = new FloorTile(47, Assets.grass_path_T_R);
    public static Tile grass_path_cross = new FloorTile(48, Assets.grass_path_cross);
    
    /*public static Tile path = new FloorTile(9, Assets.grass);
    
    public static Tile gray_brick = new SolidTile(10, Assets.gray_bricks);
    public static Tile pink_brick = new SolidTile(11, Assets.pink_bricks);*/
    
    
    public static void tickTiles() {
        water.tick();
    }
    
    
    
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
