import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class PokemonBox {
	int x;
	int y;
	int l;
	int w;
	final int BORDER_SEPERATION = 10;
	static Image PokeBall = null;
	
	public PokemonBox(int x, int y, int l, int w) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		if(PokeBall == null) {
			this.loadPokeBall();
		}
	}
	
	
	 void loadPokeBall(){
		try {
			PokeBall = ImageIO.read(getClass().getResource("pokeball.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void draw(Graphics2D g, Color c, Pokemon p, boolean selected) {
		float thickness = 5;
		Color v = new Color(250, 249, 246 );
		Color vI = new Color(250, 249, 246 , 180);
		Color s = new Color(242, 242, 221);
		Color gr = new Color(55, 228, 54);
		Color darkBlue = new Color(74, 47, 223);
		Color lightBlue = new Color(144, 12, 63 , 150);
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(thickness));
		
		Color col  = selected ? gr : darkBlue;
		g.setColor(col);
		g.drawRoundRect(x + BORDER_SEPERATION ,
					y  + BORDER_SEPERATION,
							w  - BORDER_SEPERATION, 
								l - BORDER_SEPERATION, 20, 20);
				
		g.setColor(lightBlue);
		g.fillRoundRect(x + BORDER_SEPERATION + 1  ,
				y  + BORDER_SEPERATION + 1,
				w  - BORDER_SEPERATION  - 2, 
					l - BORDER_SEPERATION - 2, 20, 20);
		
		g.drawImage(PokeBall, x + BORDER_SEPERATION + 40,  
				y  + BORDER_SEPERATION + 40, 80, 70, null);
	
		
		g.setColor(v);
		g.drawRoundRect(x +400 , y  + 150, 180, 30, 10, 10);
		g.setColor(vI);
		g.fillRoundRect(x +400 + 2 , y  + 150 + 2, 180 - 4, 30 - 4, 10, 10);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 35)); 
		g.setColor(s);
		g.drawString(p.name, x +400, y + 129);
		g.setFont(new Font("TimesRoman", Font.BOLD, 24));
		p.hp.calculateMagnitude(p);
		g.drawString(p.hp.currentHp + " / " + p.hp.magnitude,x +400 + 45, y + 220);
		g.drawString("Lv : " + p.level, x + 250, y  + 60 );
		g.setFont(new Font("TimesRoman", Font.BOLD, 12));
		
		g.setColor(c);
	}
	
	
	
	
}
