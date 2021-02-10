package cultist.inventory;

import cultist.Handler;
import cultist.gfx.Assets;
import cultist.gfx.Font;
import cultist.items.Item;
import cultist.sound.Sound;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {
    
    private Handler handler;
    private boolean show = false;
    private ArrayList<Item> inventoryItems;
    
    public Inventory (Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
        
        // Testing inventory
        addItem(new Item (Assets.yellow_book, "Book1", 255));
        addItem(new Item (Assets.yellow_book, "Book2", 254));
        addItem(new Item (Assets.yellow_book, "Book3", 253));
    }
    
    public void tick(){
        if (!show) return;
    }
    
    public void render(Graphics g){
        if (!show) return;
        
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(centerX, 0, handler.getWidth(), 8*3);
        Font.render(g, "Inventory", centerX, 0, 1, false);
        for (int i=0; i<inventoryItems.size(); i++)
            Font.render(g, "> " + inventoryItems.get(i).getName() + " " + inventoryItems.get(i).getCount(), 
                        centerX + centerX / 2, 8+4*i, 2, true);
        
    }
    
    // Inventory methods
    
    public void addItem(Item item) {
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }
    
    // Open close inventory
    
    public boolean getShown() {
        return show;
    }
    
    public void setShown(boolean s) {
        this.show = s;
        Sound.open_menu.play();
    }
}
