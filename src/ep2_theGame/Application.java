package ep2_theGame;

import javax.swing.JFrame;

public class Application extends JFrame {

    public Application() {
    	
    	setSize(Game.getWidth(), Game.getHeight());
    	setTitle("Space Combat Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);      
  }  
    
}

