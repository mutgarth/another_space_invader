package ep2_theGame;

import javax.swing.JOptionPane;

public class Player{
	private String playerName;
	
	public Player(){}
	
	public Player(String playerName){
		this.playerName = playerName;
		
	}

	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	public void teste(){
	    playerName = JOptionPane.showInputDialog(null,"please enter value");	
	}
}
