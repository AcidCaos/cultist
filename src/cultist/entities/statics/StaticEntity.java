package cultist.entities.statics;

import cultist.Handler;
import cultist.entities.Entity;

public abstract class StaticEntity extends Entity {
    
    public StaticEntity(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }
    
    public static String[] static_entities_names = {
        "tree", 
        "dead_tree",
        "rock"
    };
    
    public static StaticEntity getStaticEntityFromNameID(String ent, int x, int y, Handler handler){
        if      (ent.equals("tree")) return new Tree(handler, 8*x, 8*y, false);
        else if (ent.equals("dead_tree")) return new Tree(handler, 8*x, 8*y, true);
        else if (ent.equals("rock")) return new Rock(handler, 8*x, 8*y);
        System.out.println("SHOULD NOT HAPPEN. ent= " + ent);
        return null;
    }
    
    public static String getNameIDFromNameIDFromStaticEntity(StaticEntity ent){
        if      (ent.getClass() == Rock.class) return "rock";
        else if (ent.getClass() == Tree.class) { return ((Tree) ent).isDead() ? "dead_tree" : "tree";}
        System.out.println("SHOULD NOT HAPPEN");
        return null;
    }
    
    public static int getSize() {
        return static_entities_names.length;
    }
}
