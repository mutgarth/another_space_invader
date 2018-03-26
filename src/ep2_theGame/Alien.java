package ep2_theGame;

import java.lang.Math;

public class Alien extends Sprite {
	
    private static final int alienFallSpeed = 1;

    private int countAliens;

    public Alien(){
    	super(0 , 0);	
    }

	public Alien(int x, int y){
		super(x , y);	
	}
	
    
    public int getCountAliens(){
    	return countAliens;
    }
    
    public void setCountAliens(int countAliens){
    	this.countAliens = countAliens;
    }
    
	
	public void fallAlienEasy(){
		loadImage("images/alien_easy.png"); 
		y += alienFallSpeed;
	}
	
	public void fallAlienMedium(){
		loadImage("images/alien_medium.png"); 
		y += 3*alienFallSpeed;


	}
	

	public void fallAlienHard(){
		loadImage("images/alien_hard.png");
		y += 3*alienFallSpeed;
	}	
}
