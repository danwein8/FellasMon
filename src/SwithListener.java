import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwithListener implements KeyListener, ActionListener{

	public static SwithListener swiInstance = new SwithListener();
	
	public void performSwitch() {
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
			  
	        if (e.getKeyCode() == GameBase.UP) {
	        	GameBase.sm.setFile(9);
	        	GameBase.sm.play();
	        	SwitchBox.currentInstance.scrollUp();
	        }
	       
	        if (e.getKeyCode() == GameBase.DN) {
	        	GameBase.sm.setFile(9);
	        	GameBase.sm.play();
	        	SwitchBox.currentInstance.scrollDn();
	        }
	       
	        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	        	
	        			//BattleManager.bM.e.currentPokemon();
	        	PlayerTrainer myCur =BattleManagerThread.currentB.tp;
	        	String currentPokemonName = String.format("%s%s", myCur.currentPokemon().name.substring(0,1).toUpperCase(), myCur.currentPokemon().name.substring(1).toUpperCase());
	        	myCur.changePokemon(SwitchBox.currentInstance.pointing);
	        	String newPokemonName = String.format("%s%s", myCur.currentPokemon().name.substring(0,1).toUpperCase(), myCur.currentPokemon().name.substring(1).toUpperCase());
	        	Battle_UI_Screen.bS.text = String.format("%s will be switched out for  %s", currentPokemonName, newPokemonName);
	        	Battle_UI_Screen.bS.m = Battle_UI_Screen.Mode.Scav;
	        	Battle_UI_Screen.bS.command="";
	        	Battle_UI_Screen.bS.bkl.changePokemonChosen = false;
	        	BattleBox.currentInstance.LoadBattle();
	        	BattleManagerThread.currentB.enemies[BattleManagerThread.currentB.e.currentPoke].update();
	        	GameBase.sm.setFile(3);
	        	GameBase.sm.play();
	        	Battle_UI_Screen.bS.performedAction();
	        }
	        
	        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
	        	GameBase.sm.setFile(7);
	        	GameBase.sm.play();
	        	Battle_UI_Screen.bS.m = Battle_UI_Screen.Mode.Scav;
	        	Battle_UI_Screen.bS.command="";
	        	Battle_UI_Screen.bS.bkl.changePokemonChosen  = false;
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