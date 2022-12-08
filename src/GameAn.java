import javax.imageio.ImageIO;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class GameAn extends GameBase {
	static Mapper currentMap;
	Rect l = new Rect(30,40, 400, 400);
	
	public GameAn() {
		super();

		Camera.setCamera(Character.main);
	}
	
	
	
	@Override
	void panelPaint(Graphics g) {
		if(currentMap == null) {
			try {
				currentMap = new Mapper("text.txt", 5, 4);
			} catch (IOException | NotEnoughTilesError e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		if(mode == GameBase.GameMode.Jorney || mode == GameBase.GameMode.Confronted) {
	
			Camera.cameraPowerSwitch(currentMap.getHBounds()[1],
										currentMap.getVBounds()[1],
																c[0]);
			currentMap.draw(g, Character.main);
		}
		
		if(mode == GameBase.GameMode.Jorney) {
			for(int i = 0; i < cCounter; i++) {
				c[i].performActionImage(g, panel);
			}
		}
		
		if(mode == GameBase.GameMode.Confronted) {
			Character.main.performActionImage(g, panel);
			Confronts.enemyconfronting.performActionImage(g, panel);
		}
		
		if(mode == GameBase.GameMode.Wild) {
			Character.main.performActionImage(g, panel);
		
		}
			
		}

	@Override
	public void innerRun() {
		Character.main.move(mover);
	}
	
	static int centralize(int coordinate, int amount) {
		return coordinate - coordinate;
	}

	public static void main(String[] args) {
		//********************** Pre Loaders **********************************
		
		Enemies.eneyMovingSpeed = 1;
		Object[][] bro = {{KeyEvent.VK_RIGHT, "wr", "png"},{KeyEvent.VK_RIGHT + Character.shiftVal, "wr", "png"},
				{KeyEvent.VK_LEFT, "wl", "png"}, {KeyEvent.VK_LEFT + Character.shiftVal, "wl", "png"},
				{KeyEvent.VK_DOWN, "wf", "png"}, {KeyEvent.VK_DOWN + Character.shiftVal, "wf", "png"},
				{KeyEvent.VK_UP, "wb", "png"},{KeyEvent.VK_UP+ Character.shiftVal, "wb", "png"}} ;
	
		GameAn.addCharacter( "jamilton", bro);
		Character.main = c[0];
		new Confronts();
		Pokemon.loadTypes();
		
		
		 
		
		 GameAn test = new GameAn();
		 test.run();
		 }
		

}
