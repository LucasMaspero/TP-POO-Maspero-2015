package main;

import gameInterfaces.GameFundamental;
import gameState.GameStateManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable, KeyListener, GameFundamental {

	// Singleton
	private static Game instance;
	
	// Serializable
	private static final long serialVersionUID = 1L;

	// Título del juego
	public static final String TITLE = "The Round Knight";
	
	// Dimensiones de la ventana
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 480;

	// Thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// Game State Manager
	private GameStateManager gsm;
	
	// Imágenes
	private Graphics2D g;
	private BufferedImage image;
	
	private Game() {
		
		setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
		setFocusable(true);
		requestFocus();
	
	}
	
	public static Game getGame() {
		
		if(instance == null) {
			instance = new Game();
		}
		return instance;
		
	}
	
	public void addNotify() {
		
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
		
	}
	
	public void run() {
		
		long start, elapsed, wait;
		
		// Iniciales ..
		init();
		
		// Game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw(g);
			drawToScreen();
			
			elapsed = start - System.nanoTime();
			
			wait = targetTime - elapsed / 1000000; // wait debe estar en milisegundos
			
			// Si targetTime es mayor a elapsed, seteamos wait en un valor pequeño 
			if(wait < 0) {
				wait = 5;
			}
			
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public void init() {
		
		// Lienzo
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
	}

	public void update() {
		
		gsm.update();
		
	}

	public void draw(Graphics2D g) {

		gsm.draw(g);
		
	}
	
	public void drawToScreen() {
		
		// Lienzo
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
		
	}

	public void keyPressed(KeyEvent k) {

		gsm.keyPressed(k.getKeyCode());
		
	}

	public void keyReleased(KeyEvent k) {

		gsm.keyPressed(k.getKeyCode());
		
	}

	public void keyTyped(KeyEvent k) {

		
		
	}

	public GameStateManager getGsm() {
		return gsm;
	}
	
}
