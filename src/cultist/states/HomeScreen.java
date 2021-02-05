package cultist.states;

import cultist.Handler;
import cultist.gfx.Font;
import java.awt.Color;
import java.awt.Graphics;

public class HomeScreen extends Screen {
      
    public HomeScreen(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if (handler.getInputHandler().any_key)
            Screen.setScreen(handler.getGame().gameScreen);
    }

    @Override
    public void render(Graphics g) {
        int centerX = handler.getWidth() / 2;
        int centerY = handler.getHeight() / 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        Font.render(g, "Cultist", centerX, centerY - 16, 1, true);
        Font.render(g, "Press any key", centerX, centerY + 8, 1, true);
        Font.render(g, "to start", centerX, centerY + 16, 1, true);
        Font.render(g, "(c) AcidCaos 2021", centerX, handler.getHeight() - 8, 2, true);
    }
}
