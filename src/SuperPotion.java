import java.awt.Graphics2D;

/**
 * 
 * @author danielweiner
 *
 */
public class SuperPotion extends CollectableObject {
	
	public int strength = 50;

	public SuperPotion(Entity ownerIn) {
		super(ownerIn);
		
		setName("Super Potion");
		setValue(10);
		setDescription("[" + getName() + "]\nRestores " + getValue() + " health");
		setPrice(20);
	}
	
	public void use()
	{
		// use potion
		sm.setFile(2);
		sm.play();
		entity.changePokeHealth(strength);
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		
	}

}
