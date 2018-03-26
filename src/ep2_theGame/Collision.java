package ep2_theGame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class Collision {
	
	public Collision(){}

    public void alienCollision(Rectangle r_spaceship, ArrayList<Rectangle> r_alien, ArrayList<Alien> alienList, Spaceship spaceship, int damage){   
    	
    	for(int i = 0; i<alienList.size(); i++){
    		r_alien.add(alienList.get(i).getBounds());
        		if(r_spaceship.intersects(r_alien.get(i)) ){
        			alienList.remove(i);
        			if(spaceship.getSpaceshipShield() > 0)
        				spaceship.setSpaceshipShield(damage);
        			else if(spaceship.getSpaceshipArmor() > 0)
        				spaceship.setSpaceshipArmor(damage+5);
        			else if(spaceship.getSpaceshipHull() > 0)
        				spaceship.setSpaceshipHull(damage+10);
        		}
    		}
    	}
    
	
	

	public void missileAlienCollision(ArrayList<Alien> alienGenericalList, Spaceship spaceship, Alien alien, int pointsDeadAliens){
    	
     	Iterator<Ammo> iteratorAmmo = spaceship.getAmmo().iterator();
     	while(iteratorAmmo.hasNext()){
     		Ammo missile = iteratorAmmo.next();
     		Rectangle rectangleMissile = new Rectangle(missile.getBounds());
     		
     		Iterator<Alien> iteratorAlien = alienGenericalList.iterator();
     		while(iteratorAlien.hasNext()){
     			Alien alienIterator = iteratorAlien.next();
     			Rectangle rectangleAlien = new Rectangle(alienIterator.getBounds());
     			if(rectangleMissile.intersects(rectangleAlien)){
     				alien.setCountAliens(alien.getCountAliens()+ pointsDeadAliens);
     				iteratorAlien.remove();
     				iteratorAmmo.remove();
     			}
     		}
     	}

    } 
	
	
    
    public void bonusCollision(ArrayList<Bonus> bonusList, Rectangle r_spaceship, Spaceship spaceship, ArrayList<Rectangle> r_bonus){
    	for(int i = 0; i<bonusList.size(); i++){
    		r_bonus.add(bonusList.get(i).getBounds());
        		if(r_spaceship.intersects(r_bonus.get(i)) ){
        			bonusList.remove(i);
        			if(spaceship.getSpaceshipShield() < 100){
        				if(spaceship.getSpaceshipShield() == 95)
        					spaceship.setSpaceshipShield(-5);
        				else
        					spaceship.setSpaceshipShield(-10);
        			}

        		}
    		}  
    }
    
	
}
