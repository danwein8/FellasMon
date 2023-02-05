import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

import java.awt.image.*;
import java.io.IOException;


/**
 * Entity class will be inherited by any enemy trainer, the player character, and NPCs.
 * This class is essentially a framework for us to be able to put all the trainer/enemy information
 * in one place so we can reference it all right here during battle so we don't need to access the 
 * raw Pokemon class or raw item class directly.
 * @author danielweiner
 *
 */
public abstract class Entity implements Asset {
	
	
	// character info
	public String name;
	
	// objects
	public final int inventorySize = 10;
	public ArrayList<CollectableObject> bag = new ArrayList<>();
	public int money;
	
	/**
	 * amount of reserve pokemon available, cannot be > 5 (1 out and 5 in the backpack)
	 */
	public int reservePoke = 0;
	/**
	 * the total pokemon this entity has, wild pokemon only have 1 (themselves), trainers
	 * can not have > 6
	 */
	public int totalPoke = 0;
	/**
	 * the index of the currently fighting pokemon
	 */
	public int currentPoke = 0;
	/**
	 * an array of Pokemon objects representing all of the 
	 */
	public Pokemon[] pokemon = new Pokemon[totalPoke];
	/**
	 * a percentage representation of the health of the pokemon, needed for the rendering
	 * of the health bars
	 */
	public int pokeHealth = 100;
	
	
	public Entity()
	{
		PokemonList pl = new PokemonList();
		pl.fillRandomly("route-1");
		
		this.totalPoke = pl.numOfCurrentPokemons; 
		this.pokemon = pl.p;
	}
	
	public void changePokemon(int newPokeIndex)
	{
		currentPoke = newPokeIndex;
	}
	
	public Pokemon currentPokemon()
	{
		return pokemon[currentPoke];
	}
	
	public int getHP()
	{
		return currentPokemon().hp.currentHp;
	}
	
	public int getAttackDmg()
	{
		// TODO: get the current attacks damage
		return 20;
				//currentPokemon().getAttackData(0)
	}
	
	public void changePokeHealth(int change)
	{
		if (pokemon[currentPoke].hp.currentHp + change > pokemon[currentPoke].hp.magnitude)
		{
			pokemon[currentPoke].hp.currentHp = pokemon[currentPoke].hp.magnitude;
			pokeHealth = 100;
		}
		if (pokemon[currentPoke].hp.currentHp + change < 0)
		{
			pokemon[currentPoke].hp.currentHp = 0;
			if(reservePoke > 0) reservePoke--;
			pokeHealth = 0;
		}
		else
		{
			pokemon[currentPoke].hp.magnitude += change;
			pokeHealth = (pokemon[currentPoke].hp.currentHp / pokemon[currentPoke].hp.magnitude) * 100;
		}
	}
	
	/**
	 * All these entities need to be able to be drawn
	 * @param g - the Graphics object
	 */
	public void draw(Graphics g)
	{
		// nothing done here, this gets done once inherited
	}
	
	/**
	 * allows the NPCs to speak to the player character
	 */
	public void speak()
	{
		// nothing done here, this gets done once inherited
	}

	/**
	 * this function sets up any images needed as sprites, it can read them in and scale them to the 
	 * correct size
	 * @param imagePath - the file name of the sprite, ".png" is auto added
	 * @param width - scaled width
	 * @param height - scaled height
	 * @return - scaled image ready for use
	 */
	public BufferedImage setup(String imagePath, int width, int height)
	{
		BufferedImage image = null;

		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int coins) {
		this.money = coins;
	}

	public ArrayList<CollectableObject> getInventory() {
		return bag;
	}

	public void setInventory(ArrayList<CollectableObject> inventory) {
		this.bag = inventory;
	}

	public int getMaxInventorySize() {
		return inventorySize;
	}

}
