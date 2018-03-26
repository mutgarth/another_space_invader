package ep2_theGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Spaceship extends Sprite {
    
    private static final int MAX_SPEED_X = 3;
    private static final int MAX_SPEED_Y = 2;
   
    private int speed_x;
    private int speed_y;
    private int spaceshipShield = 100;
    private int spaceshipArmor = 100;
    private int spaceshipHull = 100;
    private ArrayList<Ammo> ammoList = new ArrayList<Ammo>();
   


    public Spaceship(int x, int y) {
        super(x, y);
        initSpaceShip();
    }
    
    public int getSpaceshipShield(){
    	return spaceshipShield;
    }
    
    public void setSpaceshipShield(int damage){
    	this.spaceshipShield -= damage;
    }
    
    public void setSpaceshipShieldCritical(){
    	this.spaceshipShield = 0;
    }
    
    public int getSpaceshipArmor(){
    	return spaceshipArmor;
    }
    
    public void setSpaceshipArmor(int damage){
    	this.spaceshipArmor -= damage;
    }
    
    public void setSpaceshipArmorCritical(){
    	this.spaceshipArmor = 0;
    }
    
    public int getSpaceshipHull(){
    	return spaceshipHull;
    }
    
    public void setSpaceshipHull(int damage){
    	this.spaceshipHull -= damage;
    }
    
    public void setSpaceshipHullCritical(){
    	this.spaceshipHull = 0;
    }
    

    private void initSpaceShip() {
        
        noThrust();
        
    }
    
    private void shieldExplosion(){
    	loadImage("images/explosion.png");
    }
    
    private void noThrust(){
        loadImage("images/spaceship_crt.png"); 
    }
    
    private void thrust(){
        loadImage("images/spaceship_crt_thrust.png"); 
    }    
    
    
    public ArrayList<Ammo> getAmmo(){
    	return ammoList;
    }
    

  
    

    public void move() {
        
        // Limits the movement of the spaceship to the side edges.
        if((speed_x < 0 && x <= 0) || (speed_x > 0 && x + width >= Game.getWidth())){
            speed_x = 0;
        }
        
        // Moves the spaceship on the horizontal axis
        x += speed_x;
        
        // Limits the movement of the spaceship to the vertical edges.
        if((speed_y < 0 && y <= 0) || (speed_y > 0 && y + height >= Game.getHeight())){
            speed_y = 0;
        }

        // Moves the spaceship on the vertical axis
        y += speed_y;
        
        
    }
    
    public void shot(){

    	ammoList.add(new Ammo(this.getX()+20, this.getY()+1));
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        // Set speed to move to the left
        if (key == KeyEvent.VK_LEFT) { 
            speed_x = -1 * MAX_SPEED_X;
        }

        // Set speed to move to the right
        if (key == KeyEvent.VK_RIGHT) {
            speed_x = MAX_SPEED_X;
        }
        
        // Set speed to move to up and set thrust effect
        if (key == KeyEvent.VK_UP) {
            speed_y = -1 * MAX_SPEED_Y;
            thrust();
        }
        
        // Set speed to move to down
        if (key == KeyEvent.VK_DOWN) {
            speed_y = MAX_SPEED_Y;

        }
        
        if (key == KeyEvent.VK_SPACE) { 
        	shot();
        }
       
        
    }
    
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            speed_x = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            speed_y = 0;
            noThrust();
        }
    }
}