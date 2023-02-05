// By Andrew Ohakam
// UI for the Pokemons name and health during battle
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Battle_UI_Pokemon_HUD extends Rect{

	Image platform = Toolkit.getDefaultToolkit().getImage("/images/battleplatform.png");
	int currentHealth;
	int maxWidth;
	Rect healthBar;
//	the gray outline that appears behind healthBar. this should never change in size
	Rect healthBarBG;

	Pokemon pokemon;
	
	
//	Pokemon poke should be a parameter here
	public Battle_UI_Pokemon_HUD(int x, int y, int w, int l, Pokemon pokemon) {
		super(x, y, w, l);
//		Healthbar is the only bar that changes throughout the game
		this.pokemon = pokemon;
		maxWidth = w-30;
		healthBar = new Rect(x+10, y+10, maxWidth, 6);
		healthBarBG = new Rect(x+10, y+10, w-25, 6);
	
	}
	
	
	
	
//	reduce the healthBar's width based on pDiff
	public void removeHealthBits(double pDiff) {
		//System.out.println(pDiff + " for some percentage");
		//System.out.println(healthBar.w + " here before");
		double g = maxWidth*pDiff;
		//System.out.println(g);
		this.healthBar.w = (int)g;
		//System.out.println(healthBar.w + " here after");
	}
	
	public void addHealthBits(int value) {
		
	}
	
	public void update() {
		//Pokemon health should be compared to max health. if 
		//difference between the two is not == 0.00, update the 
		//length of health bar
		
		
		double percentageDifference = (double)pokemon.hp.currentHp / (double)pokemon.hp.magnitude;
	if (percentageDifference < 1.0) {
		removeHealthBits(percentageDifference);
		}
	}
	
	public void draw(Graphics g) {
//		Color and draw HUD Box
		Color b = new Color(153, 0, 0, 150);
		g.setColor(b);
		g.fillRect(x+1, y+1, w-2, l-2);
		g.drawRect(x, y, w, l);
//		Color and draw healthBG
		g.setColor(Color.gray);
		g.fillRect(x+10, y+10, w-30, 6);
		healthBarBG.draw(g);
//		color and draw healthbar
		g.setColor(Color.green);
		g.fillRect(x+10, y+10, healthBar.w, 6);
		healthBar.draw(g);
		
//		Color and draw pokemon name
		g.setColor(Color.black);
        Font pokemonNameFont = new Font("Verdana", Font.BOLD, 20);
        g.setFont(pokemonNameFont);
		g.drawString(this.pokemon.name, this.x + 10, this.y + 50);
		g.drawImage(platform, 300, 300, null);
	}
	
}