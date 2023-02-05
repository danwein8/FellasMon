import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * 
 * @author danielweiner
 *
 */
public abstract class CollectableObject implements Asset {
	
	String name;
	String description;
	// sell price
	int value;
	// buy price
	int price;
	// where it is in the world if its laying on the ground
	int worldX, worldY;
	SoundManager sm = new SoundManager();
	Entity entity;
	
	// sprite
	BufferedImage image1;
	
	public CollectableObject(Entity ownerIn) {  
		entity = ownerIn;
	}

	/**
	 * draws the object relative to the players location
	 */

	/*public void draw(Graphics2D g2D) {
		// Dummy values for drawing items in the world
		// this is gonna be Jonathans thing since he made the map
		int screenX = worldX - Character.x;
		int screenY = worldY - Character.x;
		
		g2D.drawImage(image1, screenX, screenY, getTileSize(), getTileSize(), null);
	}*/


	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void pickUp() {
		entity.bag.add(this);
		// remove item from world
	}
	
	public void drop() {
		entity.bag.remove(this);
		// add item to world
	}

	public int getWorldX() {
		// TODO Auto-generated method stub
		return worldX;
	}

	public int getWorldY() {
		// TODO Auto-generated method stub
		return worldY;
	}

	public void setWorldX(int i) {
		// TODO Auto-generated method stub
		this.worldX = i;
	}

	public void setWorldY(int i) {
		// TODO Auto-generated method stub
		this.worldY = i;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public int getCoins() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedImage getImage1() {
		// TODO Auto-generated method stub
		return image1;
	}
	
	public void setImage1(BufferedImage image)
	{
		this.image1 = image;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return price;
	}
	/**
	 * not used here
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * not used here
	 */
	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * not used here
	 */
	@Override
	public void speak() {
		// TODO Auto-generated method stub
		
	}

}
