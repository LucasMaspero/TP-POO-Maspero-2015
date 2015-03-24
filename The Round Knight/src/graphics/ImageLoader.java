package graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage BG = load("/Backgrounds/menubg.gif");
	
	private static BufferedImage load(String s) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(ImageLoader.class.getResourceAsStream(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	
}
