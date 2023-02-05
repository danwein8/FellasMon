
public class BattleManager {
	PlayerTrainer tp;
	Enemy e;
	boolean myTurn;
	static BattleManager bM;
	Battle_UI_Pokemon_HUD[] enemies;
	Battle_UI_Pokemon_HUD[] trainers;
	
	

	
	
	public BattleManager() {
		e  = new Enemy(1);
		
		tp = PlayerTrainer.trainerInstance;
		myTurn = whoGoesFirst();
		bM = this;
		enemies = new Battle_UI_Pokemon_HUD[e.totalPoke];
		trainers = new Battle_UI_Pokemon_HUD[tp.totalPoke];
		for(int i = 0; i < e.totalPoke; i++) {
			enemies[i] = new Battle_UI_Pokemon_HUD(260,200, 300, 100, e.pokemon[i]);
		}
		
		for(int i = 0; i < tp.totalPoke; i++) {
			trainers[i] =  new Battle_UI_Pokemon_HUD(1200,500, 300, 100, tp.pokemon[i]);
		}

		
	}
	
	boolean whoGoesFirst() {
		return tp.currentPokemon().s.magnitude > e.currentPokemon().s.magnitude;
	}
	
	void switchPokemon(int i, boolean me) {
		if(me)tp.changePokemon(i);
		else e.changePokemon(i);
		this.changeTurn();
	}
	
	void attack(int attackIndex, boolean me) {
		int damage = 0;
		Pokemon p = tp.currentPokemon();
		Pokemon d = e.currentPokemon();
		if(me) {
			damage = d.calculateDamage(p.attackList[attackIndex], p );
			e.changePokeHealth(damage);
		}else {
			damage = p.calculateDamage(d.attackList[attackIndex], d );
			tp.changePokeHealth(damage);
		}
		this.changeTurn();
	}
	
	
	
	
		
	
	 void changeTurn() {
		this.myTurn = !myTurn;
	}
		
	

	

}
