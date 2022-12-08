import java.io.IOException;

public class SuperRare extends Pokemon {

	public SuperRare(String name,  int level, String type)
			throws WrongTypeException, IOException {
		super(name, level, type);
	
	}
	
	public static void main(String[] args) {
		try {
			Pokemon squirtle = new Rare("squirtle", 100, Pokemon.W);
			Pokemon charmanders = new Rare("charmander", 100, Pokemon.F);
			Pokemon.loadTypes();
			
			
			System.out.println(" the damage done is " + charmanders.calculateDamage("tackle", squirtle));
			
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
