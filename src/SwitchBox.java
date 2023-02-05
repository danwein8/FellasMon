
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class SwitchBox {
	Color lightG = new Color(208, 208, 225, 175);
	Color offW = new Color(247, 250, 220);
	Color gT = new Color(118, 248, 46, 170 );
	Point[] ps = new Point[6];
	static Image pointer = null;
	int pointing = 0;
	Pokemon[] p = PlayerTrainer.trainerInstance.pokemon;
	static SwitchBox currentInstance = new SwitchBox();
	
	
	
	SwitchBox(){
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
	 
	 void updatePokemon() {
		 p = PlayerTrainer.trainerInstance.pokemon;
	 }
	 
	 void scrollUp() {
		 
		 if(pointing  > 0) pointing--;
		 System.out.println(pointing);
	 }
	 
	 void scrollDn() {
		 if(pointing < PlayerTrainer.trainerInstance.totalPoke) pointing++;
		
	 }
	 
	 
	
	 void draw(Graphics l) {
		Graphics2D g = (Graphics2D)l;
		float thickness = 5;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(thickness));
		g.setColor(Color.CYAN); 
		g.drawRect(948, 610,806, 232);
    	g.setColor(Color.CYAN); 
    	
		g.setColor(lightG);
		
		g.fillRect(950, 612 ,802, 228 );
		
		double betweenSpace = 228 * .15;
		
		int lettersLength = (int)((228 - betweenSpace) /(double)6);
		
		Font font = new Font("Verdana", Font.BOLD, 15);
		g.setFont(font);
		
		g.setColor(Color.BLACK); 
		
		for(int i=0; i < 6; i++) {
			if(p[i] != null) {
				g.drawString(p[i].name, 1180, 646 + (lettersLength*i) );
				g.setColor(offW);
				g.drawRoundRect(970 ,646 + (lettersLength*i) - 5, 180, 10, 10, 10);
				g.setColor(gT);
				g.fillRoundRect(970 + 2 , 646 + (lettersLength*i) - 5 + 2, (int)((180 - 4)*p[i].hp.getHpPer()), 10 - 4, 10, 10);
				g.setColor(Color.BLACK); 
				
			}else {
				g.drawString("--", 1180, 646 + (lettersLength*i) );
				g.setColor(Color.BLACK); 
			}
			
			if(ps[i] == null) {
				Point pp;
				if(p[i] == null) {
					 pp = new Point(1180 + "--".length() * 9, 646 + (lettersLength*i) - 10 );
					
				}else {
					 pp = new Point(1180 + p[i].name.length() * 9, 646 + (lettersLength*i) - 10 );
				}
				
				ps[i] = pp;
			}
		}
		
		g.drawImage(pointer,(int)ps[pointing].getX(), (int)ps[pointing].getY(), 30, 30, null);
		
    	
    	
	}
	
	
	public static void main(String[] args) {
		System.out.println(GameBase.screenWidth);
	}
	
}