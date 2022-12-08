import java.awt.*;

public class ImageLayer {
	Image image;
	
	int depth;
	
	double zoom = 1;
	
	int w = 720;
	int h = 360;
	
	
	
	public ImageLayer(String filename, int depth) {
		this.image = Toolkit.getDefaultToolkit().getImage(filename);
		this.depth = depth;
	}
	
	public void zoomIn(double dzoom) {
		zoom *= dzoom;
	}
	
	public void zoomOut(double dzoom) {
		zoom /= 1 + dzoom;
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
