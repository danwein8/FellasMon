
/**
 * 
 * @author danielweiner
 *
 */
public class GameState {
	
	/**
	 *  these will be the two participants of the battle that make up any GameState.
	 *  TODO: change all variable values to be relative to these variables, like 
	 *  instead of reservePoke, do self.reservePoke etc.
	 */
	public PlayerTrainer enemy;
	public Enemy self;
	/**
	 * reserve pokemon that the AI trainer has, if AI is a wild pokemon this == 0
	 */
	public int reservePoke;
	/**
	 * reserve pokemon that the player trainer has
	 */
	public int enemyReservePoke;
	/**
	 * percentage of health that the AI controlled pokemon has, this value will be scaled down
	 * as to not effect the decision more than any other variable
	 */
	public int pokeHealth;
	/**
	 * percentage of health the players pokemon has, this value will be scaled down as to not
	 * effect the decision more than any other variable
	 */
	public int enemyPokeHealth;
	/**
	 * AI controlled pokemon type, used to check match-up against player pokemon
	 */
	public String pokeType;
	/**
	 * player controlled pokemon type, used to check match-up against AI pokemon
	 */
	public String enemyPokeType;
	/**
	 * AI controlled pokemon level, used to check match-up against player pokemon
	 */
	public int pokeLevel;
	/**
	 * player controlled pokemon level, used to check match-up against AI pokemon
	 */
	public int enemyPokeLevel;
	
	static int attackUsedIndex;
	
	/**
	 * The class that will have all the information for the pathfinding algorithm to make an
	 * informed decision for the AIs next move based on the current state of the battle.
	 * Measures several factors based on the level of the AI trainer, meaning more factors will
	 * be taken into account the higer the level of the AI trainer. If a wild pokemon is
	 * the AI, the factors will essentially be ignored and a random attack will be chosen
	 * since its supposed to be "wild"
	 * @param enemy - the enemy that is encountered
	 */
	public GameState(PlayerTrainer enemy, Enemy self)
	{
		/**
		 * if wild pokemon, reservePoke = 0
		 * pokeHealth can be < 100 if pokemon has damage from previous fight
		 * these factors are used to decide the value of the state
		 */
		enemyReservePoke = enemy.reservePoke;
		enemyPokeHealth = enemy.pokeHealth;
		enemyPokeType = enemy.currentPokemon().type;
		enemyPokeLevel = enemy.currentPokemon().getLevel();
		reservePoke = self.reservePoke;
		pokeHealth = self.pokeHealth;
		pokeType = self.currentPokemon().type;
		pokeLevel = self.currentPokemon().getLevel();
	}
}
