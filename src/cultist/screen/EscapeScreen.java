package cultist.screen;

import cultist.Handler;
import cultist.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EscapeScreen extends Screen {
      
    public EscapeScreen(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_ESCAPE))
            Screen.setScreen(handler.getGame().gameScreen);
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_Q))
            Screen.setScreen(handler.getGame().homeScreen);
    }

    @Override
    public void render(Graphics g) {
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font.render(g, "Escape Menu", centerX, centerY - 16, 1, true);
        Font.render(g, "<esc> to return to game", centerX, centerY + 8, 2, true);
        Font.render(g, "<q> to go to main screen", centerX, centerY + 16, 2, true);
    }
}
