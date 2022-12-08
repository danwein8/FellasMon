import javax.swing.*;
import java.awt.*;

public class FramePractice {
	
	private GraphicsDevice device;
	
	public FramePractice(){
		GraphicsEnvironment enviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 device = enviroment.getDefaultScreenDevice();

	}
	
	public void setFullScreenMode(DisplayMode displayMode, JFrame window) {
		window.setUndecorated(true);
		window.setResizable(false);
		
		device.setFullScreenWindow(window);
		if (displayMode != null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			}catch(IllegalArgumentException ex) {
				
			}
			
		}
		
		
	}
	
	public Window getFullScreenWindow() {
		return device.getFullScreenWindow();
	}
	
	
	public void restoreScreen() {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			window.dispose();
		}
		device.setFullScreenWindow(null);
	}
	
	
	
	

}
