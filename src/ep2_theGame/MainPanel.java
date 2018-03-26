package ep2_theGame;

public class MainPanel{
	   public static Application menuFrame = new Application();
	   
	   public MainPanel(){
		   menuFrame.add(new GameMenu(menuFrame));
		   menuFrame.setVisible(true);
		   
		}
	   
	   public static void main(String[] args){
		   new MainPanel();
	   }
		    
}