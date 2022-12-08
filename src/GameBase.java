import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public abstract class GameBase extends JFrame implements  KeyListener, Runnable {

	static myPanel panel;
	int mover = -1;
	boolean shiftOn; 
	static enum GameMode{
		Jorney,
		Battle,
		Confronted,
		Wild
	};
	
	static GameMode mode = GameMode.Jorney;
	
	
	static Thread t;
	
	static public Character[] c = new Character[100];
	static public int  cCounter = 0;

	
	DisplayMode displayMode;
	static int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static int screenLength = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	final int REFRESH_RATE = 16;
	
	public static final int UP          = KeyEvent.VK_UP;
	public static final int DN          = KeyEvent.VK_DOWN;
	public static final int LT          = KeyEvent.VK_LEFT;
	public static final int RT          = KeyEvent.VK_RIGHT;
	
	public static final int _A          = KeyEvent.VK_A;
	public static final int _B          = KeyEvent.VK_B;
	public static final int _C          = KeyEvent.VK_C;
	public static final int _D          = KeyEvent.VK_D;
	public static final int _E          = KeyEvent.VK_E;
	public static final int _F          = KeyEvent.VK_F;
	public static final int _G          = KeyEvent.VK_G;
	public static final int _H          = KeyEvent.VK_H;
	public static final int _I          = KeyEvent.VK_I;
	public static final int _J          = KeyEvent.VK_J;
	public static final int _K          = KeyEvent.VK_K;
	public static final int _L          = KeyEvent.VK_L;
	public static final int _M          = KeyEvent.VK_M;
	public static final int _N          = KeyEvent.VK_N;
	public static final int _O          = KeyEvent.VK_O;
	public static final int _P          = KeyEvent.VK_P;
	public static final int _Q          = KeyEvent.VK_Q;
	public static final int _R          = KeyEvent.VK_R;
	public static final int _S          = KeyEvent.VK_S;
	public static final int _T          = KeyEvent.VK_T;
	public static final int _U          = KeyEvent.VK_U;
	public static final int _V          = KeyEvent.VK_V;
	public static final int _W          = KeyEvent.VK_W;
	public static final int _X          = KeyEvent.VK_X;
	public static final int _Y          = KeyEvent.VK_Y;
	public static final int _Z          = KeyEvent.VK_Z;

	public static final int _1          = KeyEvent.VK_1;
	public static final int _2          = KeyEvent.VK_2;
	public static final int _3          = KeyEvent.VK_3;
	public static final int _4          = KeyEvent.VK_4;
	public static final int _5          = KeyEvent.VK_5;
	public static final int _6          = KeyEvent.VK_6;
	public static final int _7          = KeyEvent.VK_7;
	public static final int _8          = KeyEvent.VK_8;
	public static final int _9          = KeyEvent.VK_9;
	
	public static final int CTRL        = KeyEvent.VK_CONTROL;
	public static final int SHFT        = KeyEvent.VK_SHIFT;
	public static final int ALT         = KeyEvent.VK_ALT;
	
	public static final int SPACE       = KeyEvent.VK_SPACE;
	
	public static final int COMMA       = KeyEvent.VK_COMMA;
	public static final int PERIOD      = KeyEvent.VK_PERIOD;
	public static final int SLASH       = KeyEvent.VK_SLASH;
	public static final int SEMICOLON   = KeyEvent.VK_SEMICOLON;
	public static final int COLON       = KeyEvent.VK_COLON;
	public static final int QUOTE       = KeyEvent.VK_QUOTE;
	
	public static final int F1          = KeyEvent.VK_F1;
	public static final int F2          = KeyEvent.VK_F2;
	public static final int F3          = KeyEvent.VK_F3;
	public static final int F4          = KeyEvent.VK_F4;
	public static final int F5          = KeyEvent.VK_F5;
	public static final int F6          = KeyEvent.VK_F6;
	public static final int F7          = KeyEvent.VK_F7;
	public static final int F8          = KeyEvent.VK_F8;
	public static final int F9          = KeyEvent.VK_F9;
	public static final int F10         = KeyEvent.VK_F10;
	public static final int F11         = KeyEvent.VK_F11;
	public static final int F12         = KeyEvent.VK_F12;
	
	


	
	public GameBase() {
		panel = new myPanel();
		
		this.add(panel);
		t = new Thread(this);
		this.displayMode = new DisplayMode(
					screenWidth,
					screenLength,
					REFRESH_RATE,
					 DisplayMode.REFRESH_RATE_UNKNOWN);
		t.start();
		this.addKeyListener(this);
	
	}
	
	
	 static public void addCharacter(String name, Object[][] list) {
		 c[cCounter] = new Character(0, 0, 60, 69,  name);
		for(int i = 0; i < list.length; i++) {
			Object[] l = list[i];
			c[cCounter].addList((int)l[0], (String)l[1], (String)l[2]);	
		}
		cCounter++;
	}
	 
	 static public void addEnemies(String name, Object[][] list, Box box ) {
		 c[cCounter] = new Enemies(0, 0, 60, 69,  name, box, 200);
		for(int i = 0; i < list.length; i++) {
			Object[] l = list[i];
			c[cCounter].addList((int)l[0], (String)l[1], (String)l[2]);	
		}
		cCounter++;
	}
	 
	 public Graphics getGraphic() {
		 return panel.getGraphics();
	 }
	 
	 
	 abstract void panelPaint(Graphics g);
	
	
		
		
		class myPanel extends JPanel{
			
			
			@Override
		public synchronized void paint(Graphics g) {
				//tree.draw(g);
				//oainter algorith says we must put the stuff that we want in the front in the bottom because it's layers to this.
			panelPaint(g);
			notifyAll();
			}
		//}
		
		public  synchronized void repaintAndWait() {
			repaint();
			try {
				wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		}
		
	abstract public void innerRun();
	
	
	public void run() {
		
	
		FramePractice screen = new FramePractice();
		
		try {
			screen.setFullScreenMode(displayMode, this);
			
			while(true) {
				panel.repaintAndWait();
					try {
						this.innerRun();
						t.sleep(15);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			}finally {
				 screen.restoreScreen();
			 }
		}
	


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int shiftOn(int val) {
		if(shiftOn) return Character.shiftVal + val;
		return val;
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//2D code for running and jumping, now implementing 3D
		if(mode == GameMode.Jorney) {
		int s = Character.shiftVal;
		int k = shiftOn?e.getKeyCode() + s: e.getKeyCode();
		if(k != mover) this.c[0].getActionImage(k);//perform animation
		if(!shiftOn && SHFT == k) shiftOn = true;
		if (k == LT || k == LT + s )mover = shiftOn(LT);
		if (k == RT || k == RT + s) mover = shiftOn(RT);
		if (k == DN || k == DN + s) mover = shiftOn(DN);
		if (k == UP || k == UP + s) mover = shiftOn(UP);
		if	(SPACE == k || k ==SPACE + s) {
			if(mover == -1)mover = 0;
			mover = mover + SPACE;
			mover = shiftOn(mover);
		}
		}
		
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if(shiftOn && SHFT == k) shiftOn = false;
		mover =-1;
		
		
		
	}
	
	
	
	
	
		
}
