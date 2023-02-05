import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * 
 * @author danielweiner
 *
 */
public class Enemy extends Entity {
	
	/**
	 * there will be 4 possible skill levels from 0-3, 0 being a wild
	 * pokemon and 3 being a super skilled trainer. This value determines
	 * the amount of ratings they use when deciding their next move in battle
	 */
	public int skillLevel;
	public ArrayList<CollectableObject> bag = new ArrayList<>();
	
	public Enemy( int skill)
	{
		super();
		this.skillLevel = skill;
	
	}
	
	public void decideAttackAndInflict() {
		Pokemon enemy =  BattleManagerThread.currentB.e.currentPokemon();
				//BattleManager.bM.e.currentPokemon();
		Pokemon myCur =BattleManagerThread.currentB.tp.currentPokemon();
		int getDamage = myCur.calculateDamage(enemy.attackList[0], enemy);
		if(getDamage == 0) {
			GameBase.sm.setFile(6);
        	GameBase.sm.play();
			Battle_UI_Screen.bS.text = String.format("%s%s performed %s and made some effect", enemy.name.substring(0, 1).toUpperCase(),
																								enemy.name.substring(1),
																								enemy.attackList[0]);
		}else {
			GameBase.sm.setFile(10);
        	GameBase.sm.play();
			Battle_UI_Screen.bS.text = String.format("%s%s performed %s and inflicted damage", enemy.name.substring(0,1).toUpperCase(),
					enemy.name.substring(1),
				enemy.attackList[0]);
			
			
		}
		myCur.hp.recieveDamage(getDamage);
		System.out.println("my current stuff is" + myCur.hp.currentHp);
		BattleManagerThread.currentB.trainers[BattleManagerThread.currentB.tp.currentPoke].update();
		Battle_UI_Screen.bS.performedAction();
		
	}
	
	
	public void enemySwitch() {
    	String currentPokemonName = String.format("%s%s", this.currentPokemon().name.substring(0,1).toUpperCase(), this.currentPokemon().name.substring(1).toUpperCase());
    	this.changePokemon(++this.currentPoke);
    	String newPokemonName = String.format("%s%s", this.currentPokemon().name.substring(0,1).toUpperCase(), this.currentPokemon().name.substring(1).toUpperCase());
    	Battle_UI_Screen.bS.text = String.format("%s will be switched out for  %s", currentPokemonName, newPokemonName);
    	Battle_UI_Screen.bS.m = Battle_UI_Screen.Mode.Scav;
    	Battle_UI_Screen.bS.command="";
    	Battle_UI_Screen.bS.bkl.changePokemonChosen = false;
    	BattleBox.currentInstance.LoadBattle();
    	BattleManagerThread.currentB.enemies[BattleManagerThread.currentB.e.currentPoke].update();
    	GameBase.sm.setFile(3);
    	GameBase.sm.play();
    	Battle_UI_Screen.bS.performedAction();
 
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
