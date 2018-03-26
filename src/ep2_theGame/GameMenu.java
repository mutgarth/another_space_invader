package ep2_theGame;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameMenu extends JPanel {
	private final Image background;
	
	public GameMenu(){
        ImageIcon image = new ImageIcon("images/space.jpg");
        this.background = image.getImage();
	}
	
	public GameMenu(Application gameMenu) {
        ImageIcon image = new ImageIcon("images/space.jpg");
        this.background = image.getImage();
        
        setLayout(new FlowLayout());
        JButton buttonStart = new JButton("Start Game"); 
        JButton buttonRank = new JButton("Rank"); 
        JButton buttonExit = new JButton("Exit"); 


        add(buttonStart);
        add(buttonRank);
        add(buttonExit);
        
		   buttonStart.addActionListener(new ActionListener() { 
			   public void actionPerformed(ActionEvent e) { 
				   EventQueue.invokeLater(new Runnable() {
					   @Override
					   public void run() {
						   Application app = new Application();
						   app.add(new Map());
						   app.setVisible(true);
						   new GameMenu().setVisible(false);
						   gameMenu.setVisible(false);
						   
					   }
				   });
			   }
		   });
		   
		   buttonRank.addActionListener(new ActionListener() { 
			   public void actionPerformed(ActionEvent e) { 
				   EventQueue.invokeLater(new Runnable() {
					   @Override
					   public void run(){
						   Application rankFrame = new Application();
						   rankFrame.add(new Rank(rankFrame));
						   rankFrame.setVisible(true);
						   new Rank().readRank();
						   gameMenu.setVisible(false);
					   }
				   });
			   }
		   });
		   
		   buttonExit.addActionListener(new ActionListener() { 
			   public void actionPerformed(ActionEvent e) { 
				   System.exit(0);
			   }
		   });     
        
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(this.background, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
    }

}
