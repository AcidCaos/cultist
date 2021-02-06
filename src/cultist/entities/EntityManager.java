package cultist.entities;

import cultist.Handler;
import cultist.entities.creatures.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
    
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities; // includes the Player entity
    
    private Comparator<Entity> renderZBuffer = new Comparator<Entity>(){
        public int compare(Entity a, Entity b) {
            // -1 : a before b
            // +1 : a after  b
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) return -1;
            return +1;
        }
    };
    
    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.entities = new ArrayList<Entity>();
        this.player = player;
        this.entities.add(player);
    }
    
    public void tick() {
        //for(int i = 0; i < entities.size(); i++){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if (!e.exists()) it.remove(); // It died
        }
        entities.sort(renderZBuffer);
    }

    public void render(Graphics g) {
        for(Entity e : entities){
            e.render(g);
        }
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
}
