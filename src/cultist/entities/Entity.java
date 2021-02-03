package cultist.entities;

import cultist.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    
    protected Handler handler;
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle hitbox;
    
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        hitbox = new Rectangle(0, 0, width, height);
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);

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

}
