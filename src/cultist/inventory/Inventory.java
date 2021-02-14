package cultist.inventory;

import cultist.Handler;
import cultist.items.Item;
import cultist.sound.Sound;
import java.util.ArrayList;

public class Inventory {
    
    private Handler handler;
    private boolean show = false;
    private ArrayList<Item> inventoryItems;
    
    public Inventory (Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
        
        // Testing inventory
        //addItem(new Item (Assets.yellow_book, "Book1", 255));
        //addItem(new Item (Assets.yellow_book, "Book2", 254));
        //addItem(new Item (Assets.yellow_book, "Book3", 253));
    }
    
    // Tick and Render --> moved to frameManager

    // Inventory methods
    
    public void addItem(Item item) {
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        System.out.println("Picked new Item");
        inventoryItems.add(item);
    }
    
    public void addItem(int id, int quantity) {
        for(Item i : inventoryItems){
            if(i.getId() == id) {
                i.setCount(i.getCount() + quantity);
                return;
            }
        }
        Item item = Item.items[id].createNew();
        inventoryItems.add(item);
        item.setCount(quantity);
    }
    
    // Open close inventory
    
    public boolean getShown() {
        return show;
    }
    
    public void setShown(boolean s) {
        this.show = s;
        Sound.open_menu.play();
    }
    
    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }
    
    public void setInventoryItems(ArrayList<Item> inventory_items) {
        this.inventoryItems = inventoryItems;
    }
}
