import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Confronts {
	static Enemies enemyconfronting;
	static Image caught;
	static Image vs;
	static int stepCounter = 0;
	static int moveIndex = 0;
	final static int indexTimer = 60;
	static int[]loc;
	static boolean alreadyChecked = false;
	static int screenChanger = 0;
	static int changerMax = 80;
	static boolean isWhite;
	
	
	Confronts(){
		 try {
			 Confronts.caught =  ImageIO.read(getClass().getResource("gotYou.png"));
			 Confronts.vs =  ImageIO.read(getClass().getResource("vs.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		 alreadyChecked = false;
	}
	
	static public void circulator() {
		stepCounter++;
		if(stepCounter == indexTimer) {
			moveIndex++;
			stepCounter = 0;
		}
	}
	
	static public void screenShift() {
		screenChanger++;
		if(screenChanger == changerMax) {
			screenChanger++;
			screenChanger= 0;
		}
		isWhite = screenChanger < 35;
	}
		 
	 static public void initialConfront(Graphics g, JPanel stuff) {
		 	circulator();
		 	screenShift();
		 	if(GameBase.mode == GameBase.GameMode.Confronted) {
		 	Enemies ec = enemyconfronting;
		 	if(moveIndex >=0)
			g.drawImage(caught, ec.x-Camera.x + (ec.w/2), ec.y-Camera.y - 40, ec.w/2, ec.l/2, stuff);
			Character c = Character.main;
			
			
			int[] xBound = GameAn.currentMap.getHBounds();
			int[] yBound = GameAn.currentMap.getVBounds();
			String enemyArgs = "you fool you must be crazy rolling around here, lets battle, ima have to"
					+ "	cook you with all my might now, and this does not stop cause i will flame you until the end so prepare";
			final int letterPerRow = 60;
			int boxHeight = (enemyArgs.length()/letterPerRow ) + 1;
			final int rowSize = (int)(GameBase.screenLength * 0.05);//multipy to make it less expensive
			int xA = (int)(c.x + c.w  - Camera.x - GameBase.screenWidth * 0.25);
			int yA = c.y + c.l + 10 - Camera.y ;
			
			
			if((int)(c.x - GameBase.screenWidth*0.5 - 50) < xBound[0] ) {
				xA = (int)(xBound[0] + GameBase.screenWidth*0.25 - Camera.x);
			}
			
			if(c.y +  GameBase.screenWidth/2 + 50 > yBound[1] ) {
				yA = (int)(yBound[1] - (GameBase.screenLength * 0.9) - Camera.y - 10);
			}
			if(moveIndex >=1) {
				g.fillRect(xA, yA, (GameBase.screenWidth/2) ,rowSize * boxHeight);
				g.setColor(java.awt.Color.white);
				g.fillRect(xA + 8, yA + 8 , (int)(GameBase.screenWidth * 0.5 - 16), rowSize  * boxHeight - 16);
				g.setColor(java.awt.Color.black);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 22)); 
			}
			for(int i =1; i <=  boxHeight && i <= moveIndex - 1; i ++) {
				if( enemyArgs.length()>= i * letterPerRow  ) {
				g.drawString(enemyArgs.substring((i-1)*letterPerRow , i * letterPerRow ),
				xA + 80 , yA + ((int)(GameBase.screenLength * 0.04) * i)) ;
				
				}else {
					g.drawString(enemyArgs.substring((i-1)*letterPerRow ),
					xA + 80 , yA + ((int)(GameBase.screenLength * 0.04) * i)) ;
				}
			}
			
			if(moveIndex >=6) {
				if(isWhite)GameBase.panel.setBackground(Color.white);
				else GameBase.panel.setBackground(Color.black);
				
				}
			
		 	}
			
			if(moveIndex >=7 && GameBase.mode == GameBase.GameMode.Confronted || GameBase.mode == GameBase.GameMode.Wild) {
				if(!alreadyChecked)
					loc = GameAn.currentMap.getCharacterLocation(Character.main);
				int fc = (int)Math.ceil(GameAn.currentMap.fitToScreen /2 ) + 1;
				int rowLooperS = loc[0] - fc;
				int colLooperS = loc[1] - fc;
				int rowLooperF = loc[0] + fc;
				int colLooperF = loc[1] + fc;
				if(rowLooperS < 0 )
					rowLooperS = 0;
				if(rowLooperF >  GameAn.currentMap.mapSquare.length)
					rowLooperF = GameAn.currentMap.mapSquare.length;
				if(colLooperS < 0 )
					colLooperS = 0;
				if(colLooperF >  GameAn.currentMap.mapSquare[0].length)
					rowLooperF = GameAn.currentMap.mapSquare[0].length;
				Tiles cS;
				int counter = 0;
				for(int i = 0; i < GameAn.currentMap.mapSquare.length; i++) {
					for(int j = 0; j < GameAn.currentMap.mapSquare[i].length; j++) {
	
						cS = GameAn.currentMap.mapSquare[i][j];
						if(j < loc[1])
							cS.moveAxis(-8, -8);
						else if(j > loc[1])
							cS.moveAxis(8, 8);
						if(loc[1] == j) {
							if(i< loc[0] )
								cS.moveAxis(0, -8);
							else if(i> loc[0])
								cS.moveAxis(0, 8);
							}
						}
					}
			}
			
			
				
			}
			
			
		

	 
	 		
			
		
		
		public void WipeScreen(GameBase.GameMode mode) throws SwithModeError{
			if(mode != GameBase.GameMode.Confronted) 
				throw new SwithModeError(" cant implement transition unless it comes from being confronted");
		};
}
