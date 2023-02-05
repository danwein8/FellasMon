import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author danielweiner
 *
 */
public class SubNode extends AStarNode 
{
	/**
	 * this goal node needs to be defined somehow, I'm not sure this all works if we
	 * don't have a tangible target node to search for, so i have to find a way to 
	 * define a win
	 */
	SubNode goalNode;
	/**
	 * use the GameState item as the object that holds all the variables that SubNode 
	 * needs to calculate the cost of any node
	 */
	GameState nodeState;
	/**
	 * keep all neighbors in an ArrayList data member so we can add and subtract them as
	 * needed
	 */
	private ArrayList<SubNode> neighbors;
	
	
	public SubNode(SubNode parent, GameState currentState)
	{
		if (parent != null)
		{
			pathParent = parent;
			costFromStart = parent.costFromStart + getCost(parent);
		}
		else
		{
			pathParent = null;
			costFromStart = 0;
		}
		/**
		 * set the goal node to zero health for the enemy poke, and zero 
		 * reserve poke, this way we have a tangible goal that we can shoot for.
		 */
		goalNode.nodeState.self = currentState.self;
		goalNode.nodeState.enemy = currentState.enemy;
		goalNode.nodeState.enemyPokeHealth = 0;
		goalNode.nodeState.enemyReservePoke = 0;
	}
	
	
	
	/**
	 * Builds the list of neighbors for the AStarNode representation.
	 * The neighbors are all the possible moves the AI can make from
	 * this current GameState.
	 */
	public void buildNeighborList() {
		/**
		 * with all this information compiled we can take this neighbors list and rate each node as useful
		 * or not useful in our A* algorithm, then we can make a choice based on the highest rated one
		 */
		neighbors = new ArrayList();
		PlayerTrainer tempTrainer;
		Enemy tempAI;
		Pokemon pokeA = nodeState.self.currentPokemon(); 
		String[] attacks = pokeA.attackList;
		// we are going to add all the possible attacks of the current pokemon to the neighbor list
		// these are all attacks we can perform
		for (int i = 0; i < attacks.length; i++)
		{
			// I instantiate a new object as to not change any values of the original
			tempTrainer = nodeState.enemy;
			// once I have a copy of the enemy state I test all attacks against their pokemon
			tempTrainer.currentPokemon().hp.magnitude -= tempTrainer.currentPokemon().allAttackMap.get(attacks[i]);
			neighbors.add(new SubNode(this, new GameState(tempTrainer, nodeState.self)));
		}
		
		// we add all of the current items available in the bag to the neighbor list
		// these are all items we can use
		for (int i = 0; i < nodeState.self.bag.size(); i++)
		{
			// I make a copy of the AI
			tempAI = nodeState.self;
			// go thru and use all its items once
			tempAI.bag.get(i).use();
			// add a new neighbor for each item
			neighbors.add(new SubNode(this, new GameState(nodeState.enemy, tempAI)));
		}

		// we add all the reserve pokemon into the neighbor list as long as health is > 0 and its not the current pokemon
		// these are all the pokemon we could switch to, to test their type strengths and weaknesses
		for (int i = 0; i < nodeState.self.pokemon.length; i++)
		{
			// I make a copy of the AI
			tempAI = nodeState.self;
			// if the pokemon were looking at isn't K.O.ed
			if (tempAI.pokemon[i].hp.magnitude > 0)
			{
				// if the pokemon were looking at is the current pokemon
				if (tempAI.pokemon[i] != tempAI.currentPokemon()) {
					// change to that pokemon
					tempAI.changePokemon(i);
					// add a new neighbor for each possible switch
					neighbors.add(new SubNode(this, new GameState(nodeState.enemy, tempAI)));
				}
			}
		}

		// Trim to size then remove references to this node.
		// Ensures extra capacity for calls to addNeighbor()
		// without enlarging the array capacity)
		neighbors.trimToSize();
		while(neighbors.remove(this));
	}
	
	
	
	/**
	 * builds a neighbor list for the selected subnode but only adds possible attacks
	 */
	public void buildAttackNeighborList() {
		/**
		 * with all this information compiled we can take this neighbors list and rate each node as useful
		 * or not useful in our A* algorithm, then we can make a choice based on the highest rated one
		 */
		neighbors = new ArrayList();
		PlayerTrainer tempTrainer;
		Enemy tempAI;
		Pokemon pokeA = nodeState.self.currentPokemon(); 
		String[] attacks = pokeA.attackList;
		// we are going to add all the possible attacks of the current pokemon to the neighbor list
		// these are all attacks we can perform
		for (int i = 0; i < attacks.length; i++)
		{
			// I instantiate a new object as to not change any values of the original
			tempTrainer = nodeState.enemy;
			// once I have a copy of the enemy state I test all attacks against their pokemon
			tempTrainer.currentPokemon().hp.magnitude -= tempTrainer.currentPokemon().allAttackMap.get(attacks[i]);
			neighbors.add(new SubNode(this, new GameState(tempTrainer, nodeState.self)));
		}

		// Trim to size then remove references to this node.
		// Ensures extra capacity for calls to addNeighbor()
		// without enlarging the array capacity)
		neighbors.trimToSize();
		while(neighbors.remove(this));
	}
	
	
	
