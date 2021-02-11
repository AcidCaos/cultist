package cultist.screen;

import cultist.Handler;
import cultist.data.Data;
import cultist.gfx.Font;
import cultist.screen.menu.Menu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class LoadSavedGameScreen extends Screen {
    
    Menu menu;
    
    String savePath = "saves/save.txt";
    
    public LoadSavedGameScreen(Handler handler){
        super(handler);
        //if (Data.existsFile(savePath))
            menu = new Menu(handler, new String[]{"Continue game", "New game", "Exit"});
        //else
            //menu = new Menu(handler, new String[]{"New game", "Exit"});
    }

    @Override
    public void tick() {
        menu.tick();

        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ENTER)){
            int index = menu.getIndex();
            // TODO
            if (index == 0){
                ( (GameScreen) handler.getGame().gameScreen ).getWorld().loadMap();
                ( (GameScreen) handler.getGame().gameScreen ).getWorld().loadProgress(savePath);
                Screen.setScreen(handler.getGame().gameScreen);
            }
            else if (index == 1){
                ( (GameScreen) handler.getGame().gameScreen ).getWorld().loadMap();
                Screen.setScreen(handler.getGame().gameScreen);
            }
            else if (index == 2){
                Screen.setScreen(handler.getGame().homeScreen);
            }
            menu.reset();
        }
    }

    @Override
    public void render(Graphics g) {
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font.render(g, "CULTIST", centerX, centerY - 35, 1, true);
        menu.render(g, centerX, centerY, 2, true);
    }
    
}
