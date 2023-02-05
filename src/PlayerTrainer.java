import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author danielweiner
 *
 */
public class PlayerTrainer extends Entity {
	
	private ArrayList<CollectableObject> bag = new ArrayList<>();
	
	static PokemonList mainPokeList;
	static PlayerTrainer trainerInstance = new PlayerTrainer();
	
	
	public PlayerTrainer()
	{
			
			try {
				Pokemon s = new Common("squirtle", 45);
				Pokemon e = new Common("eevee", 30);
				Pokemon p = new Common("pikachu", 50);
				Pokemon c = new Common("charmander", 40);
				Pokemon ch = new Common("charmeleon", 30);
				Pokemon cha = new Common("charizard", 50);
				s.generateAttackList();
				e.generateAttackList();
				p.generateAttackList();
				c.generateAttackList();
				ch.generateAttackList();
				cha.generateAttackList();
				
				PokemonList l = new PokemonList();
				
				
				
				
				
				l.add(p);
				l.add(e);
				l.add(s);
				l.add(c);
				l.add(ch);
				l.add(cha);
				
				
				mainPokeList = l;
				
				this.totalPoke = l.numOfCurrentPokemons; 
				this.pokemon = l.p;
			} catch (WrongTypeException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Pokemon ra = new Common("raichu", 20);
			
			
			
			//l.add(cha);
			//l.add(ra);
			
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<CollectableObject> getInventory()
	{
		return bag;
	}
	
	public void setInventory(ArrayList<CollectableObject> bagIn)
	{
		bag = bagIn;
	}

	@Override
	public void speak() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCoins() {
		// TODO Auto-generated method stub
		return money;
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