	/**
	 * builds a neighbor list for the selected subnode but only adds possible pokemon switches
	 */
	public void buildPokemonNeighborList() {
		/**
		 * with all this information compiled we can take this neighbors list and rate each node as useful
		 * or not useful in our A* algorithm, then we can make a choice based on the highest rated one
		 */
		neighbors = new ArrayList();
		PlayerTrainer tempTrainer;
		Enemy tempAI;
		Pokemon pokeA = nodeState.self.currentPokemon(); 
		String[] attacks = pokeA.attackList;

		// we add all the reserve pokemon into the neighbor list as long as health is > 0 and its not the current pokemon
		// these are all the pokemon we could switch to, to test their type strengths and weaknesses
		for (int i = 0; i < nodeState.self.pokemon.length; i++)
		{
			// I make a copy of the AI
			tempAI = nodeState.self;
			// if the pokemon were looking at isn't K.O.ed
			if (tempAI.pokemon[i].hp.magnitude > 0)
			{
				// if the pokemon were looking at is the current pokemon
				if (tempAI.pokemon[i] != tempAI.currentPokemon()) {
					// change to that pokemon
					tempAI.changePokemon(i);
					// add a new neighbor for each possible switch
					neighbors.add(new SubNode(this, new GameState(nodeState.enemy, tempAI)));
				}
			}
		}

		// Trim to size then remove references to this node.
		// Ensures extra capacity for calls to addNeighbor()
		// without enlarging the array capacity)
		neighbors.trimToSize();
		while(neighbors.remove(this));
	}
	
	
	
	/**
	 * builds a neighbor list for the selected subnode but only adds possible item uses
	 */
	public void buildItemNeighborList() {
		/**
		 * with all this information compiled we can take this neighbors list and rate each node as useful
		 * or not useful in our A* algorithm, then we can make a choice based on the highest rated one
		 */
		neighbors = new ArrayList();
		PlayerTrainer tempTrainer;
		Enemy tempAI;
		Pokemon pokeA = nodeState.self.currentPokemon(); 
		String[] attacks = pokeA.attackList;
		
		// we add all of the current items available in the bag to the neighbor list
		// these are all items we can use
		for (int i = 0; i < nodeState.self.bag.size(); i++)
		{
			// I make a copy of the AI
			tempAI = nodeState.self;
			// go thru and use all its items once
			tempAI.bag.get(i).use();
			// add a new neighbor for each item
			neighbors.add(new SubNode(this, new GameState(nodeState.enemy, tempAI)));
		}

		// Trim to size then remove references to this node.
		// Ensures extra capacity for calls to addNeighbor()
		// without enlarging the array capacity)
		neighbors.trimToSize();
		while(neighbors.remove(this));
	}
	
	
	
	public void addNeighbor(AStarNode node) {
		if (neighbors == null) buildNeighborList();
		neighbors.add((SubNode)node);
	}
	
	
	public void removeNeighbor(AStarNode node) {
		if (neighbors == null) buildNeighborList();
		neighbors.remove(node);
	}
	
	
	// ASTARNODE METHODS
	
	/**
	 * this is like the function below but singular, only for the cost spanning 2 nodes, where the
	 * function below can span any number of nodes
	 */
	@Override
	public float getCost(AStarNode node) {
		/**
		 * This cost gets calculated PER-NODE in the GameState class then this
		 * function calculates the cost between the implicit node and the specified
		 * neighbor node
		 * 
		 * Cost between this node and the specified neighbor node defined as:
		 * 
		 * my pokemon has less health = higher cost
		 * enemy pokemon has less health = lower cost
		 * no change/higher health not taken into account
		 * 
		 * I have less reserve pokemon = higher cost
		 * enemy has less reserve pokemon = lower cost
		 * no change/revived pokemon not taken into account
		 * 
		 * my poke type is strong against enemy poke type = lower cost
		 * enemy poke type is strong against my poke type = higher cost (this should cause
		 * a switch in higher skill trainers if better option available)
		 * no type advantage has no effect on cost
		 * 
		 * my poke is lower level than enemy poke = higher cost (this should cause
		 * a switch in higher skill trainers if better option available)
		 * my poke is higher level than enemy poke = lower cost
		 * poke within 3 or 4 levels will have no effect on cost
		 * 
		 */
		return getEstimatedCost(node);
	}

