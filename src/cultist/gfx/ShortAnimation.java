package cultist.gfx;

import java.awt.image.BufferedImage;

public class ShortAnimation extends Animation{
    
    enum Status {REST, PLAY}
    Status status;
    private static BufferedImage empty_frame;
    
    public ShortAnimation(int speed, BufferedImage[] frames){
        super(speed, frames);
        status = Status.REST;
        empty_frame = null;
    }
    
    @Override
    protected void setToNext() {
        if (status != Status.PLAY) return;
        if (timer >= speed) {
            index ++;
            timer = 0;
        }
        if (index >= frames.length) status = Status.REST;
    }
    
    @Override
    public BufferedImage getCurrentFrame(){
        if (status != Status.PLAY) return empty_frame;
        return frames[index];
    }
    
    public void playOnce(){
        status = Status.PLAY;
        index = 0;
    }
}
