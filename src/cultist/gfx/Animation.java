package cultist.gfx;

import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;
    
    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void tick(){
        long auxMilis = System.currentTimeMillis();
        timer += auxMilis - lastTime;
        lastTime = auxMilis;
        if (timer >= speed) {
            index = (index+1)%frames.length;
            timer = 0;
        }
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
