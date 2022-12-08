import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;


public class Mapper {
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	final String PROJECT_DIR = System.getProperty("user.dir");//file should be inside the dir
	double picToCharacterRatio;
	int rows;
	int columns;
	int fitToScreen;
	static Tiles[][] mapSquare;
	public static ArrayList<String>  mapString = new ArrayList<String>();


		public Mapper(String fileName, double picToCharacterRatio, int fitToScreen ) throws IOException, NotEnoughTilesError{
			
			this.picToCharacterRatio = picToCharacterRatio;
			this.setRowsAndColumn(fileName);
			this.loadMapString(fileName);
			if((int)Math.pow(fitToScreen,2)> this.columns * this.rows) throw new NotEnoughTilesError("FitToScreen var is too big....pause");
			this.fitToScreen = fitToScreen;
			this.loadMapSquare();
			Box.setPerimeter(2);
			ArrayList<Box> b = Box.getArrayOfBoxes();
			Object[][] enemies = {{Enemies.RT, "wr", "png"},
					{Enemies.LT, "wl", "png"},
					{Enemies.DN,  "wf", "png"},
					{Enemies.UP, "wb", "png"}} ;
			 for (Box i : b) {
				GameAn.addEnemies("jamilton", enemies, i);
			 }
			
		}
		//return object is a bytestream ready in the buffer
		private BufferedReader docScanr(String text)throws IOException{
			BufferedReader br = null;
			try {
			 br = new BufferedReader(new FileReader(PROJECT_DIR + "/" + text));
			}catch(IOException e) {
				e.printStackTrace();
			}
			 return br;
		}
		//laysout the grid for our mapping 
		public void setRowsAndColumn(String text) throws IOException {
			BufferedReader file = this.docScanr(text);
			 int lineCounter = 1;
			 String line = file.readLine();
			 this.columns = line.length()/4;
			 for(String b = file.readLine(); b != null; b = file.readLine()) {
				 lineCounter++;
			 }
			 this.rows = lineCounter;
			 file.close();
		}
	
		public int getTilesWidth() {
			return (int)(GameBase.screenWidth / this.fitToScreen);
		}
		
		public int getTileHeight() {
			return (int)(GameBase.screenLength / this.fitToScreen);
		}
		
		public int getMapWidth() {
			return columns * this.getTilesWidth();
		}
		
		public int getMapHeight() {
			return rows * getTileHeight();
		}
		
		private int[] getBounds(int range) {
			int midPoint = range/2;
			int inital = -midPoint;
			int end = midPoint;
			int[] j = {inital, end};
			return j;
		}
	
		public int[] getHBounds() {
			return this.getBounds(this.getMapWidth());
		}
		
		public int[] getVBounds() {
			return this.getBounds(this.getMapHeight());
		}
		
		public void loadMapSquare() {
			//this rect will later be replaced with a walkable interface and etc
			int mapCounter = 0;
			
			
			char flag;
			Tiles[][] map = new Tiles[this.rows][this.columns];
			int i, j;
			i = j = 0;
			for( int y = this.getVBounds()[0];
						y < this.getVBounds()[1]; 
							y+= this.getTileHeight()) {
							    j=0;
								for (int x = this.getHBounds()[0];
											x < this.getHBounds()[1]; 
												x+= this.getTilesWidth()) {
									flag = mapString.get(mapCounter).charAt(3);
									map[i][j] = Tiles.getTileKind(x, y, this.getTilesWidth(), this.getTileHeight(), flag,
													this.mapStringFileName(mapString.get(i*this.columns + j)));	
									j++;
									mapCounter++;
								}
								i++;
			}
			mapSquare = map;
			
		}
		
		
		
		
		public void loadMapString(String text) throws IOException{
			BufferedReader file = this.docScanr(text);
		     String resp = "";
			for(int b = file.read(); b!= -1; b = file.read()) {
				if(b != 10)resp += (char)b;
	        	 if (resp.length() ==4) {
	        		mapString.add(resp);
	        		resp = "";
	        	 } 
	         }
			file.close();
		}
		
		private String imageMapper(int mapCounter) {
			String s = mapString.get(mapCounter);
			
			return this.mapStringFileName(s) ;
		}
		
		private Image ImageGetter(String fileName) {
			Image image = null;
			try {
				image = ImageIO.read(getClass().getResource(PROJECT_DIR + "/tiles/" + fileName + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return image;
		}
		
		private void ImageSetter(Image image, Rect square,  Graphics g) {
			
			g.drawImage(image, square.x,
						square.y, square.x + square.w,
							square.y + square.l, null);
		}
		
		private String mapStringFileName(String fullString) {
			return fullString.substring(0, 3);
			
		}
		
		private void defaultDrawers(Graphics g) {
			g.setColor(Color.BLACK);
		}
		
		
		public void draw(Graphics g, Character c) {
			String fileName;
			Image blockImage;
			Tiles cr = null;//short for current rectanle
			for (int i=0; i < mapSquare.length; i ++) {
				for(int j = 0; j < mapSquare[i].length; j++) {
					cr = mapSquare[i][j];
					if(cr instanceof Confrontational)
						Confrontational.randomPokemon(cr.trackSecond(c));
					cr.draw(g,c);
					this.defaultDrawers(g);
					
					
					//fileName = this.imageMapper(mapCounter);
					//blockImage = this.ImageGetter(fileName);

				}
			}
			
		}
		
		public int[] getCharacterLocation(Character c) {
			int[]location = {0 , 0};
			for (int i=0; i < mapSquare.length; i ++) {
				for(int j = 0; j < mapSquare[i].length; j++) {
					if(mapSquare[i][j].insideCircle(c)) {
						location[0] = i;
						location[1] = j;
						break;
						
					}
				}
				
			}
			return location;
		}
		
	

}
