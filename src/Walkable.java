import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Walkable extends Tiles {
	
	public Walkable(int x, int y, int w, int l, String tileImageName) {
		super(x, y, w, l, tileImageName);
		wayPoints = new Point[9];
		this.loadWayPoints();
	}
	
	
	@Override
	Graphics tileAction(Graphics g, Character c) {
		g.setColor(Color.GREEN);
		g.fillRect(10,10,10,10);
		return g;
	};
	
	
}
