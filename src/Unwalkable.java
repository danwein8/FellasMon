import java.awt.*;
import javax.swing.*;

public class Unwalkable extends Tiles {
	
	public Unwalkable(int x, int y , int w, int l, String tileImageName) {
		super(x, y, w, l, tileImageName);
		this.hasWayPoints = false;
		
	}
	
	@Override
	Graphics tileAction(Graphics g, Character c) {
		g.setColor(Color.RED);
		g.fillRect(10,10,10,10);
		if(this.overLaps(c)) { 
			this.directionlCollisionResponse(c); 
		}
		return g;
	};
	
	public void directionlCollisionResponse(Character c) {
		
			Character.speedVal *= 2;
			if(this.collisionFromTop(c)) {
				c.setVelocity( this.vx, -Character.speedVal);
				Camera.setVelocity(this.vx, -Character.speedVal);
			}
			if(this.collisionFromBottom(c)) {
				c.setVelocity( this.vx, Character.speedVal);
				Camera.setVelocity( this.vx, Character.speedVal);
			}
			if(this.collisionFromLeft(c)) {
				c.setVelocity( Character.speedVal, this.vy);
				Camera.setVelocity( Character.speedVal, this.vy);
			}
			if(this.collisionFromRight(c)) {
				c.setVelocity( -Character.speedVal, this.vy);
				Camera.setVelocity( -Character.speedVal, this.vy);
			}
			c.moveVelocityBased();
			Camera.moveVelocityBased();
			
	}
			
	public boolean collisionFromTop(Character c) {
			return ((c.x >= x) &&
						(c.x <= (x + w)) &&
							c.y < y);
	}
	
	
	public boolean collisionFromLeft(Character c) {
		return ((c.y + c.l >= y) &&
					(c.y + c.l <= (y + l)) &&
					c.x + c.w > x + w);
	}		
		
	public boolean collisionFromRight(Character c) {
		return ((c.y >= y) &&
				(c.y <= (y + l)) &&
					c.x < x);
	}
	
public boolean collisionFromBottom(Character c) {
		
		return ((c.x + c.w >= x) &&
					(c.x + c.w <= (x + w)) &&
						c.y + c.l > y+ l);
	}


	

}
