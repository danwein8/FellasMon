import javax.swing.*;
import java.awt.*;
public class Rect  {

	
	public int x;
	public int y;
	public int w;
	public int l;
	public int vx;
	public int vy;
	public int ax;
	public int ay;
	
	public Rect(int x, int y, int w, int l ) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.l = l;
	}
	
	
	public void draw(Graphics g) {
		g.drawRect(0+ x - Camera.x, 0 + y - Camera.y, w,l);
		
	}
	
	
	public void moveAxis(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void setVelocity(int x, int y) {
		this.vx = x;
		this.vy = y;
	}
	
	public void setAcceleration(int x, int y) {
		this.ax = x;
		this.ay = y;
	}
	
	public void moveVelocityBased() {
		x += vx;
		y += vy;
		
		vx = ax;
		vy = ay;
	}
	
	public void moveV() {
		x+= vx;
		y += vy;
	}
	
	public void hitVertical() {
		vx = vx * -1;
		vy = vy * -1;
	}
	
	public void hitHorizontal() {
		vy = vy * -1;
	}

	



	public boolean overLaps(Rect r) {
		return (x + w >= r.x) &&
				(r.x + r.w >= x) &&
				(r.y + r.l >= y) &&
				(y + l >= r.y);
	}
}
