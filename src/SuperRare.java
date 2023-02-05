import java.io.IOException;

public class SuperRare extends Pokemon {

	public SuperRare(String name,  int level)
			throws WrongTypeException, IOException {
		super(name, level);
	
	}
	
	public static void main(String[] args) {
		Pokemon.generateMapForAttacks();
		try {
			Pokemon squirtle = new Rare("squirtle", 100);
			Pokemon charmanders = new Rare("charmander", 100);
			
			Pokemon.loadTypes();
			squirtle.generateAttackList();
			for(int i = 0; i < squirtle.attackList.length; i++) {
				System.out.println(squirtle.attackList[i]);
			}
			
			
			
			
			System.out.println(" the damage done is " + charmanders.calculateDamage("flamethrower", squirtle));
			System.out.println(squirtle.isWeakAgaints(charmanders));
			
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
