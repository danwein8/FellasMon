import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class Box {
	int starterMapperX;
	int starterMapperY;
	static private  int perimeterTotal;
	static int vectori = 0;
	static int vectorj = 0;
	static int initialVectori = 0;
	static int initialVectorj = 0;
	static Random r = new Random();
	
	public Box(int x, int y) {
		this.starterMapperX = x;
		this.starterMapperY = y;
	}
	
	private Tiles genrateRandomTile() {
		int xMax = (this.starterMapperX  + perimeterTotal)
					>= Mapper.mapSquare.length ?
							Mapper.mapSquare.length:
								this.starterMapperX  + perimeterTotal;
					
		int yMax = (this.starterMapperY  + perimeterTotal)
				>= Mapper.mapSquare[0].length ?
						Mapper.mapSquare[0].length:
							this.starterMapperY + perimeterTotal;
		
		
		
		int x = r.nextInt(xMax - this.starterMapperX) + this.starterMapperX;
		int y = r.nextInt(yMax - this.starterMapperY) + this.starterMapperY;
		//System.out.println("chosen box within bounds of " + x + " ," + y);
		while(!Mapper.mapSquare[x][y].hasWayPoints) {
			//System.out.println("this occured in response");
			
			x = r.nextInt(xMax - this.starterMapperX) + this.starterMapperX;
			y = r.nextInt(yMax - this.starterMapperY) + this.starterMapperY;
			//System.out.println("chosen box within bounds of " + x + " ," + y);
			if(Mapper.mapSquare[x][y].hasWayPoints) break;
		}
		return Mapper.mapSquare[x][y];
		
		
	}
	
	public static void setPerimeter(int perimeter) {
		perimeterTotal = perimeter * 2 + 1;
	}
	
	Point getWayPoint() {
		Tiles square = this.genrateRandomTile();
		int i = ThreadLocalRandom.current().nextInt(0, square.wayPoints.length);
		return square.wayPoints[i];
	}
	
	private static  boolean confrontInstance(Tiles confront) {
		return  confront instanceof Confrontational;
	}
	
	 static  Box boxesLoader (int y, int x , Tiles tile) {
		Box newBox;
		if((vectori == x|| vectori > Mapper.mapSquare[0].length) && vectorj == y ) vectori = 0;
		/*System.out.println("x is at : " + x + " and y is at: " + y);
		System.out.println("are you being called  I " + vectori);
		System.out.println("are you being called  J " + vectorj);*/
		
		if(((x >= vectori && y >= initialVectorj) || (y >= vectorj)) && (confrontInstance(tile))) {
			initialVectori = x;
			initialVectorj = vectorj;
			vectori = x + perimeterTotal;
			vectorj = y + perimeterTotal;
			//System.out.println("it was activated here");
	
			
			newBox = new Box(y, x);
		}else if((x + perimeterTotal) <= initialVectori && y >= initialVectorj &&  (confrontInstance(tile))) {
			initialVectori = x;
			initialVectorj = vectorj;
			vectori = x + perimeterTotal;
			vectorj = y + perimeterTotal;
			//System.out.println("it was activated here");
			newBox = new Box(y, x);
		}
		else {
			newBox =  null;
		}
		
		return newBox;
		
	}
	 
	 public static ArrayList<Box> getArrayOfBoxes() {
		 Tiles[][] t = Mapper.mapSquare;
		 Box b;
		 ArrayList< Box > bArray = new ArrayList<>();
		 
		 for(int i= 0; i < t.length; i++) {
			 for(int j =0; j < t[i].length; j++) {
				 b = boxesLoader(i, j, t[i][j]);
			
				 if(b != null)
					 bArray.add(b);
			 }
		 }
		 return bArray;
	 }
	 
	 
	
	
	
	

}
