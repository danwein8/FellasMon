
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class BattleBox {
	Point[] ps = new Point[PlayerTrainer.trainerInstance.currentPokemon().attackList.length];
	static Image pointer = null;
	int pointing = 0;
	static BattleBox currentInstance = new BattleBox();
	Pokemon p;
	
	
	BattleBox(){
		if(pointer == null) {
			this.loadPointer();
		}
	}
	
	
	 void loadPointer(){
		try {
			pointer = ImageIO.read(getClass().getResource("pointer.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	 void LoadBattle() {
		 p = PlayerTrainer.trainerInstance.currentPokemon();
		 p.generateAttackList();
	 }
	 
	 void scrollUp() {
		 
		 if(pointing  > 0) pointing--;
		 System.out.println(pointing);
	 }
	 
	 void scrollDn() {
		 if(pointing < 3) pointing++;
		 System.out.println(pointing);
	 }
	
	 void draw(Graphics l) {
		Graphics2D g = (Graphics2D)l;
		float thickness = 5;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(thickness));
		g.setColor(Color.CYAN); 
		g.drawRect(948, 610,806, 232);
    	g.setColor(Color.CYAN); 
    	Color lightG = new Color(208, 208, 225, 175);
		g.setColor(lightG);
		
		g.fillRect(950, 612 ,802, 228 );
		
		double betweenSpace = 228 * .15;
		
		int lettersLength = (int)((228 - betweenSpace) /(double)4);
		
		Font font = new Font("Verdana", Font.BOLD, 20);
		g.setColor(Color.black); 
		g.setFont(font);
		for(int i=0; i < p.attackList.length; i++) {
			if(p.attackList[i] != null) {
				g.drawString(p.attackList[i], 955, 646 + (lettersLength*i) );
			}else {
				g.drawString("--", 955, 646 + (lettersLength*i) );
			}
			
			if(ps[i] == null) {
				Point pp = new Point(955 + p.attackList[i].length() * 12, 646 + (lettersLength*i) - 10 );
				ps[i] = pp;
			}
		}
		
		g.drawImage(pointer,(int)ps[pointing].getX(), (int)ps[pointing].getY(), 30, 30, null);
		
    	
    	
	}
	
	
	public static void main(String[] args) {
		System.out.println(GameBase.screenWidth);
	}
	
}
