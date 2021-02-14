package cultist.screen.frames;

import cultist.Handler;
import cultist.gfx.Font;
import cultist.inventory.Inventory;
import cultist.items.Item;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class InventoryFrame extends Frame{
    
    public InventoryFrame (Handler handler){
        super( handler, 0, 0, 0, 0);
    }
    
    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        ArrayList<Item> inventoryItems = handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems();
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(centerX, 0, handler.getWidth(), 8*3);
        Font.render(g, "Inventory", centerX + 1, 0, 1, false);
        for (int i=0; i<inventoryItems.size(); i++)
            Font.render(g, "> " + inventoryItems.get(i).getName() + " " + inventoryItems.get(i).getCount(), 
                        centerX + centerX / 2, 8+4*i, 2, true);
    }
    
}
