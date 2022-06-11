import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BigDemon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BigDemon extends Enemy
{   
    public BigDemon(int health, double speed, double scale) {
        super(health, "big_demon", speed, scale);
    }
    
    public void act() {
        super.act();
        // move
        int t1 = 50;
        // spread attack
        int t2 = t1 + 30;
        int t3 = t2 + 5;
        // targeted attack
        int t4 = t3 + 80;
        int t5 = t4 + 50;
        // large size attack
        int t6 = t5 + 50;
        int t7 = t6 + 5;
        int x = actCount % (t7 + 20);
        
        if (x < t1) {
            move();
            runAnimate();
        } else {
            idleAnimate();
        }
        if (x > t2 && x < t3) {
            spreadAttack();
        }
        if (x > t4 && x < t5) {
            targetedAttack();
        }
        if (x > t6 && x < t7) {
            largeSizeAttack();
        }
    }
    
    public void move() {
        // moves towards player
        Player player = getWorld().getObjects(Player.class).get(0);
        turnTowards(player.getX(), player.getY());
        move(speed);
        
        // turn back
        setRotation(0);
    }
    
    public void spreadAttack() {
        // fires a bunch of fireballs in a circular pattern
        int turnAmount = 0;
        for (int i = 0; i < 12; i++) {
            BigDemonBall b = new BigDemonBall(3, 0.1, false);
            getWorld().addObject(b, getX(), getY());
            b.turn(turnAmount);
            turnAmount += 30;
        }
    }
    
    public void targetedAttack() {
        if (actCount % 10 == 0) {
            BigDemonBall b = new BigDemonBall(0.1, 0.1, true);
            Player player = getWorld().getObjects(Player.class).get(0);
            
            getWorld().addObject(b, getX(), getY());
            b.turnTowards(player.getX(), player.getY());    
        }
    }
    
    public void largeSizeAttack() {
        BigDemonBall b = new BigDemonBall(3, 0.3, false);
        Player player = getWorld().getObjects(Player.class).get(0);
        
        getWorld().addObject(b, getX(), getY());
        b.turnTowards(player.getX(), player.getY());
    }
    
    public void idleAnimate() {
        if (actCount % 8 == 0) {
            if (facingRight) setImage(idleFacingRight[idle_index]);
            else setImage(idleFacingLeft[idle_index]);
            idle_index++;
            idle_index %= idle_size;
        }
    }
    
    public void runAnimate() {
        if (actCount % 8 == 0) {
            if (facingRight) setImage(runFacingRight[run_index]);
            else setImage(runFacingLeft[run_index]);
            run_index++;
            run_index %= run_size;
        }
    }
    
    /*
     * attacks:
     * aura attack
     * 3 quick targeted attacks
     * large ball attack
     * 
     * movement:
     * periodically moves toward player
     */
}
