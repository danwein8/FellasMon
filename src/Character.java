
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class Character extends Rect {
	
	
	//************************** CONSTANTS*****************************
	final static int shiftVal = 1000;
	final static int run = 14;
	final static int walk = 4;
	final static int jumpTime = 20;
	final static int gravity = 3;
	final static int  jumpIni = jumpTime - gravity;
	final static int  jumpCeiling = jumpIni * 30;// max jump
	
	
	//****************************INSTANCT**********************
	String name;
	Object[][] anArr = new Object[5][4];
	int anArrCounter =0;
	Animation a;
	boolean moving = false;
	public static int speedVal;
	static Character main;
	
	
	
	//air Values
	boolean inAir = false;
	int moveHVal = 0;
	int jumpMid = 0;//this reads our time in between jumps
	int dropVal = 0;
	
	
	
	
	public Character(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		this.addList(-1, "s", "png");
		this.getActionImage(-1);		
	}
	
	
	public Character(int x, int y, int w, int h,  String name) {
		super(x, y, w, h);
		this.name = name;
		this.addList(-1, "s", "png");
		this.getActionImage(-1);
	}
	
	
	public void jump(int vx, int vy) {
		inAir =true;
		setVelocity(vx, -vy);
		jumpMid += jumpIni;
		this.ay = gravity;	
	}
	
	public void dropSensor(int code) {
		setVelocity(moveHVal, this.vy);
		if(code  != KeyEvent.VK_SPACE && jumpMid > 0) {
			dropVal += gravity;
			if(dropVal >= jumpMid) {
				inAir =	false;
				dropVal = 0;
				setAcceleration(0,0);
				setVelocity(0,0);
			}
		}
	}
	
	private void checkAirStatus(int code) {
		if(!inAir) {
			inAir = (code == GameBase.LT + GameBase.SPACE || 
						code == GameBase.RT + GameBase.SPACE ||
							code == GameBase.SPACE);
			jumpMid = 0;
		}
		
	}
	
	public void move(int code) {
		moving = (code >= 0);
		if(code < 0) code ++;
		if(code > shiftVal) {
			speedVal = run;
			code = code - shiftVal;
		}else speedVal = walk;
		
		this.checkAirStatus(code);
	
		if(code == GameBase.LT || code == GameBase.LT + GameBase.SPACE  ) {
			moveHVal =inAir? -speedVal * 2: 0;
			setVelocity(-speedVal,this.vy);
			code = code - GameBase.LT;
			Camera.setVelocity(-speedVal,this.vy);
		}
		if(code == GameBase.UP || code == GameBase.UP + GameBase.SPACE) {
			setVelocity(0,-speedVal);
			code = code - GameBase.UP;
			Camera.setVelocity(0,-speedVal);
		}
		
		if(code == GameBase.DN || code == GameBase.DN + GameBase.SPACE) {
			setVelocity(0,speedVal);
			code = code - GameBase.DN;
			Camera.setVelocity(0,speedVal);
		}
		if(code == GameBase.RT || code == GameBase.RT + GameBase.SPACE)  {
			moveHVal =inAir ? speedVal * 2: 0;
			setVelocity(speedVal,this.vy);
			code = code - GameBase.RT;
			Camera.setVelocity(speedVal,this.vy);
		}
		
		if(inAir) dropSensor(code);
		else setVelocity(this.vx,this.vy);
		
		
		if(code  == GameBase.SPACE && (jumpMid <= jumpCeiling )) this.jump(moveHVal, jumpTime);

		this.moveVelocityBased();
		Camera.moveVelocityBased();
	}
	
	
	//
	private void expandArray() {
		Object[][] temp = new Object[anArrCounter * 2][4];
		for(int i =0; i < anArr.length; i++) {
			for (int j =0; j < anArr[i].length; j++) {
				temp[i][j] = anArr[i][j];
			}
		}
		
		anArr = temp;
	}
	
	
	
	//this is in charge of apending movements to charactors to move
	public void addList(int keyCode, String aniCategory, String ext ) {
		if(anArr.length == anArrCounter) {
			expandArray();
		}
		
		Object[] arr = new Object[4];
		int numOfImages = FileActions.getCount(this.name, ext,  aniCategory);
		System.out.println(numOfImages);
		arr[0] = Integer.valueOf(keyCode);
		arr[1] = aniCategory;
		arr[2] = Integer.valueOf(numOfImages);//count
		arr[3] = ext;
		
		anArr[anArrCounter] = arr;
		anArrCounter ++;
	}
	
	//this is in charge of loading what specific actions the character will perform in the perform actionImage
	
	public void getActionImage( int keyCode) {
		
			for(int i = 0; i < anArrCounter; i++) {
				
				if(keyCode == (int)anArr[i][0]) {
					//cast values cause array is an objects in order to store diffirent data types
					a = new Animation(this.name, (String)anArr[i][1], (int)anArr[i][2], (String)anArr[i][3] );
				}
			}
		}
	
	
	// ths will perform the character is drawing of his current movement	
	public void performActionImage(Graphics g, JPanel stuff) {
		Image image;
		if(moving) {
			 image = a.currentImage();
		}else {
			image = a.stillImage();
		}
		
		
		
		g.drawImage(image, x-Camera.x, y-Camera.y, w, l, stuff);
		
	}
		
		
	
	
	
	
}
