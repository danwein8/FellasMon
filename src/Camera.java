
public class Camera
{
	static int x;
	static int y;
	static int z = 1;
	static int vx;
	static int vy;
	static int ax = 0;
	static int ay = 0;
	static boolean Xon = true;
	static boolean Yon = true;
	
	
	public static  void setCamera(Character c) {
		x = -(GameBase.screenWidth/2) + (c.w);
		y = -(GameBase.screenLength/2) + (c.l);
	}
	
	
	
	public static void setVelocity(int x, int y) {
		vx = x;
		vy = y;
	}
	
	public void setAcceleration(int x, int y) {
		this.ax = x;
		this.ay = y;
	}
	
	public static void moveVelocityBased() {
		if(Xon) x += vx;
		if(Yon) y += vy;
		
		vx = ax;
		vy = ay;
	}
	
	public static void moveIn(int dz)
	{
		z -= dz;
	}
	
	public static void moveOut(int dz)
	{
		z += dz;
	}
	
	public static void cameraPowerSwitch( int Hbound, int Vbound, Character c) {
		int w = (int)(GameBase.screenWidth/2);
		int l = (int)(GameBase.screenLength/2);
		
		
		
		
		Xon = !(w + Math.abs(c.x) >= Hbound || w + c.x + c.w >= Hbound );
		Yon = !(l + Math.abs(c.y) >= Vbound || l + c.y + c.l >= Vbound  );
		
			

			
		}
	}
	