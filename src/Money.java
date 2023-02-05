import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * 
 * @author danielweiner
 *
 */
public class Money extends CollectableObject {

	public Money(Entity ownerIn) {
		super(ownerIn);
		
		setName("Poke Coin");
		setValue(1);
		setDescription("[" + getName() + "]\nA coin worth " + getValue());
	}
	
	public void use()
	{
		// pick up coin
		sm.setFile(1);
		sm.play();
		entity.setMoney(entity.getCoins() + 1);
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		
	}
}
