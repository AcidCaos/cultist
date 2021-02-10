package cultist.items;

import cultist.Handler;
import cultist.sound.Sound;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {
    
    private Handler handler;
    private ArrayList<Item> items;
    
    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }
    
    public void tick() {
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if (i.getToPickUp()) {
                Sound.pick_item.play();
                it.remove();
            }
        }
    }
    
    public void render(Graphics g) {
        for (Item i : items)
            i.render(g);
    }
    
    public void addItem(Item i) {
        i.setHandler(handler);
        items.add(i);
    }
    
    public Handler getHandler() {
        return handler;
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
}
