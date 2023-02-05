import javax.imageio.ImageIO;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameAn extends GameBase {
	static Mapper currentMap;
	Rect l = new Rect(30,40, 400, 400);
	MenuScreen men;
	
	

	
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
		
		//game modes will decide in what part of the game are we drawing and performing draw which carry most of calcs
		
		
		if(mode == GameBase.GameMode.Jorney || mode == GameBase.GameMode.Confronted || mode == GameBase.GameMode.Wild) {
	
			Camera.cameraPowerSwitch(currentMap.getHBounds()[1],
										currentMap.getVBounds()[1],
																c[0]);
			currentMap.draw(g, Character.main);//keeps the current map to know which one to draw
			Character.main.performActionImage(g, panel);
		}
		
		if(mode == GameBase.GameMode.Jorney) {
			for(int i = 1; i < cCounter; i++) {
				c[i].performActionImage(g, panel);
			}
		}
		
		if(mode == GameBase.GameMode.Confronted) {
			Confronts.enemyconfronting.performActionImage(g, panel);
		}
		
		if(mode == GameBase.GameMode.Wild) {
			
			Confronts.initialConfront(g, GameBase.panel);
		
		}
		
		if(mode == GameBase.GameMode.Menu ) {
			currentMap.draw(g, Character.main);
			men.draw(g);
		}
		
		if(mode == GameBase.GameMode.Battle ) {
			
			Battle_UI_Screen.bS.draw(g);
		}
			
		}

	@Override
	public void innerRun() {

		Character.main.move(mover);
		if(mode == GameBase.GameMode.Battle) Battle_UI_Screen.bS.update();
	}
	
	@Override
	public void screenAdder(KeyEvent s) {
		boolean alreadyExecuted = false;
		if(mode == GameBase.GameMode.Battle) {
			if(Battle_UI_Screen.bS.m == Battle_UI_Screen.Mode.Scav ) BattleKeyListener.battleInstance.keyPressed(s);
			else if(Battle_UI_Screen.bS.m == Battle_UI_Screen.Mode.Attacking) AttackListener.atkInstance.keyPressed(s);
			else if (Battle_UI_Screen.bS.m == Battle_UI_Screen.Mode.Switching) SwithListener.swiInstance.keyPressed(s);
		}
		if(s.getKeyCode() == _E ) {
			if(mode == GameMode.Jorney && !alreadyExecuted) {
				GameBase.mode = GameBase.GameMode.Menu;
				alreadyExecuted = !alreadyExecuted;
				men = new MenuScreen(2,  .06,  .08);
				Camera.set2Origin(men.startX, men.startY);
			}
			if(mode == GameMode.Menu && !alreadyExecuted) {
				GameBase.mode = GameBase.GameMode.Jorney;
				alreadyExecuted = !alreadyExecuted;
				Camera.resetCharacterCam();
				
			}
		}
		
		if(mode == GameMode.Menu) {
			MenuKeyListener.menuInstance.keyPressed(s);
		}
		
		
	}
	
	
	
		
	
	static int centralize(int coordinate, int amount) {
		return coordinate - coordinate;
	}
	public static void main(String[] args) {
		
		//********************** Pre Loaders **********************************
		//we load all the data game will need to start
		Enemies.eneyMovingSpeed = 1;
		Object[][] bro = {{RT, "wr", "png"},{RT + Character.shiftVal, "wr", "png"},
				{LT, "wl", "png"}, {LT + Character.shiftVal, "wl", "png"},
				{DN, "wf", "png"}, {DN+ Character.shiftVal, "wf", "png"},
				{UP, "wb", "png"},{UP+ Character.shiftVal, "wb", "png"}} ;
	
		GameAn.addCharacter( "jamilton", bro);
		Character.main = c[0];
		new Confronts();
		Pokemon.loadTypes();
		Pokemon.generateMapForAttacks();
		sm.setFile(0);
		
		//sm.play();
		//sm.loop();
		
		
		 GameAn test = new GameAn();
		 BattleManagerThread bmt = new BattleManagerThread();
		 //test.run();
		 
	}
		 
		 

}
