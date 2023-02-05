import java.awt.*;
public class rowsInsideCol  {
	
	int x;
	int y;
	int l;
	int w;
	int set;
	static PokemonBox[] selector;
	static int activeIndexSelected = 0;
	

	
	public rowsInsideCol(int x, int y, int l, int w, int set) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.w = w;
		this.set = set;
	
	}
	
	int getEachBoxLength() {
		return (int)((double)(l - l * 0.1)/(double)3);
	}
	
	int getSeperationLength() {
		return (int)((double)l * 0.1/(double)4);
	}
	
	int getEachBoxWidth() {
		return (int)((double)(w - w * 0.05));
	}
	
	int getSeperationWidth() {
		return (int)((double)w * 0.05/(double)2);
	}
	
	
	void draw(Graphics g, int startIndex) {
		Color burg = new Color(199, 0, 57);
		g.setColor(burg);
		for(int i =1; i <=3; i++) {
			int xs = x + this.getSeperationWidth();
			int ys = y + i* this.getSeperationLength() + (i - 1)* this.getEachBoxLength();
			int ws = this.getEachBoxWidth();
			int ls = this.getEachBoxLength();
			g.fillRoundRect(xs,ys, ws,ls, 50,50);
			PokemonBox pb = new PokemonBox(xs, ys, ls, ws);
			Graphics2D j = (Graphics2D)g;
			if(i <= set) {
				int listIndex = startIndex + (i - 1);
		
				pb.draw(j, burg,  
						PlayerTrainer.mainPokeList.p[listIndex],
							(activeIndexSelected == listIndex));
				selector[listIndex] = pb;
			}
		}
	}

}
