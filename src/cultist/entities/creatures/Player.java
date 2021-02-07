package cultist.entities.creatures;

import cultist.Handler;
import cultist.entities.Entity;
import cultist.gfx.Animation;
import cultist.gfx.Assets;
import cultist.inventory.Inventory;
import cultist.screen.Screen;
import cultist.sound.Sound;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature{
    
    // Animations
    private Animation downAnim, upAnim, leftAnim, rightAnim;
    
    // Attack
    private long attackCooldown = 800; // ms
    private long lastAttack;
    private long attackTimer = attackCooldown;
    
    // Inventory
    Inventory inventory;
    
    public Player(Handler handler, int x, int y) {
        super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
        
        hitbox.x = 1;
        hitbox.y = 0;
        hitbox.width = 6;
        hitbox.height = 8;
        
        downAnim = new Animation(500, Assets.player_down);
        upAnim = new Animation(500, Assets.player_up);
        leftAnim = new Animation(500, Assets.player_left);
        rightAnim = new Animation(500, Assets.player_right);
        
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
        
        // Move
        getKeyboardMovement();
        move();
        handler.getGame().getCamera().centerOnEntity(this);
        
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
        attackTimer += now - lastAttack;
        lastAttack = now;
        if (attackTimer < attackCooldown) return;
        
        if(handler.getInputHandler().attack){
            // Player looking up
            if(handler.getInputHandler().up){
                attackHitBox.x = playerHitBox.x + playerHitBox.width / 2 - getAttackRange() / 2;
                attackHitBox.y = playerHitBox.y - getAttackRange();
            }
            // Player looking down
            else if(handler.getInputHandler().down){
                attackHitBox.x = playerHitBox.x + playerHitBox.width / 2 - getAttackRange() / 2;
                attackHitBox.y = playerHitBox.y + playerHitBox.height;
            }
            // Player looking left
            else if(handler.getInputHandler().left){
                attackHitBox.x = playerHitBox.x - getAttackRange();
                attackHitBox.y = playerHitBox.y + playerHitBox.height / 2 - getAttackRange() / 2;
            }
            // Player looking right
            else if(handler.getInputHandler().right){
                attackHitBox.x = playerHitBox.x + playerHitBox.width;
                attackHitBox.y = playerHitBox.y + playerHitBox.height / 2 - getAttackRange() / 2;
            }
            else return; // not attacking 
            
            attackTimer = 0;
            
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
        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getCamera().getxOffset()),
                (int) (y - handler.getCamera().getyOffset()),
                getWidth(), getHeight(), null);
        inventory.render(g);
    }
    
    @Override
    public void renderEntityInfo(Graphics g) {
        
    }
    
    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0) return leftAnim.getCurrentFrame();
        else if (xMove > 0) return rightAnim.getCurrentFrame();
        else if (yMove < 0) return upAnim.getCurrentFrame();
        else if (yMove > 0) return downAnim.getCurrentFrame();
        return downAnim.getCurrentFrame();
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
    
}
