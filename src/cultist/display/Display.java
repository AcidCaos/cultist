package cultist.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    
    private JFrame frame;
    private Canvas canvas;
    
    private String title;
    private int width, height;
    
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        makeDisplay();
    }
    
    private void makeDisplay() {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        frame.add(getCanvas());
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
