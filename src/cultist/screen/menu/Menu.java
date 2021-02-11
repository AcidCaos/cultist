package cultist.screen.menu;

import cultist.Handler;
import cultist.gfx.Font;
import cultist.sound.Sound;
import cultist.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Menu {
    
    Handler handler;
    
    String[] options;
    int width, height;
    int index;
    
    public Menu(Handler handler, String[] options){
        this.handler = handler;
        this.options = options;
        this.index = 0;
        this.height = Tile.TILEHEIGHT * options.length;
        int chars = -1;
        for (String o : options) if (o.length() > chars) chars = o.length();
        this.width = Tile.TILEWIDTH * chars;
    }
    
    public Menu(Handler handler, String[] options, int index, int height, int width){
        this(handler, options);
        this.index = index;
        this.height = height;
        this.width = width;
    }
    
    public void tick() {
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_UP) || handler.getInputHandler().keyJustPressed(KeyEvent.VK_W)){
            index = (index + options.length - 1) % options.length;
            Sound.menu_select.play();
        }
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_DOWN) || handler.getInputHandler().keyJustPressed(KeyEvent.VK_S)){
            index = (index + 1) % options.length;
            Sound.menu_select.play();
        }
    }
    
    public void render(Graphics g, int x, int y, int size, boolean center) {
        
        // Center
        int newX = x - (center ? (width / size)/2 + Tile.TILEWIDTH / size : 0);
        int newY = y - (center ? (height / size)/2 + Tile.TILEHEIGHT / size : 0);
        
        g.setColor(Color.gray);
        g.fillRect(newX, newY, width / size + 2 * Tile.TILEWIDTH / size, height / size + 2 * Tile.TILEHEIGHT / size);
        
        // Inside Margins
        newX = newX + Tile.TILEWIDTH / size;
        newY = newY + Tile.TILEHEIGHT / size;
        
        for (int i = 0; i < options.length; i++)
            Font.render(g, i == index ? ">" + options[i] + "<" : options[i], 
                    newX + (width / size)/2, 
                    newY + i * (Tile.TILEHEIGHT / size), 
                    size, true);
        
    }
    
    public int getIndex() {
        return index;
    }
    
    public void reset() {
        index = 0;
    }
    
}
