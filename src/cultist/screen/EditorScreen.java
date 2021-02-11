package cultist.screen;

import cultist.Handler;
import cultist.editor.Editor;
import java.awt.Graphics;

public class EditorScreen extends Screen {
    
    private Editor editor;
    private String mapPath = "res/maps/map.txt";
    
    public EditorScreen(Handler handler){
        super(handler);
        
        editor = new Editor(handler, mapPath);
        
    }

    @Override
    public void tick() {
        editor.tick();
    }

    @Override
    public void render(Graphics g) {
        editor.render(g);
    }
    
    public Editor getEditor(){
        return editor;
    }
}
