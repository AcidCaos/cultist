package cultist.entities;

import cultist.Handler;
import cultist.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    
    public static final int DEFAULT_HEALTH = 10;
    
    protected Handler handler;
    protected float x;
    protected float y;
    protected int width, height;
    private int health;
    private boolean exists = true;
    protected Rectangle hitbox;
    
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;;
        
        hitbox = new Rectangle(0, 0, width, height);
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public void renderHitbox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int) (x + hitbox.x - handler.getCamera().getxOffset()),
                (int) (y + hitbox.y - handler.getCamera().getyOffset()),
                (int) (hitbox.width),
                (int) (hitbox.height));
    }
    
    public void renderEntityInfo(Graphics g) {
        Font.render(g,"health: " + health,(int) (x + width - handler.getCamera().getxOffset()),(int) (y + height / 2 - handler.getCamera().getyOffset()),  2, false);
    }

    public abstract void interact();
    
    public abstract void die();
    
    public void hurt(int amount){
        health -= amount;
        if (health <= 0) {
            exists = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this)) continue;
            if(e.getHitbox(0f, 0f).intersects(this.getHitbox(xOffset, yOffset)))
                return true;
        }
        return false; // No collision
    }
    
    public Rectangle getHitbox(float xOffset, float yOffset){
        return new Rectangle((int)(x + xOffset + hitbox.x),(int)(y + yOffset + hitbox.y), hitbox.width, hitbox.height);
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

}
