import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AttackListener implements KeyListener, ActionListener{

	public static AttackListener atkInstance = new AttackListener();
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
			  
	        if (e.getKeyCode() == GameBase.UP) {
	        	BattleBox.currentInstance.scrollUp();
	        	GameBase.sm.setFile(9);
	        	GameBase.sm.play();
	        	
	        }
	       
	        if (e.getKeyCode() == GameBase.DN) {
	        	BattleBox.currentInstance.scrollDn();
	        	GameBase.sm.setFile(9);
	        	GameBase.sm.play();
	        
	        }
	        
	        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
	        	
	        	
	        	Pokemon enemy =  BattleManagerThread.currentB.e.currentPokemon();
	        			//BattleManager.bM.e.currentPokemon();
	        	Pokemon myCur =BattleManagerThread.currentB.tp.currentPokemon();
	        	int atkIns = BattleBox.currentInstance.pointing;
	        	int getDamage = enemy.calculateDamage(myCur.attackList[atkIns], myCur);
	        	if(getDamage == 0) {
	        		GameBase.sm.setFile(6);
		        	GameBase.sm.play();
	        		Battle_UI_Screen.bS.text = String.format("%s%s performed %s and made some effect", myCur.name.substring(0,1).toUpperCase(),
	        																							myCur.name.substring(1),
	        																							myCur.attackList[atkIns]);
	        
	        	}else {
	        		GameBase.sm.setFile(10);
		        	GameBase.sm.play();
	        		Battle_UI_Screen.bS.text = String.format("%s%s performed %s and inflicted damage", myCur.name.substring(0,1).toUpperCase(),
							myCur.name.substring(1),
						myCur.attackList[atkIns]);
	        		
	        		
	        	}
	        	enemy.hp.recieveDamage(getDamage);
	        	BattleManagerThread.currentB.enemies[BattleManagerThread.currentB.e.currentPoke].update();
	        	Battle_UI_Screen.bS.performedAction();
	        	
	        	
	        }
	        
	        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
	        	Battle_UI_Screen.bS.m = Battle_UI_Screen.Mode.Scav;
	        	Battle_UI_Screen.bS.command="";
	        	Battle_UI_Screen.bS.bkl.attackChosen = false;
	        	GameBase.sm.setFile(7);
	        	GameBase.sm.play();
	        	
	        	
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