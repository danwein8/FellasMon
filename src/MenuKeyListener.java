import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyListener implements KeyListener, ActionListener{

	public static MenuKeyListener menuInstance = new MenuKeyListener();
	
	
	
	
	
	


	@Override
	public void keyPressed(KeyEvent e) {
		 int code = e.getKeyCode();
		 int pN = PlayerTrainer.mainPokeList.numOfCurrentPokemons;
		 int aIS = rowsInsideCol.activeIndexSelected ;
	        if (code == GameBase.LT) {
	        	if(aIS > 2) rowsInsideCol.activeIndexSelected -= 3;
	        }
	        if (code == GameBase.UP) {
	        	if(aIS > 0) rowsInsideCol.activeIndexSelected -= 1;
	        }
	        if (code == GameBase.DN) {
	        	if(aIS < pN) rowsInsideCol.activeIndexSelected += 1;
	        
	        }
	        if (code == GameBase.RT) {
	        	if(aIS < 3 ) rowsInsideCol.activeIndexSelected += 3;
	        
	        }
	       		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}}