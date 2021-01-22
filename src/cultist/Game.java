package cultist;

import cultist.display.Display;
import cultist.gfx.Assets;
import cultist.input.InputHandler;
import cultist.states.GameState;
import cultist.states.State;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    
    private static final int IDEAL_FPS = 120;
    private static final int IDEAL_TPS = 32;
    
    public String title;
    private int width, height;
    private Display display;
    private static final int SCALE = 3;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics2D g;
    
    private State gameState;
    private State menuState;
    
    private Handler handler;
    private InputHandler inputHandler;
    
    public Game(String title, int width, int height) {
        this.height = height * SCALE;
        this.width = width * SCALE;
        this.title = title;
        inputHandler = new InputHandler();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(inputHandler);
        Assets.init();
        
        handler = new Handler(this);
                
        gameState = new GameState(handler);
        //menuState = new MenuState(handler);
        State.setState(gameState);
    }
    
    private void tick() {
        inputHandler.tick();
        
        if (State.getState() != null) 
            State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = (Graphics2D) bs.getDrawGraphics();
        g.scale(SCALE, SCALE);
        g.setClip(0, 0, width / SCALE, height / SCALE);
        // Clear screen
        g.clearRect(0,0, width, height);
        
        // Draw here
        if (State.getState() != null)
            State.getState().render(g);
        
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / IDEAL_TPS;
        int frames = 0;
        int ticks = 0;
        long lastTimerMs = System.currentTimeMillis();
        int ms_sleep_time = 10;
        
        init();
        
        while(running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            
            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;
            }
            
            frames++;
            render();
            
            try {
                Thread.sleep(ms_sleep_time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (System.currentTimeMillis() - lastTimerMs > 1000) {
                lastTimerMs += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps, " + ms_sleep_time + " ms sleep");
                if (frames > IDEAL_FPS) ms_sleep_time ++;
                else if (frames < IDEAL_FPS*0.9) ms_sleep_time --;
                frames = 0;
                ticks = 0;
            }
        }
        
        stop();
        
    }
    
    public synchronized void start() {
       running = true;
       thread = new Thread(this);
       thread.start();
    }
    
    public synchronized void stop() {
        running = false;
        try { thread.join(); } catch (InterruptedException ex) { ex.printStackTrace(); }
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public InputHandler getInputHandler() {
        return inputHandler;
    }

}
