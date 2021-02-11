package cultist.entities.creatures;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.gfx.Animation;
import cultist.gfx.Assets;
import cultist.gfx.ShortAnimation;
import cultist.inventory.Inventory;
import cultist.screen.Screen;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    
    enum Dir {UP, DOWN, LEFT, RIGHT}
    Dir lastAttackDir;
    
    // Animations
    int walk_animVel = 140;
    private final Animation downAnim, upAnim, leftAnim, rightAnim;
    
    int hit_animVel = 90;
    private final ShortAnimation hitUp, hitDown, hitLeft, hitRight;
    
    // Attack
    private long attackCooldown = 600; // ms
    private long lastAttack;
    private long attackTimer = attackCooldown;
    private boolean showHitAnimation_flag = false;
    
    //private int attackDirection = -1; // up=0 down=1 left=2 right=3
    
    // Inventory
    Inventory inventory;
    
    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        
        hitbox.x = 1;
        hitbox.y = 0;
        hitbox.width = 6;
        hitbox.height = 8;
        
        downAnim = new Animation(walk_animVel, Assets.player_down);
        upAnim = new Animation(walk_animVel, Assets.player_up);
        leftAnim = new Animation(walk_animVel, Assets.player_left);
        rightAnim = new Animation(walk_animVel, Assets.player_right);
        
        hitUp = new ShortAnimation(hit_animVel, Assets.hit_up);
        hitDown = new ShortAnimation(hit_animVel, Assets.hit_down);
        hitLeft = new ShortAnimation(hit_animVel, Assets.hit_left);
        hitRight = new ShortAnimation(hit_animVel, Assets.hit_right);
        
        inventory = new Inventory(handler);
        
        strength = 4;
    }
    
    @Override
    public void tick() {
        // Animation
        
        downAnim.tick();
        upAnim.tick();
        leftAnim.tick();
        rightAnim.tick();
        
        hitUp.tick();
        hitDown.tick();
        hitLeft.tick();
        hitRight.tick();
        
        // Move
        getKeyboardMovement();
        move();
        // handler.getGame().getCamera().centerOnEntity(this);
        
        // Attack
        getKeyboardAttack();
        
        // Interact
        
        // Inventory
        inventory.tick();
    }
    
    public void getKeyboardAttack() {
        Rectangle playerHitBox = getHitbox(0, 0);
        Rectangle attackHitBox = new Rectangle(getAttackRange(), getAttackRange());
        
        long now = System.currentTimeMillis();
        //boolean lastNotAttack;
        //if (attackTimer < attackCooldown)
        attackTimer += now - lastAttack;
        lastAttack = now;
        if (attackTimer < attackCooldown) return;
        
        showHitAnimation_flag = false;
        if(handler.getInputHandler().attack){
            
            System.out.println("Attack Click");
            
            attackTimer = 0;
            showHitAnimation_flag = true;
            
            // Player looking up
            if(handler.getInputHandler().up){
                attackHitBox.x = playerHitBox.x + playerHitBox.width / 2 - getAttackRange() / 2;
                attackHitBox.y = playerHitBox.y - getAttackRange();
                lastAttackDir = Dir.UP;
                
            }
            // Player looking down
            else if(handler.getInputHandler().down){
                attackHitBox.x = playerHitBox.x + playerHitBox.width / 2 - getAttackRange() / 2;
                attackHitBox.y = playerHitBox.y + playerHitBox.height;
                lastAttackDir = Dir.DOWN;
            }
            // Player looking left
            else if(handler.getInputHandler().left){
                attackHitBox.x = playerHitBox.x - getAttackRange();
                attackHitBox.y = playerHitBox.y + playerHitBox.height / 2 - getAttackRange() / 2;
                lastAttackDir = Dir.LEFT;
            }
            // Player looking right
            else if(handler.getInputHandler().right){
                attackHitBox.x = playerHitBox.x + playerHitBox.width;
                attackHitBox.y = playerHitBox.y + playerHitBox.height / 2 - getAttackRange() / 2;
                lastAttackDir = Dir.RIGHT;
            }
            else {
                return; // not attacking
            }
            
            // Check every entity in the game
            for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
                if (e.equals(this)) continue; // Do not attack the player
                if (attackHitBox.intersects(e.getHitbox(0, 0))) {
                    e.hurt(getStrength());
                    return; // hurt one entity at a time
                }
            }
        }
    }
    
    public void getKeyboardMovement() {
        xMove = 0;
        yMove = 0;
        
        if(handler.getGame().getInputHandler().up) yMove = -speed;
        if(handler.getGame().getInputHandler().down) yMove = +speed;
        if(handler.getGame().getInputHandler().left) xMove = -speed;
        if(handler.getGame().getInputHandler().right) xMove = +speed;
    }
    
    @Override
    public void render(Graphics g) {
        renderPlayerAnimation(g);
        renderHitAnimation(g);
        inventory.render(g);
        
    }
    
    private void renderPlayerAnimation(Graphics g) {
        BufferedImage currentFrame;
        if      (xMove < 0) currentFrame = leftAnim.getCurrentFrame();
        else if (xMove > 0) currentFrame = rightAnim.getCurrentFrame();
        else if (yMove < 0) currentFrame = upAnim.getCurrentFrame();
        else if (yMove > 0) currentFrame = downAnim.getCurrentFrame();
        else                currentFrame = downAnim.getCurrentFrame();     
        g.drawImage(currentFrame,
                (int) (x - handler.getCamera().getxOffset()),
                (int) (y - handler.getCamera().getyOffset()),
                getWidth(), getHeight(), null);
    }
    
    private void renderHitAnimation(Graphics g) {
        
        if (lastAttackDir == null) return;
        
        ShortAnimation anim = null;
        int ySum = 0;
        int xSum = 0;
        if      (lastAttackDir == Dir.UP)    {anim = hitUp; ySum = - 8;} // up=0 down=1 left=2 right=3
        else if (lastAttackDir == Dir.DOWN)  {anim = hitDown; ySum = + 8;}
        else if (lastAttackDir == Dir.LEFT)  {anim = hitLeft; xSum = - 8;}
        else if (lastAttackDir == Dir.RIGHT) {anim = hitRight; xSum = + 8;}
        
        if (showHitAnimation_flag) {
            anim.playOnce();
            showHitAnimation_flag = false;
        }
        
        BufferedImage frame = anim.getCurrentFrame();
        if (frame == null) return;
        
        g.drawImage(frame,
            (int) (x - handler.getCamera().getxOffset()) + xSum,
            (int) (y - handler.getCamera().getyOffset()) + ySum,
            getWidth(), getHeight(), null);

    }
    
    @Override
    public void renderEntityInfo(Graphics g) {
        // Do not show entity info for Player entity.
    }
    
    @Override
    public void interact() {
        // Should not happen 
    }

    @Override
    public void die() {
        System.out.println("player died");
        Screen.setScreen(handler.getGame().homeScreen);
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
}
