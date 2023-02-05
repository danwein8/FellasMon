import java.awt.Point;
import java.util.Random;
import java.awt.*;

public class Confrontational extends Tiles {
	
	static Random rand = new Random();
	static int secondCounter = 0;
	static int oneSecondBasedOnG = 60;
	
	public Confrontational(int x, int y, int w, int l, String tileImageName){
		
		super(x, y, w, l,  tileImageName);
		wayPoints = new Point[9];
		this.loadWayPoints();
	}
	@Override
	public int trackSecond(Character c) {
		if(this.insideCircle(c)) {
	
			secondCounter++;
			
			if(secondCounter == oneSecondBasedOnG) {
				secondCounter = 0;
				return rand.nextInt(100);
			}
		}
		
		return -1;
		
	}
	
	public static void randomPokemon(int rand, Graphics g) {
		if(rand < 13 && rand > -1) 
			GameBase.mode = GameBase.GameMode.Wild;
			
	}
	
	
	
	
	
	

}
