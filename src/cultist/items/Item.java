package cultist.items;

import cultist.Handler;
import cultist.gfx.Assets;
import cultist.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Item {
    
    // Static
    
    public static Item[] items = new Item[256];
    
    public static Item woodLog = new Item(Assets.wood_log, "Log", 0);
    
    // Class
    
    public static final int ITEMWIDTH = 8, ITEMHEIGHT = 8;
    public static final int PICK_RADIUS = 8;
    
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    
    protected Ellipse2D pickBounds; /*Rectangle*/
    
    protected int x;
    protected int y;
    protected int count;
    protected boolean to_pick_up = false;
    
    public Item (BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.count = 1;
        
        pickBounds = new Ellipse2D.Double((double) x + ITEMWIDTH / 2, (double) y + ITEMHEIGHT / 2, (double) PICK_RADIUS, (double) PICK_RADIUS);
        
        items[id] = this;
    }
    
    public void tick() {
        if (pickBounds.intersects(handler.getWorld().getEntityManager().getPlayer().getHitbox(0f, 0f))){
            to_pick_up = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }
    
    public void render(Graphics g) {
        if (getHandler() == null) return;
        render(g, (int) (getX() - getHandler().getCamera().getxOffset()),
                (int) (getY() - getHandler().getCamera().getyOffset()));
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }
    
    public void renderPickBounds(Graphics g) {
        g.setColor(Color.blue);
        g.drawOval((int) (x - handler.getCamera().getxOffset()),
                (int) (y - handler.getCamera().getyOffset()),
                PICK_RADIUS, PICK_RADIUS);
    }
    
    public void renderItemInfo(Graphics g) {
        Font.render(g,"name: " + name,(int) (x + ITEMWIDTH - handler.getCamera().getxOffset()),(int) (y + ITEMHEIGHT / 2 - handler.getCamera().getyOffset()),  2, false);
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        pickBounds = new Ellipse2D.Double((double) x, (double)y, (double) PICK_RADIUS, (double) PICK_RADIUS);
    }
    
    public Item createNew(int x, int y) {
        Item i = new Item (texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public boolean getToPickUp() {
        return to_pick_up;
    }
    
}
