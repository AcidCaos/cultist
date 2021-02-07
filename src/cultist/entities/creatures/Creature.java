package cultist.entities.creatures;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.tiles.Tile;

public abstract class Creature extends Entity{
    
    
    public static final float DEFAULT_SPEED = 2.0f;
    public static final int DEFAULT_WIDTH = 8;
    public static final int DEFAULT_HEIGHT = 8;
    
    // Movement
    protected float speed;
    protected float xMove, yMove;
    
    // Attack
    protected int attackRange = 8;
    protected int strength = 3;
    
    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    
    public void move() {
        if (!checkEntityCollisions(xMove, 0f))
            moveX();
        if (!checkEntityCollisions(0f, yMove))
            moveY();
    }
    
    public void moveX(){
        if(xMove > 0){ //Moving right
            int tx = (int) (x + xMove + hitbox.x + hitbox.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + hitbox.y) / Tile.TILEHEIGHT )
                    && !collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height - 1) / Tile.TILEHEIGHT )){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - hitbox.x - hitbox.width;
            }
        }else if (xMove < 0){ // Moving left
            int tx = (int) (x + xMove + hitbox.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + hitbox.y) / Tile.TILEHEIGHT )
                    && !collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height - 1) / Tile.TILEHEIGHT )){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - hitbox.x;
            }
        }
    }
    
    public void moveY(){
        if(yMove > 0){ //Moving down
            int ty = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + hitbox.x) / Tile.TILEWIDTH , ty)
                    && !collisionWithTile((int) (x + hitbox.x + hitbox.width - 1) / Tile.TILEWIDTH , ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - hitbox.y - hitbox.height;
            }
        }else if (yMove < 0){ // Moving up
            int ty = (int) (y + yMove + hitbox.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + hitbox.x) / Tile.TILEWIDTH , ty)
                    && !collisionWithTile((int) (x + hitbox.x + hitbox.width - 1) / Tile.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - hitbox.y;
            }
        }
    }
    
    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    
    
}
