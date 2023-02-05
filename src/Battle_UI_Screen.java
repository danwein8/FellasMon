import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.Timer;

import javax.swing.JPanel;

//extends JPanel implements Runnable
public class Battle_UI_Screen  {
	//int FPS = 60;
	//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	//int screenWidth = (int)size.getWidth();
	//int screenHeight = (int)size.getHeight();
//	if true, player is choosing their command
	Boolean choosingActionState;
//	if true, player chosen and AI chosen actions are being performed
	Boolean actionPerformanceState;
	Rect BattleWindow = new Rect(0, 0, GameBase.screenWidth - 200, GameBase.screenLength - 200);
//	ImageLayer playerMonsterTile 
	Rect CommandWindow = new Rect(0 ,750, GameBase.screenWidth - 200, GameBase.screenLength - 200);
	
//	The width of the box should always be 300;
	
	final int BACK_TO_NORM = 240;
	final int ENEMY_DECIDE_ATTACK = 120;
	int enemyAttackCounter = 0;
	
	int normCounter = 0;
	boolean normCounterOn = true;
	boolean readyToChooseAnother = true;
	String text = "";
	String command = "";
	public BattleKeyListener bkl = BattleKeyListener.battleInstance;
	public AttackListener atk = AttackListener.atkInstance;
	public SwithListener swi = SwithListener.swiInstance;
	public static Battle_UI_Screen  bS = new Battle_UI_Screen();
	
	enum Mode{
		Scav,
		Attacking,
		Switching
		
	};
	
	Mode m = Mode.Scav;
	
	public Battle_UI_Screen() {
		choosingActionState = true;
		actionPerformanceState = false;
		//battleM = new BattleManager();
		
        //this.addKeyListener(bkl);
		//this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		//this.setBackground(Color.white);
		// This improves games rendering performance
		//this.setDoubleBuffered(true);
		//this.addKeyListener(bkl);
      // BattleUIScreen is 'focused' to receive key input
      //this.setFocusable(true);
	}
	
   /* Thread battle_UI_Thread;
	public void startGameThread(){
        // pass the gameThread to GamePanel
        battle_UI_Thread = new Thread(this);
        // this will start the run method
        battle_UI_Thread.start();
    }*/
    
