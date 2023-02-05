import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BattleKeyListener implements KeyListener, ActionListener{
	public Boolean attackChosen = false;
	public Boolean bagChosen = false;
	public Boolean changePokemonChosen = false;
	public Boolean runChosen = false;
	public Boolean actionChosen = false;
	public static BattleKeyListener battleInstance = new BattleKeyListener();
	
	
	
	
	



	@Override
	public void keyPressed(KeyEvent e) {
		 int code = e.getKeyCode();
	        if (code == KeyEvent.VK_LEFT) {
	        	changePokemonChosen = true;
	        	
	        	attackChosen = false;
	        	bagChosen = false;
	        	runChosen = false;
	        	actionChosen = false;
	        }
	        if (code == KeyEvent.VK_UP) {
	        	attackChosen = true;
	        	
	        	changePokemonChosen = false;
	        	bagChosen = false;
	        	runChosen = false;
	        	actionChosen = false;
	        }
	        if (code == KeyEvent.VK_DOWN) {
	        	bagChosen = true;
	        	
	        	attackChosen = false;
	        	changePokemonChosen = false;
	        	runChosen = false;
	        	actionChosen = false;
	        }
	        if (code == KeyEvent.VK_RIGHT) {
	        	runChosen = true;
	        	
	        	bagChosen = false;
	        	attackChosen = false;
	        	changePokemonChosen = false;
	        	actionChosen = false;
	        }
	        if (code == KeyEvent.VK_ENTER) actionChosen = true;		
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