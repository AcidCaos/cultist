package cultist.screen;

import cultist.Handler;
import cultist.gfx.Font;
import cultist.screen.menu.Menu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EscapeScreen extends Screen {
    
    Menu menu;
    
    public EscapeScreen(Handler handler){
        super(handler);
        menu = new Menu(handler, new String[]{"Return to game", "Save game", "Save and Exit", "Exit"});
    }

    @Override
    public void tick() {
        menu.tick();
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ESCAPE)) 
            Screen.setScreen(handler.getGame().gameScreen);
        else if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ENTER)) {
            int index = menu.getIndex();
            if      (index == 0) 
                Screen.setScreen(handler.getGame().gameScreen, true);
            else if (index == 1) {
                saveGame();
                Screen.setScreen(handler.getGame().gameScreen, true);
            }
            else if (index == 2) {
                saveGame();
                Screen.setScreen(handler.getGame().homeScreen, true);
            }
            else if (index == 3) {
                Screen.setScreen(handler.getGame().homeScreen, true);
            }
            menu.reset();
        }
    }
    
    private void saveGame() {
        handler.getWorld().saveProgress();
    }

    @Override
    public void render(Graphics g) {
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font.render(g, "Escape Menu", centerX, centerY - 35, 1, true);
        menu.render(g, centerX, centerY, 2, true);
        //Font.render(g, "<esc> to return to game", centerX, centerY + 8, 2, true);
        //Font.render(g, "<q> to go to main screen", centerX, centerY + 16, 2, true);
    }
}
