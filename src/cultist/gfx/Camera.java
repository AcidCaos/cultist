package cultist.gfx;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.tiles.Tile;

public class Camera {
    
    private Handler handler;
    private float xOffset;
    private float yOffset;
    
    public Camera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void checkBlankSpace() {
        float x_max = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        if (xOffset < 0) {
            xOffset = 0;
        } else if(xOffset > x_max) {
            xOffset = x_max;
        }
        
        float y_max = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        if (yOffset < 0) {
            yOffset = 0;
        } else if(yOffset > y_max) {
            yOffset = y_max;
        }
    }
    
    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        checkBlankSpace();
    }
    
    public void move(float x, float y){
        xOffset += x;
        yOffset += y;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
}
