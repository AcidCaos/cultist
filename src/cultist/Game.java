package cultist;

import cultist.display.Display;
import cultist.gfx.Assets;
import cultist.gfx.Camera;
import cultist.input.InputHandler;
import cultist.states.GameScreen;
import cultist.states.Screen;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    
    private static final int IDEAL_FPS = 120;
    private static final int IDEAL_TPS = 32;
    
    private static int SCALE;
    private static int INGAME_WIDTH, INGAME_HEIGHT; // in ingame pixels
    private static int CANVAS_WIDTH, CANVAS_HEIGHT; // in screen pixels
    
    public String title;
    private Display display;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics2D g;
    
    private Screen gameScreen;
    private Screen menuScreen;
    
    private Handler handler;
    private InputHandler inputHandler;
    
    private Camera camera;
    
    public Game(String title, int ingame_width, int ingame_height, int scale) {
        this.SCALE = scale;
        this.INGAME_HEIGHT = ingame_height;
        this.INGAME_WIDTH = ingame_width;
        this.CANVAS_HEIGHT =  INGAME_HEIGHT * SCALE;
        this.CANVAS_WIDTH =  INGAME_WIDTH * SCALE;
        this.title = title;
        inputHandler = new InputHandler();
    }

    private void init() {
        display = new Display(title, CANVAS_WIDTH, CANVAS_HEIGHT);
        display.getFrame().addKeyListener(inputHandler);
        Assets.init();
        
        handler = new Handler(this);
        camera = new Camera(handler, 0, 0);
     
        gameScreen = new GameScreen(handler);
        //menuState = new MenuState(handler);
        Screen.setState(gameScreen);
    }
    
    private void tick() {
        inputHandler.tick();
        
        if (Screen.getState() != null) 
            Screen.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = (Graphics2D) bs.getDrawGraphics();
        g.scale(SCALE, SCALE);
        g.setClip(0, 0, INGAME_WIDTH, INGAME_HEIGHT);
        // Clear screen
        g.clearRect(0,0, INGAME_WIDTH, INGAME_HEIGHT);
        
        // Draw here
        if (Screen.getState() != null)
            Screen.getState().render(g);
        
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
        return INGAME_WIDTH;
    }

    public int getHeight() {
        return INGAME_HEIGHT;
    }
    
    public InputHandler getInputHandler() {
        return inputHandler;
    }
    
    public Camera getCamera() {
        return camera;
    }

}
