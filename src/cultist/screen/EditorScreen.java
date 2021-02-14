package cultist.screen;

import cultist.Handler;
import cultist.editor.Editor;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EditorScreen extends Screen {
    
    private Editor editor;
    private String mapPath = "res/maps/map.txt";
    
    public EditorScreen(Handler handler){
        super(handler);
        
        editor = new Editor(handler, mapPath);
        
    }

    @Override
    public void tick() {
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            Screen.setScreen(handler.getGame().editorEscapeScreen, true);
        }
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
