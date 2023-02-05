import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomPokemonGenerator {
	
	static Pokemon currentRandomPokem;
	
	static String currentLocation;
	
	static Random rand = new Random();
	
	
	static ArrayList<String[]> localFellas;
	
	
	public static void setCurrentLocation(String location) {
		currentLocation = location;
		try {
			localFellas = RequestInfo.listOfFellasInArea(location);//loads maps 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static private int  parseLevel(String range) {
		String[] r = range.split("-");
		
		int level1 = Integer.valueOf(r[0]);
		if(r.length < 2) return  level1;
		int level2 = Integer.valueOf(r[1]);
		return rand.nextInt(level2 - level1) + level2;
		
	}
	
	static public int determineLevel(String range) {
		if(range.length() == 1)
			return Integer.valueOf(range);
		return parseLevel(range);
	}
	
	static public String[] generateRandomPokemon() {
		int r = rand.nextInt(localFellas.size());
		return localFellas.get(r);
	}
	
	static public void generate() {
		String[] pokemonWLev = generateRandomPokemon();
		String pokemonName = pokemonWLev[0];
		int level =  determineLevel(pokemonWLev[1]);
		try {
			Pokemon p = new Common(pokemonName,
					level);
			currentRandomPokem = new Common(pokemonName,
					level);
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		RandomPokemonGenerator.setCurrentLocation("route-1");
		RandomPokemonGenerator.generate();
		System.out.println(RandomPokemonGenerator.currentRandomPokem.name + " and this level is " + 
				RandomPokemonGenerator.currentRandomPokem.level	);
	}
	
	
	

}
