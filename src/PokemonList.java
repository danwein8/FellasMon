import java.io.IOException;

public class PokemonList {
	Pokemon[] p;
	int numOfCurrentPokemons = 0;
	final int MAX_POKEMON = 6;
	
	public PokemonList() {
		p = new Pokemon[MAX_POKEMON];
	}
	
	
	public void remove(int index) {
		if(index <= numOfCurrentPokemons) {
			Pokemon[] newP = new Pokemon[MAX_POKEMON];;
			p[index] = null;
			this.numOfCurrentPokemons--;
			int counter = 0;
			for(int i = 0; i < this.MAX_POKEMON; i++) {
				if(p[i] != null) {
					newP[counter++] = p[i];
				}
			}
			this.p = newP;
		}
		
	}
	
	public void add(Pokemon po) {
		if(this.numOfCurrentPokemons == this.MAX_POKEMON) {
			this.remove(5);
			this.add(po);
		}else {
			p[this.numOfCurrentPokemons++] = po;
			
		}
	}
	
	public void switches(Pokemon po, int index) {
		this.remove(index);
		this.add(po);
	}
	
	public void fillRandomly(String loc) {
		int gN = RandomPokemonGenerator.rand.nextInt(5) + 1;
		RandomPokemonGenerator.setCurrentLocation(loc);
		for (int i = 0; i < gN; i++) {
			RandomPokemonGenerator.generate();
			RandomPokemonGenerator.currentRandomPokem.generateAttackList();
			this.add(RandomPokemonGenerator.currentRandomPokem);
			
		}
		
	}
	
	public static  void main(String[] args) {
		try {
			Pokemon s = new Common("squirtle", 20);
			Pokemon e = new Common("eevee", 20);
			Pokemon p = new Common("pikachu", 20);
			Pokemon c = new Common("charmander", 20);
			Pokemon ch = new Common("charmeleon", 20);
			Pokemon cha = new Common("charizard", 20);
			Pokemon ra = new Common("raichu", 20);
			PokemonList l = new PokemonList();
			
			/*l.add(p);
			l.add(e);
			l.add(s);
			l.add(c);
			l.add(ch);
			l.add(cha);
			l.add(ra);*/
			
			//l.remove(2);
			l.fillRandomly("route-1");
			for(int i = 0; i < l.numOfCurrentPokemons; i++) {
				System.out.println(l.p[i].name + "and their levels are " + l.p[i].level);
			}
		} catch (WrongTypeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
}
