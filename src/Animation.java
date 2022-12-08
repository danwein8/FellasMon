import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {
	Image[] image;
	int current = 0;
	final int duration = 10;
	int delay = duration;
	
	public Animation(String name, String ani, int count, String ext) {
		image = new Image[count];
		for(int i=0; i < count; i++) {
			try {
				
				image[i] = ImageIO.read(getClass().getResource(name + "_" + ani + "_" + i +"." +  ext));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Image currentImage() {
		//System.out.println("thos ppne " + current);
		if(delay ==0) {
			current++;
			if(current == image.length) current =0;
			delay = duration;
		}
		delay--;
		return image[current];
	}
	
	
	public Image stillImage() {
		return image[0];
	}
}
