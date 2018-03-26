package ep2_theGame;

public class Bonus extends Sprite {

	private static final int bonusFallSpeed = 1;
	
	public Bonus(){
		super(0, 0);
	}
	
	public Bonus(int x , int y){
		super(x, y);
	}
	
	public void fallBonus(){
		loadImage("images/bonus.png"); 
		y += bonusFallSpeed;
	}
	
}
