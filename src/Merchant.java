import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * merchant is just meant to sell us a potion, he will have no pokemon
 * @author danielweiner
 *
 */
public class Merchant extends Entity {

	public Merchant() {
		bag.add(new SuperPotion(this));
		bag.add(new SuperPotion(this));
	}
	
	public void sellItem(PlayerTrainer pt) {
		if (!bag.isEmpty()) {
			int price = bag.get(0).getPrice();
			if (pt.getCoins() >= price) {
				bag.remove(0);
				pt.getInventory().add(new SuperPotion(pt));
				pt.setMoney(pt.getCoins() - price);
			}
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCoins() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedImage getImage1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
