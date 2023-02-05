import java.util.ArrayList;

public class BattleManagerThread implements Runnable {
	
	static ArrayList<BattleManager> bm =new ArrayList<BattleManager>();
	Thread t;
	static BattleManager currentB;
	
	
	
	public BattleManagerThread(){
		t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		 
	        while(bm.size() < 5) {
	         BattleManager bro = new BattleManager();
	         bm.add(bro);
	         System.out.println("you added");
	         try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
	}
	
	static void  getBM() {
		currentB = bm.get(bm.size() -1 );

		bm.remove(bm.size() -1 );
		
	}
	
	

	
}
