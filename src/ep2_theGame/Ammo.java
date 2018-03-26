package ep2_theGame;

public class Ammo extends Sprite {
	
	public Ammo(int x, int y){
		super(x , y);
		shotAmmo();
	}
	
	private void shotAmmo(){
		missile();
	}

	private void missile(){
        loadImage("images/missile.png"); 
	}
	
	public void missileDynamics(){
		y -= 5;
	}
	
}
