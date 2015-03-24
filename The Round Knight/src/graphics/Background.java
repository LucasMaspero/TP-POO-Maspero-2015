package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;
import main.GamePanel;

public class Background {

	private BufferedImage image;
	
	private double x, y, dx, dy, moveScale;
	
	public Background(BufferedImage image, double moveScale) {
		
		this.image = image;
		this.moveScale = moveScale;
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % Game.WINDOW_WIDTH;
		this.y = (y * moveScale) % Game.WINDOW_HEIGHT;
	}
	
	public void setMovement(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image,(int)x,(int)y,null);
		if(x < 0) {
			g.drawImage(image,(int)x + Game.WINDOW_WIDTH,(int)y,null);
		}
		if(x > 0) {
			g.drawImage(image,(int)x + Game.WIDTH,(int)y,null);
		}
	}
	
}
