import java.io.IOException;

public class Common extends Pokemon{

	public Common(String name,  int level)
			throws WrongTypeException, IOException {
		super(name, level);
	
	}
	
	public static void main(String[] args) {
		try {
			Pokemon charmander = new Rare("charmander", 2);
			charmander.printMyStats();
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
