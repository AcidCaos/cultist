package cultist;

import cultist.gfx.Camera;
import cultist.input.InputHandler;
import cultist.input.MouseHandler;
import cultist.worlds.World;

public class Handler {
    
    public boolean DEBUG = false;
    
    private Game game;
    private World world;
    
    public Handler(Game game){
        this.game = game;
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }
    
    public Game getGame(){
        return game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public World getWorld(){
        return world;
    }
    
    public void setWorld(World world){
        this.world = world;
    }
    
    public Camera getCamera(){
        return game.getCamera();
    }
    
    public InputHandler getInputHandler() {
        return game.getInputHandler();
    }
    
    public MouseHandler getMouseHandler() {
        return game.getMouseHandler();
    }
    
    public int getFPS() {
        return game.getFPS();
    }

    public int getTPS() {
        return game.getTPS();
    }
}
