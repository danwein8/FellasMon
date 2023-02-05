
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
	static int lastCoorsX;
	static int lastCoorsY;
	
	
	public static  void setCamera(Character c) {
		x = -(GameBase.screenWidth/2) + (c.w);
		y = -(GameBase.screenLength/2) + (c.l);
	}
	
	public static void resetCharacterCam() {
		x =lastCoorsX;
		y = lastCoorsY; 
	}
	
	public static void set2Origin(int _x, int _y) {
		lastCoorsX = x;
		lastCoorsY = y;
		 x= _x;
		 y = _y;
		 
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

		Xon = !(w + Math.abs(c.x) + 50 >= Hbound || w + c.x + c.w + 50 >= Hbound );
		Yon = !(l + Math.abs(c.y) + 50 >= Vbound || l + c.y + c.l + 50 >= Vbound  );

		}
	}
	