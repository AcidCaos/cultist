package cultist.screen;

import cultist.Handler;
import cultist.editor.Editor;
import java.awt.Graphics;

public class EditorScreen extends Screen {
    
    private Editor editor;
    
    public EditorScreen(Handler handler){
        super(handler);
        
        editor = new Editor(handler, "res/maps/map.txt");
        
        //handler.setWorld(editor);
    }

    @Override
    public void tick() {
        editor.tick();
    }

    @Override
    public void render(Graphics g) {
        editor.render(g);
    }
}
