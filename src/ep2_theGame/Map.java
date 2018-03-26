package ep2_theGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel implements ActionListener{

    private final int SPACESHIP_X = 220;
    private final int SPACESHIP_Y = 430;
    private final Timer timer_map;
    private final Timer timer_alien;
    private final Timer timer_alien_medium;
    private final Timer timer_alien_hard;
    private final Timer timer_bonus;
    
    private static final int mediumAlienCondition = 15;
    private static final int hardAlienCondition = 80;
    private static final int totalPoints = 500;

    
    private static final int easyAlienDelay = 1000;
    private static final int mediumAlienDelay = 450;
    private static final int hardAlienDelay = 300;
    private static final int bonusDelay = 5000;
    
    private static final int pointsDeadAliens = 1;
    private static final int pointsDeadMediumAliens = 3;
    private static final int pointsDeadHardAliens = 5;
    
    private static final int easyAlienDamage = 5;
    private static final int mediumAlienDamage = 10;
    private static final int hardAlienDamage = 15;
    
    private final Alien alien = new Alien();
    private ArrayList<Alien> alienList = new ArrayList<Alien>();
    private ArrayList<Alien> alienMediumList = new ArrayList<Alien>();
    private ArrayList<Alien> alienHardList = new ArrayList<Alien>();
    private ArrayList<Bonus> bonusList = new ArrayList<Bonus>();
    
    private Random randomAlienPosition = new Random();
    
    private final Image background;
    private final Spaceship spaceship;

    public Map() {
        
        addKeyListener(new KeyListerner());
        
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon image = new ImageIcon("images/space.jpg");
        
        this.background = image.getImage();
        
        spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y);
                
        timer_map = new Timer(Game.getDelay(), this);
        timer_map.start();
        
        ActionListener addAlienEasy = new ActionListener(){
        	public void actionPerformed(ActionEvent event){	
        		addAlien(alienList);
        	}
        };
        timer_alien = new Timer(easyAlienDelay, addAlienEasy);
        timer_alien.start();        
        
        ActionListener addAlienMedium = new ActionListener(){
        	public void actionPerformed(ActionEvent event){	
        		if(alien.getCountAliens()>mediumAlienCondition)
        			addAlien(alienMediumList);
        	}
        };
        
        timer_alien_medium = new Timer(mediumAlienDelay, addAlienMedium);
        timer_alien_medium.start(); 
        
        ActionListener addAlienHard = new ActionListener(){
        	public void actionPerformed(ActionEvent event){	
        		if(alien.getCountAliens()>hardAlienCondition)
        			addAlien(alienHardList);
        	}
        };
        timer_alien_hard = new Timer(hardAlienDelay, addAlienHard);
        timer_alien_hard.start(); 

        ActionListener addBonus = new ActionListener(){
        	public void actionPerformed(ActionEvent event){	
        		addBonus(bonusList);
        	}
        };
        
        timer_bonus = new Timer(bonusDelay, addBonus);
        timer_bonus.start();
        
        
  }
    
    
    public void addAlien(ArrayList<Alien> alienGenericalList){
    	alienGenericalList.add(new Alien(randomAlienPosition.nextInt(Game.getWidth()-1), 10));
    }
    
    public void addBonus(ArrayList<Bonus> bonusGenericalList){
    	bonusGenericalList.add(new Bonus(randomAlienPosition.nextInt(Game.getWidth() -1), 10));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.background, 0, 0, null);
       
        draw(g);

        Toolkit.getDefaultToolkit().sync();
    }
    
    private void draw(Graphics g) {
               
        g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);        
        for(int i = 0; i < bonusList.size(); i++){
        	g.drawImage(bonusList.get(i).getImage(), bonusList.get(i).getX(), bonusList.get(i).getY(), this);

        }
        
        drawAlienList(alienList,g);
        
        if(alien.getCountAliens() > mediumAlienCondition){
            drawAlienList(alienMediumList,g);
        }

        if(alien.getCountAliens() > hardAlienCondition){
            drawAlienList(alienHardList,g);
        }
        
        for(int i=0; i < spaceship.getAmmo().size(); i++)
        	g.drawImage(spaceship.getAmmo().get(i).getImage(), spaceship.getAmmo().get(i).getX(), spaceship.getAmmo().get(i).getY(), this);
    
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Points: " + alien.getCountAliens(), 5, 15);
        g.drawString("Points left: " + (totalPoints - alien.getCountAliens()), 5, 30);
        
        
        if(spaceship.getSpaceshipShield() > 0){
            g.setColor(Color.WHITE);
        	g.drawString("Shield: " + spaceship.getSpaceshipShield() + "%", spaceship.getX()-15, spaceship.getY()+65);
        }

        else{
        	spaceship.setSpaceshipShieldCritical();
            g.setColor(Color.RED);
        	g.drawString("Shield: " + spaceship.getSpaceshipShield() + "%", spaceship.getX()-15, spaceship.getY()+65);
        }
        
        if(spaceship.getSpaceshipArmor() > 0){
            g.setColor(Color.WHITE);
            g.drawString("Armor: " + spaceship.getSpaceshipArmor() + "%", spaceship.getX()-15, spaceship.getY()+80);
        }

        else{
        	spaceship.setSpaceshipArmorCritical();
            g.setColor(Color.RED);
            g.drawString("Armor: " + 0 + "%", spaceship.getX()-15, spaceship.getY()+80);
        }
        if(spaceship.getSpaceshipHull() > 0){
            g.setColor(Color.WHITE);
            g.drawString("Hull: " + spaceship.getSpaceshipHull() + "%", spaceship.getX()-15, spaceship.getY()+95);
        }

        else{
        	spaceship.setSpaceshipHullCritical();
        	//timer_map.stop();
        	drawGameOver(g);
            g.setColor(Color.RED);
            g.drawString("Hull: " + 0 + "%", spaceship.getX()-15, spaceship.getY()+95);
        }
        
        if(alien.getCountAliens() >= totalPoints){
        	drawMissionAccomplished(g);
        }
    
    }
    
    
    private void drawAlienList(ArrayList<Alien> alienList, Graphics g){
        for(int i = 0; i < alienList.size(); i++){
        	g.drawImage(alienList.get(i).getImage(), alienList.get(i).getX(), alienList.get(i).getY(), this);

        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(alien.getCountAliens() > totalPoints){
    	   endGameWin();
    	   timer_map.stop();
       }
       else if(spaceship.getSpaceshipHull() <= 0){
    	   endGameLost();
    	   timer_map.stop();
       }
        updateSpaceship();
        updateAlien();
        updateBonus();
        updateMissile();
        collision();
        repaint();
    }
   
    // ---Messages de vitória ou derrota
   
    public void drawMissionAccomplished(Graphics g) {

        String message = "MISSION ACCOMPLISHED";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
     }
    
    public void endGameWin(){
        String name = JOptionPane.showInputDialog(null,"You Win! Enter your name");
        Player player = new Player(name);
        new Rank().logRank(player, alien);
        new MainPanel();
    }
    
    public void endGameLost(){
        String name = JOptionPane.showInputDialog(null,"Game Over! Enter your name");
        Player player = new Player(name);
        new Rank().logRank(player, alien);
        new MainPanel();
    }
    
    private void drawGameOver(Graphics g) {

        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
    }
    
    // --- Update objects
    
    private void updateSpaceship() {
        spaceship.move();
    }
    
    
    private void updateAlien(){

		for(int i = 0; i < alienList.size(); i++)
			alienList.get(i).fallAlienEasy();
		if(alien.getCountAliens() > mediumAlienCondition){
	   		for(int i = 0; i< alienMediumList.size(); i++)
	   			alienMediumList.get(i).fallAlienMedium();

		}
		if(alien.getCountAliens() > hardAlienCondition){
	   		for(int i = 0; i< alienHardList.size(); i++)
	   			alienHardList.get(i).fallAlienHard();
		}
    }
    
    private void updateBonus(){
    	for(int i = 0; i < bonusList.size(); i++)
    		bonusList.get(i).fallBonus();
    }
    
    private void updateMissile(){
    	for(int i = 0; i < spaceship.getAmmo().size(); i++)
    		spaceship.getAmmo().get(i).missileDynamics();
    }
    
    // ---- Collisions
    
    private void collision(){
    	
    	Rectangle r_spaceship = spaceship.getBounds();
    	ArrayList<Rectangle> r_alien = new ArrayList<>();
    	ArrayList<Rectangle> r_alien_medium = new ArrayList<>();
    	ArrayList<Rectangle> r_alien_hard = new ArrayList<>();
    	ArrayList<Rectangle> r_bonus = new ArrayList<>();
          	   	    	
     	new Collision().alienCollision(r_spaceship, r_alien, alienList, spaceship, easyAlienDamage);
     	new Collision().alienCollision(r_spaceship, r_alien_medium, alienMediumList, spaceship, mediumAlienDamage);
     	new Collision().alienCollision(r_spaceship, r_alien_hard, alienHardList, spaceship, hardAlienDamage);
   	
     	new Collision().missileAlienCollision(alienList, spaceship, alien, pointsDeadAliens);
     	new Collision().missileAlienCollision(alienMediumList, spaceship, alien, pointsDeadMediumAliens);
     	new Collision().missileAlienCollision(alienHardList, spaceship, alien, pointsDeadHardAliens);
     	
     	new Collision().bonusCollision(bonusList, r_spaceship, spaceship, r_bonus);
    }
    

          
    // --- KeyListener
    private class KeyListerner extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }       
    }
}