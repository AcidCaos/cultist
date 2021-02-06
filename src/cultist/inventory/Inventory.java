package cultist.inventory;

import cultist.Handler;
import cultist.items.Item;
import java.awt.Graphics;
import java.util.ArrayList;

public class Inventory {
    
    private Handler handler;
    private boolean show = false;
    private ArrayList<Item> inventoryItems;
    
    public Inventory (Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
    }
    
    public void tick(){
        if (!show) return;
        System.out.println("INVENTORY: ");
        for (Item i : inventoryItems)
            System.out.println("> " + i.getName() + " " + i.getCount());
    }
    
    public void render(Graphics g){
        if (!show) return;
        
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
    }
}