	/*@Override
	public void run() {
		// 1 billion! Since we're recording in nanoseconds
        double drawInterval = 1000000000/FPS; // 0.0166666 seconds
        // Once we hit nextDrawTime, draw the screen again
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (battle_UI_Thread != null){
            // begin loop!
            long currentTime = System.nanoTime();
            
            // update info such as button presses, animation
            update();
            // draw the screen with the updated info
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                // this is because thread.sleep must take in milliseconds (6 zeroes). 
                // We're using nanoseconds (9 zeroes), so convert it to milliseconds
                remainingTime = remainingTime/1000000;
                // prevent any negative time shenanigans
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
	
	
	
	void resetAfterBattle() {
		this.enemyAttackCounter = 0;
		this.normCounter = 0;
		this.readyToChooseAnother = true;
		choosingActionState = true;
		actionPerformanceState = false;
		this.bkl.actionChosen = false;
		this.bkl.attackChosen = false;
		this.bkl.bagChosen = false;
		this.bkl.changePokemonChosen = false;
		this.bkl.runChosen = false;
		this.text = "";
		this.command = "";
	
	}
	
	
	
	
	static void updateBM() {
	BattleManagerThread.getBM();
	}
	
	void performedAction(){
		Battle_UI_Screen.bS.m = Battle_UI_Screen.Mode.Scav;
    	Battle_UI_Screen.bS.command="";
    	Battle_UI_Screen.bS.bkl.attackChosen = false;
    	Battle_UI_Screen.bS.bkl.actionChosen = false;
    	Battle_UI_Screen.bS.choosingActionState = true;
    	Battle_UI_Screen.bS.actionPerformanceState = false;
    	Battle_UI_Screen.bS.readyToChooseAnother = false;
    	Battle_UI_Screen.bS.normCounter = 0;
    	BattleManagerThread.currentB.changeTurn();
		
	}

	public void update() {
			if(!this.readyToChooseAnother && normCounterOn) normCounter++;
			
			if(this.normCounter == this.BACK_TO_NORM) {
				this.readyToChooseAnother = true;
				normCounter = 0;
			};
			// TODO Auto-generated method stub
			if(BattleManagerThread.currentB.e.currentPokemon().hp.currentHp == 0 && this.readyToChooseAnother ) { 
				if(BattleManagerThread.currentB.e.totalPoke - 1== BattleManagerThread.currentB.e.currentPoke) {
					GameBase.sm.setFile(4);
		        	GameBase.sm.play();
					this.resetAfterBattle();
					GameAn.currentMap.loadMapSquare();
					GameBase.mode = GameBase.GameMode.Jorney;
				}else {
					BattleManagerThread.currentB.e.enemySwitch();
					this.readyToChooseAnother = false;
					normCounter = 0;
				}
			}
				
				
			
			
			
			if(choosingActionState && this.readyToChooseAnother) {

				if(BattleManagerThread.currentB.myTurn) {
				text = "What will you do?";
				if (this.bkl.bagChosen) {
					command = "bag";	
				}
				if (this.bkl.attackChosen) {
					
					command = "attack";
					text = "choosing Attack";
					m = Mode.Attacking;
				}
				if (this.bkl.runChosen) {
					command = "run";
				}
				if (this.bkl.changePokemonChosen) {
					command = "change pokemon";
					m = Mode.Switching;
				}
				if (this.bkl.actionChosen) {
	//				turn choosingActionState off, turn actionPerformanceState on
					choosingActionState = false;
					actionPerformanceState = true;
					this.readyToChooseAnother = false;
				}
				}else {
					if(this.enemyAttackCounter < this.ENEMY_DECIDE_ATTACK ) {
						text = "Your Oponent is turn";
						this.enemyAttackCounter++;
					}else {
						BattleManagerThread.currentB.e.decideAttackAndInflict();
						this.enemyAttackCounter = 0;
						
					}
					
				}
			}
			else if (actionPerformanceState){
				switch(command) {
				case "bag":
	//				Open the bag
					text = "You open your bag...";
					break;
				case "attack":
					
	//				Do the attack sequence
					/*
					Pokemon p = enemy_hud.pokemon;
					Pokemon m = player_hud.pokemon;
					String attackName = "water-gun";
					text = m.name.substring(0,1).toUpperCase() + m.name.substring(1) + " end this mother loving fool with the Almiiiiigty " + attackName.toUpperCase(); 
					int dg = p.calculateDamage(attackName, m);
					p.hp.recieveDamage(dg);
					enemy_hud.update();
					*/
					break;
				case "run":
					text = "You get away safely!";
	//				...leave the program, I guess?
					break;
				case "change pokemon":
					text = "You prepare to switch...";
	//				Load up Ajani's pokemon switch ui
					break;
				}
				this.bkl.actionChosen = false;
				 choosingActionState = true;
				actionPerformanceState = false;
			}	
	}
	
	
	public void draw(Graphics l) {
		
		Graphics2D g = (Graphics2D)l;
		
		float thickness = 5;
		
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(thickness));
		g.setColor(Color.gray);
		g.drawRect(25, 25, GameBase.screenWidth - 50, GameBase.screenLength -50);
		Color lightG = new Color(208, 208, 225, 175);
		g.setColor(lightG);
		g.fillRect(52, 842,GameBase.screenWidth - 92, 231);
    	g.setColor(Color.CYAN); 
    	g.drawRect(50, 840, GameBase.screenWidth - 90, 235);
    	BattleManagerThread.currentB.trainers[BattleManagerThread.currentB.tp.currentPoke].draw(g);
    	BattleManagerThread.currentB.enemies[BattleManagerThread.currentB.e.currentPoke].draw(g);
    	g.setColor(Color.black); 
    	
    	
    	if(this.command == "attack") {
    		BattleBox.currentInstance.draw(g);
    	}
    	
    	if(this.command == "change pokemon") {
    		SwitchBox.currentInstance.draw(g);
    	};
    	

    	Font font = new Font("Verdana", Font.BOLD, 20);
    	g.setFont(font);
    	if (choosingActionState) {
    		g.drawString(text, 215, 925);
    		g.drawString("Attack", 1320, 920);
    		g.drawString("Pokemon", 1200, 955);
    		g.drawString("Run", 1400, 955);
    		g.drawString("Bag", 1325, 1005);
    	}
    	if(actionPerformanceState) {
    		g.drawString(text, 15, 825);

    	}

        // dispose of graphics context and release any system resources
        // that it may be using
        g.dispose();
	}
    /*public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	g.setColor(Color.gray);
    	BattleWindow.draw(g);
    	g.setColor(Color.CYAN); 
    	CommandWindow.draw(g);
    	g.setColor(Color.black); 
    	player_hud.draw(g);
    	enemy_hud.draw(g);
    	

    	Font font = new Font("Verdana", Font.BOLD, 20);
    	g.setFont(font);
    	if (choosingActionState) {
    		g.drawString(text, 15, 625);
    		g.drawString("Attack", 1120, 590);
    		g.drawString("Pokemon", 1000, 625);
    		g.drawString("Run", 1200, 625);
    		g.drawString("Bag", 1125, 675);
    	}
    	if(actionPerformanceState) {
    		g.drawString(text, 15, 625);

    	}

        // dispose of graphics context and release any system resources
        // that it may be using
        g.dispose();*/
    }
