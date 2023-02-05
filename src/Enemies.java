import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Enemies extends Character {
	final static int UNREACHABLE_ASCII = 10000;
	static final public int UP = 1 * UNREACHABLE_ASCII;
	static final public int DN = 2 * UNREACHABLE_ASCII;;
	static final public int LT = 3 * UNREACHABLE_ASCII;
	static final public int RT = 4 * UNREACHABLE_ASCII;
	int actionChangeBound;
	final String[] actionState = { "moveX", "moveY", "faceUp",
										"faceDn", "faceLt", "faceRt"};
	int currentState;
	int enemyLoop = 0;
	Box box;
	Point nextPoint;
	static int eneyMovingSpeed;
	static Random r = new Random();
	HashMap<String, HashMap<String, Integer>> actions = new HashMap<>();
	boolean willConfront = true;
	
	

	public Enemies(int x, int y, int w, int h, String name,  Box box,  int actionaChangeBound) {
		super(x, y, w, h, name);
		this.box = box;
		nextPoint  = box.getWayPoint();
		this.x = (int)nextPoint.getX();
		this.y = (int)nextPoint.getY();
		this.defaultMapLoad();
		this.actionChangeBound = actionaChangeBound;
		nextPoint  = box.getWayPoint();
		this.loadActionHashMap();
		this.getActionImage(actions.get(actionState[currentState])
				.get("animation"));
		
	

	}
	
	void loadActionHashMap(){
		
		
		if(this.x > nextPoint.getX()) {
			actions.get(actionState[0]).put("move", -1);
			actions.get(actionState[0]).put("animation", LT);
			
		}else {
			actions.get(actionState[0]).put("move", 1);
			actions.get(actionState[0]).put("animation", RT);
		}
		
		if(this.y > nextPoint.getY()) {
			actions.get(actionState[1]).put("move", -1);
			actions.get(actionState[1]).put("animation", UP);

		}else {
			actions.get(actionState[1]).put("move", 1);
			actions.get(actionState[1]).put("animation", DN);
	
		}
		
		
		
	}
	
	
	boolean confrontCharacter(String cS) {
		Character protaganist = GameBase.c[0];
		if((cS.charAt(4) == 'X' || 
				cS.equals(actionState[4]) ||
					cS.equals(actionState[5])) &&
						Math.abs(protaganist.y - this.y)<= 15 ) {
	
			return (((actions.get(cS).get("animation") == LT) && 
					(protaganist.x - this.x > -(GameAn.currentMap.getTilesWidth())&&
							protaganist.x - this.x <= 0)) ||
					((actions.get(cS).get("animation") == RT) && 
							(protaganist.x - this.x < GameAn.currentMap.getTilesWidth())&&
							protaganist.x - this.x >= 0));
				
		}
		if((cS.charAt(4) == 'Y' || 
				cS.equals(actionState[2]) ||
					cS.equals(actionState[3])) &&
						Math.abs(protaganist.x - this.x)<= 15 ) {
		
			return (((actions.get(cS).get("animation") == UP) && 
					(protaganist.y - this.y > -(GameAn.currentMap.getTileHeight())&&
							protaganist.y - this.y <= 0)) ||
					((actions.get(cS).get("animation") == DN) && 
							(protaganist.y - this.y < GameAn.currentMap.getTileHeight()&&
									protaganist.y - this.y >= 0)));
				
		}
		return false;
	}
	
	
	
	
	//simpler math abs
	private int mb(int num) { return Math.abs(num);}
	
	private boolean reachedNextPointX() {
		return (mb(mb(this.x) - mb((int)nextPoint.getX())) < 10);
	}
	
	private boolean reachedNextPointY() {
		return (mb(mb(this.y) - mb((int)nextPoint.getY())) < 10);
	}
	
	private boolean actionChangeBoundReady() {
		this.enemyLoop++;
		return actionChangeBound == enemyLoop;
	}
	

	
	
	private void defaultMapLoad() {
		for(int i = 0; i < actionState.length; i++) {
			actions.put(actionState[i], new HashMap<String, Integer>());
			actions.get(actionState[i]).put("move", 0);
			actions.get(actionState[i]).put("animation", 0);
		}
		
		actions.get("faceUp").put("animation", this.UP);
		actions.get("faceDn").put("animation", this.DN);
		actions.get("faceLt").put("animation", this.LT);
		actions.get("faceRt").put("animation", this.RT);
}
	
	/*private boolean readyToChangePoint() {
		return this.reachedNextPointX() && this.reachedNextPointY();
	}*/
	
	private boolean reachedSomePoint() {
		return this.reachedNextPointX() || this.reachedNextPointY();
	}
	
	private boolean willChangeDirection(int directioIndex) {
		return (actionState[currentState].equals(actionState[directioIndex]));
	}
	
	private void generateAction() {
		int percentEvaluator  = r.nextInt(10);
		int r0;
		//percent calculation
		
		
		if(percentEvaluator <= 7) {
			r0 = r.nextInt(2);
			
			if(this.reachedSomePoint()) {
				r0 = (r0 + 1) % 2;
			}else {
				if(willChangeDirection(r0)) {
					percentEvaluator  = r.nextInt(10);
					if(percentEvaluator <= 6)
						r0 = currentState;
				}
			}
				
		}else {
	
			
			r0 = r.nextInt(4) + 2;
			
		}
		
		
		currentState = r0;
		this.getActionImage(actions.get(actionState[currentState])
											.get("animation"));
	}
	
	private void resetAction() {
		this.enemyLoop = 0;
	}
	
	void performAction() {
		if(GameBase.mode == GameBase.GameMode.Jorney) {
		if(this.reachedSomePoint()) {
			this.nextPoint = box.getWayPoint();
			this.loadActionHashMap();
		}
		if(this.actionChangeBoundReady()) {
			this.generateAction();
			this.resetAction();

		}
		
		String cS = actionState[this.currentState];
		
		int move = this.actions.get(cS).get("move");
		
			
		if(cS.charAt(4) == 'X') {
			this.vx = eneyMovingSpeed * move;
			this.vy = 0;
		}
		if(cS.charAt(4) == 'Y') {
			this.vx = 0;
			this.vy =  eneyMovingSpeed * move;;
		}
		
		if(cS.indexOf("face") != -1) {
			this.vx = 0;
			this.vy = 0;
		}
		
		if(this.confrontCharacter(cS) && willConfront) {
			Confronts.enemyconfronting = this;
			willConfront = false;
			GameBase.mode = GameBase.GameMode.Confronted;
			
		}
		
		
		this.moveVelocityBased();
		this.vx = 0;
		this.vy = 0;

		}
	}
	
	
	
	
	public void performActionImage(Graphics g, JPanel stuff) {

		this.performAction();

		Image image;
		
		if(moving) {
			 image = a.currentImage();
		}else {
			image = a.stillImage();
		}
		
		
		g.drawImage(image, x-Camera.x, y-Camera.y, w, l, stuff);
		if(GameBase.mode == GameBase.GameMode.Confronted) {
			Confronts.initialConfront(g, stuff);
		}
		
	}

	
	
	
	
	
}
