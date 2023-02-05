
import javax.swing.JFrame;

public class Battle_UI_Frame {
	public static void main (String [] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("***BATTLE SCREEN***");

        Battle_UI_Screen battleUIScreen = new Battle_UI_Screen();
        //window.add(battleUIScreen);

        // window will be resized to fit the preferred size and layout of its subcomponents
        // (ITC, GamePanel)
        window.pack();

        // window will be set at middle of the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        //battleUIScreen.startGameThread();
    }
}
