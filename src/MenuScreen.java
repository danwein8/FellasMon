import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.awt.*;

public class MenuScreen {
	//MenuBoxCol[] cols;
	Point midPoint;
	int cols;
	double wSpace;
	double lSpace;
	int startX;
	int startY;
	int setOfPOM = 0;// pokemons on you that were already sent
	
	MenuScreen(int numOfBoxes, double l, double w){
		midPoint = new Point(0,0);
		cols = numOfBoxes;
		this.wSpace = w;
		this.lSpace = l;
		try {
			rowsInsideCol.selector = new PokemonBox[PlayerTrainer.mainPokeList.numOfCurrentPokemons];  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Point getStartingPoint() {
		Point p = new Point((int)(midPoint.getX() - 735), 
					(int)(midPoint.getY() - 850));
		return p;
		
	}
	
	private int calculateWidth() {
		return (int)(GameBase.screenWidth - (GameBase.screenWidth * this.wSpace));
	}
	
	private int calculateWidthstart() {
		return (int)((GameBase.screenWidth * (this.wSpace/2)));
	}
	
	public int colWidth() {
		double reductedSubScreen = calculateWidth() - (calculateWidth()* .06);
		return (int)reductedSubScreen;
	}
	
	public int colWidthUnit() {
		return (int)((double)this.colWidth()/(double)cols);
	}
	
	
	private int calculateLength() {
		return (int)(GameBase.screenLength - (GameBase.screenLength * this.lSpace));
	}
	
	private int calculatelengthstart() {
		return (int)((GameBase.screenLength * (this.lSpace/2)));
	}
	
	private int colLength() {
		double reductedSubScreen = calculateLength() - (calculateLength()* .15);
		return (int)(reductedSubScreen);
	}
	
	private int colLengthUnit() {
		return (int)((double)this.colLength()/(double) cols);
	}
	
	public int[] calculateColLength() {
		double columnTotalSpace = calculateLength()* .07;
		double unitMove =(double)columnTotalSpace/(double)cols;
		int[] l = new int[cols];
		for(int i = 0 ; i < cols; i++ ) {
			l[i] = calculatelengthstart() + (int)(i * unitMove) + this.colLength();
		}
		this.startY = l[0];
		return l;
	}

	

	
	public int[] calculateColWidth() {
		double columnTotalSpace = calculateWidth()* .05;
		double unitMove = columnTotalSpace/cols;
		
		int[] l = new int[cols];
		for(int i = 0; i < cols; i++ ) {
			l[i] = calculateWidthstart() + (int)((i + 1)* colWidthUnit() + unitMove);
		}
		this.startX = l[0];
		return l;
	}
	
	int decideNumP(int i ) {
		int pokemonsOnMe = PlayerTrainer.mainPokeList.numOfCurrentPokemons;
		int colsFull = pokemonsOnMe/this.cols;
		if(colsFull * (i + 1) <= pokemonsOnMe ) {
			if(colsFull * this.cols == pokemonsOnMe) return colsFull;
			else {
				int leftOver = pokemonsOnMe - colsFull * this.cols;
				if((i + 1) <= leftOver) return colsFull + 1;
				return colsFull;
			}
		}
		else return pokemonsOnMe % colsFull;
		
			
			
	}
	
	
	public void draw(Graphics g) {
		
		int[] ls = this.calculateColLength();
		int[] ws = this.calculateColWidth();
		for(int i = 0; i < cols; i++ ) {
			Color offWhite = new Color(250, 248, 209);
			g.setColor(offWhite );
			Point s = getStartingPoint();
			int xs = (int)s.getX() + ws[i];
			int ys = (int)s.getY() + ls[i];
			int wss = this.colWidthUnit() - 70;
			int lss = this.colLength();
			g.fillRoundRect(xs,ys, wss, lss, 50,50);
			rowsInsideCol in = new rowsInsideCol(xs, ys, lss, wss, this.decideNumP(i));
			in.draw(g, i*3);
		}
		
		
	}


}
