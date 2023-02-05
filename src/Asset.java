import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Asset will be used by all Entities and Objects
 * @author danielweiner
 *
 */
public interface Asset {
    void update();

    void draw(Graphics2D graphics2D);

    String getName();

    void speak();
    
    int getCoins();

    BufferedImage getImage1();

    String getDescription();

    void use();

    int getPrice();
}
