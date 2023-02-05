
public class HP  extends Stats{
	
	int currentHp;
	
	public HP(int base, Pokemon p) {
		super(base, p);
		this.setCurrentHp(this.magnitude);
	}
	
	public void setCurrentHp(int c) {
		this.currentHp = c;
	}
	
	public void recieveDamage(int damage) {
		this.currentHp = this.currentHp - damage;
		if(this.currentHp < 0) this.currentHp = 0;//at this point you're dead bro.... dead... your mortal. fool... weakling.... done... graveyard
	}
	
	public void addHealth(int amount) {
		this.currentHp += amount;
		if(this.currentHp > this.magnitude) this.currentHp = this.magnitude;
	}
	
	double getHpPer() {
		return (int)((double)this.currentHp/(double)this.magnitude);
	}
	
	
	
	//magnitude is meant to be updated after every battle and level up 
	@Override
	void calculateMagnitude(Pokemon p){
		double effortValue =  Math.sqrt(65025);
		double innerNum =(2 * base + individualValue + (effortValue / 4)) * p.level;
		this.magnitude = (int) ((innerNum / 100) + 10 + p.level);
	}
	
	
}