	/**
	 * this function asks the question "after this move, are you in a better position?" so it
	 * works in conjunction with the build neighbors list function above, and the find path function
	 * from AStarSearch to build a graph of possible moves, and rate them by which puts the AI in
	 * the best position. this takes into account the skill level of the AI so not all factors are
	 * considered every time.
	 */
	@Override
	public float getEstimatedCost(AStarNode node) {
		/**
		 * here we need to calculate the best path to a winning GameState.
		 * We will accomplish that with the comparisons of the AStarNode class
		 * and the A* search method of the AStarSearch class, we just have to
		 * make sure all the necessary information is available for those classes
		 * to function as expected
		 */
		float estimate = 0;
		// if the AStarNode explicit parameter is a SubNode
		if (node instanceof SubNode) {
			// cast it to a SubNode
			SubNode other = (SubNode)node;
			// if the skill level is 0 aka its a wild pokemon, we just return a random rating thats
			// anywhere in the possible rating system involving attacks
			if (nodeState.self.skillLevel < 1) {
				Random rand = new Random(System.currentTimeMillis());
				rand.nextInt(1 - (-4) + (-4));
			}
			// if the current enemy pokemon has less health after the move
			// if AI skill level is high enough decrement cost by 1
			if (nodeState.enemy.getHP() > other.nodeState.enemy.getHP())
				if (nodeState.self.skillLevel > 0) estimate--;
			// if one of the enemy pokemon gets K.O.ed after the move
			// if AI skill level is high enough decrement cost by 3
			if (nodeState.enemy.reservePoke > other.nodeState.enemy.reservePoke)
				if (nodeState.self.skillLevel > 0) estimate -= 3;
			// if our pokemon loses health after the move,
			// and if AI skill level is high enough increment the cost
			if (nodeState.self.getHP() > other.nodeState.self.getHP())
				if (nodeState.self.skillLevel > 1) estimate++;
			// if one of our pokemon gets K.O.ed after the move,
			// and if AI skill level is high enough increment the cost by 3
			if (nodeState.self.reservePoke > other.nodeState.self.reservePoke)
				if (nodeState.self.skillLevel > 1) estimate += 3;
			// check if this pokemon is weak against the enemy pokemon
			// if AI skill level is high enough increment cost by 2
			if (nodeState.self.currentPokemon().isWeakAgainst(nodeState.enemy.currentPokemon()))
				if (nodeState.self.skillLevel > 2) estimate += 2;
			// check if this pokemon is strong against the enemy pokemon
			// if AI skill level is high enough decrement cost by 2
			if (nodeState.enemy.currentPokemon().isWeakAgainst(nodeState.self.currentPokemon()))
				if (nodeState.self.skillLevel > 2) estimate -= 2;
			// if AI pokemon is lower level than player pokemon
			// if AI skill level is high enough increment cost by 2
			if (nodeState.enemy.currentPokemon().level > nodeState.self.currentPokemon().level)
				if (nodeState.self.skillLevel > 2) estimate += 2;
			// if AI pokemon is higher level than player pokemon
			// if AI skill level is high enough decrement cost by 2
			if (nodeState.self.currentPokemon().level > nodeState.enemy.currentPokemon().level)
				if (nodeState.self.skillLevel > 2) estimate -= 2;
		}
		return 0;
	}

	@Override
	public List getNeighbors() {
		/**
		 * neighbor nodes are any nodes that are only one move away, so if we 
		 * are at the initial GameState, all the neighbor nodes are:
		 * 1. every attack that can be made 
		 * 2. every item that can be used 
		 * 3. every pokemon that can be switched to
		 * 4. run from the fight
		 * 
		 * its just everything that you can do this exact turn
		 */
		if (neighbors == null) {
            buildNeighborList();
        }
        return neighbors;
	}
	
	/**
	 * Split up version of the getNeighbors method
	 * @return List of all possible attacks this turn
	 */
	public List getAttackNeighbors() {
		/**
		 * neighbor nodes are any nodes that are only one move away, so if we 
		 * are at the initial GameState, all the neighbor nodes are:
		 * 1. every attack that can be made 
		 * 
		 * its just everything that you can do this exact turn
		 */
		if (neighbors == null) {
            buildAttackNeighborList();
        }
        return neighbors;
	}
	
	/**
	 * Split up version of the getNeighbors method
	 * @return List of all possible pokemon swaps this turn
	 */
	public List getPokemonNeighbors() {
		/**
		 * neighbor nodes are any nodes that are only one move away, so if we 
		 * are at the initial GameState, all the neighbor nodes are:
		 * 1. every pokemon that can be switched to
		 * 
		 * its just everything that you can do this exact turn
		 */
		if (neighbors == null) {
            buildPokemonNeighborList();
        }
        return neighbors;
	}
	
	/**
	 * Split up version of the getNeighbors method
	 * @return List of all possible items this turn
	 */
	public List getItemNeighbors() {
		/**
		 * neighbor nodes are any nodes that are only one move away, so if we 
		 * are at the initial GameState, all the neighbor nodes are:
		 * 1. every item that can be used 
		 * 
		 * its just everything that you can do this exact turn
		 */
		if (neighbors == null) {
            buildItemNeighborList();
        }
        return neighbors;
	}

}
