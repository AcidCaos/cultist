package cultist.screen;

import cultist.Handler;
import cultist.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class HomeScreen extends Screen {
    
    public HomeScreen(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_P))
            Screen.setScreen(handler.getGame().gameScreen);
        if (handler.getInputHandler().keyJustPressed(KeyEvent.VK_E))
            Screen.setScreen(handler.getGame().editorScreen);
    }

    @Override
    public void render(Graphics g) {
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font.render(g, "Cultist", centerX, centerY - 16, 1, true);
        Font.render(g, "<p> to play", centerX, centerY + 8, 2, true);
        Font.render(g, "<e> to open map Editor", centerX, centerY + 16, 2, true);
        Font.render(g, "(c) AcidCaos 2021", centerX, handler.getHeight() - 8, 2, true);
    }
}
