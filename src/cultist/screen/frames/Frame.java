package cultist.screen.frames;

import cultist.Handler;
import java.awt.Graphics;

public abstract class Frame {
    
    protected Handler handler;
    
    protected int x, y;
    protected int width, height;
    
    public Frame(Handler handler, int x, int y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
}
