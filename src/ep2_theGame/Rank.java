package ep2_theGame;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Rank extends JPanel{
    private static String logPlayer;
	private final Image background;
	public static StringBuilder sb = new StringBuilder();
	public static String line;
	public static JTextArea playerList = new JTextArea();



	public Rank(){
        ImageIcon image = new ImageIcon("images/space.jpg");
        this.background = image.getImage();
	}
	
	public Rank(Application rankFrame){
        ImageIcon image = new ImageIcon("images/space.jpg");
        this.background = image.getImage();
        
        setLayout(new FlowLayout());
        JButton buttonStart = new JButton("Start Game"); 
        JButton buttonExit = new JButton("Exit");
        add(buttonStart);
        add(buttonExit);
        
        add(playerList);

        
		   buttonStart.addActionListener(new ActionListener() { 
			   public void actionPerformed(ActionEvent e) { 
				   EventQueue.invokeLater(new Runnable() {
					   @Override
					   public void run() {
						   Application app = new Application();
						   app.add(new Map());
						   app.setVisible(true);
						   rankFrame.setVisible(false);
						   
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
    
    public static void readRank(){
    	try{
            FileReader in = new FileReader("rank/rank.txt");
            BufferedReader br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
            	playerList.read(br, "text");
                System.out.println(line);
            }
            in.close();
    	} catch(IOException ie){
    		ie.printStackTrace();
    	}

    }

    public static void logRank(Player player, Alien alien) {   	
    	
        logPlayer = "Player: " + player.getPlayerName()+ " Points: " + alien.getCountAliens() + "\n";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter("rank/rank.txt", true) );
            writer.write(logPlayer);
        }
        catch ( IOException e) { }
        finally {
            try {
                if (writer != null)
                    writer.close( );
            }
            catch ( IOException e) { }
        }

    }
    
}
