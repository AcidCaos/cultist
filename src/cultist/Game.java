package cultist;

import cultist.display.Display;
import cultist.entities.Entity;
import cultist.entities.creatures.Player;
import cultist.gfx.Assets;
import cultist.gfx.Camera;
import cultist.gfx.Font;
import cultist.input.InputHandler;
import cultist.states.GameScreen;
import cultist.states.HomeScreen;
import cultist.states.Screen;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    
    private static final int IDEAL_FPS = 120;
    private static final int IDEAL_TPS = 32;
    private int fps = 0, tps = 0;
    
    private static int SCALE;
    private static int INGAME_WIDTH, INGAME_HEIGHT; // in ingame pixels
    private static int CANVAS_WIDTH, CANVAS_HEIGHT; // in screen pixels
    
    public String title;
    private Display display;
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics2D g;
    
    public Screen gameScreen;
    public Screen homeScreen;
    
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
        
    }

    private void init() {
        handler = new Handler(this);
        inputHandler = new InputHandler(handler);
        
        display = new Display(title, CANVAS_WIDTH, CANVAS_HEIGHT);
        display.getFrame().addKeyListener(inputHandler);
        display.getCanvas().addKeyListener(inputHandler);
        Assets.init();
        
        camera = new Camera(handler, 0, 0);
        
        homeScreen = new HomeScreen(handler);
        gameScreen = new GameScreen(handler);
        Screen.setScreen(homeScreen);
    }
    
    private void tick() {
        inputHandler.tick();
        
        if (Screen.getScreen() != null) 
            Screen.getScreen().tick();
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
        //g.fillRect(0,0, INGAME_WIDTH, INGAME_HEIGHT);
        g.clearRect(0,0, INGAME_WIDTH, INGAME_HEIGHT);
        // Draw here
        if (Screen.getScreen() != null)
            Screen.getScreen().render(g);
        // Debug info
        if (handler.DEBUG) renderDebugInfo(g);
        g.dispose();
        bs.show();
    }
    
    private void renderDebugInfo(Graphics2D g) {
        
        // Entities
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            e.renderHitbox(g);
            e.renderEntityInfo(g);
            
        }
        
        // Screen info
        Player player = handler.getWorld().getEntityManager().getPlayer();
        int playerCenterX = (int) player.getX() + (int) player.getWidth() / 2;
        int playerCenterY = (int) player.getY() + (int) player.getHeight() / 2;
        String playerPos = playerCenterX + "," + playerCenterY + " - (" + playerCenterX/8 + "," + playerCenterY/8 + ")";
        int health = (int) player.getHealth();
        int speed = (int) player.getSpeed();
        int strength = (int) player.getStrength();
        int attackRange = (int) player.getAttackRange();
        
        Font.render(g, "fps: " + fps + " tps: " + tps + " - show/hide debug: F3/F4", 0, 0, 2, false);
        Font.render(g, playerPos, 0, 1*4, 2, false);
        
        Font.render(g, "health: " + health, 0, INGAME_HEIGHT - 3*4, 2, false);
        Font.render(g, "speed: " + speed, 0, INGAME_HEIGHT - 2*4, 2, false);
        Font.render(g, "attack: " + strength + "(r2: " + attackRange + ")", 0, INGAME_HEIGHT - 1*4, 2, false);
        
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
                fps = frames;
                tps = ticks;
                System.out.println(tps + " ticks, " + fps + " fps, " + ms_sleep_time + " ms sleep");
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
