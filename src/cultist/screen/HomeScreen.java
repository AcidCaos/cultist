package cultist.screen;

import cultist.Handler;
import cultist.gfx.Font;
import cultist.screen.menu.Menu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class HomeScreen extends Screen {
    
    Menu menu;
    
    public HomeScreen(Handler handler){
        super(handler);
        menu = new Menu(handler, new String[]{"Play","Map Editor","Exit"});
    }

    @Override
    public void tick() {
        menu.tick();

        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ENTER)){
            int index = menu.getIndex();
            if      (index == 0) {
                Screen.setScreen(handler.getGame().loadSavedGameScreen);
            }
            else if (index == 1) {
                ( (EditorScreen) handler.getGame().editorScreen ).getEditor().loadMap();
                Screen.setScreen(handler.getGame().editorScreen);
            }
            else if (index == 2) System.exit(0);
            menu.reset();
        }
    }

    @Override
    public void render(Graphics g) {
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font.render(g, "Cultist", centerX, centerY - 35, 1, true);
        menu.render(g, centerX, centerY, 2, true);
        Font.render(g, "(c) AcidCaos 2021", centerX, handler.getHeight() - 8, 2, true);
    }
}
