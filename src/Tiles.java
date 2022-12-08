import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Tiles extends Rect{
	
	//Image image;
	Point[] wayPoints;// will contains points in the tile for locaitoin
	final double[] BORDERS = {.2, .5, .8};
	boolean hasWayPoints = true;
	Image loadedImage;
	static Map<String, Image> imageString = new HashMap<String, Image>();
	//tiles will contain imaginary borders that will define where a character would walk
	
	public Tiles(int x, int y, int w, int l, String tileImageName) {
		
		super(x,y, w, l);
		try {
			if(imageString.get(tileImageName) == null) {
				loadedImage = ImageIO.read(getClass().getResource(String.format("%s.jpeg", tileImageName)));
				imageString.put(tileImageName, loadedImage);
			}else {
				loadedImage = imageString.get(tileImageName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//image = null;
	}

	public static Tiles getTileKind(int x, int y, int w, int l, char tileType, String tileImageName) {
		Tiles tileTypes = null;
		switch (tileType) {
        case 'w':
        	tileTypes = new Walkable(x, y, w, l, tileImageName); 
            break;
        case 'u':
        	tileTypes = new Unwalkable(x, y, w, l, tileImageName);
            break;
        case 'c':
        	tileTypes = new Confrontational(x, y, w, l, tileImageName);
            break;  
    }
		return tileTypes;
	}
	
	 Graphics tileAction(Graphics g, Character c) {
		return g;
	};
	
	
	  
	   void loadWayPoints() {
		  int xBoard;
		  int  yBoard;
		  int i = 0;
			for(int x = 0; x < BORDERS.length; x ++) {
				for(int y = 0; y < BORDERS.length; y++) {
					
					xBoard =  (int)(this.x + w *BORDERS[x]);
					yBoard = (int)(this.y + l *BORDERS[y]);
					Point p = new Point(xBoard, yBoard);
					
					wayPoints[i] = p; 
					
					i++;
				}
			}
		 }
	  
	  int selectRandomPoint() {
		  Random rand = new Random();
		  return (int)rand.nextInt(this.wayPoints.length);
	  }
	 
	
	public void draw(Graphics g, Character c) {
		if(this.overLaps( c)) {
			g = tileAction(g,c);
		}
		g.drawImage(this.loadedImage, 0+ x - Camera.x, 0 + y - Camera.y, w,l, null);
		
	}
	
	public boolean insideCircle(Character c) {
		return ((c.x >= x) &&
				(c.x <= (x + w)) &&
					(c.y >= y) &&
						(c.y <= (y + l)));
	}
	
	public int trackSecond(Character c) {
		return -1;
	}
	
	
}
