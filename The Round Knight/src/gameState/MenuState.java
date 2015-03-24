package gameState;

import graphics.Background;
import graphics.ImageLoader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.Game;

public class MenuState implements GameState {

	private Background bg;
	private int currentChoice;
	private String[] options = { "Start", "Help", "Quit"};
	private Color titleColor;
	private Font titleFont, font;
	
	public MenuState() {
		
		init();
		
	}
	
	public void init() {

		currentChoice = 0;
		try{
			
			bg = new Background(ImageLoader.BG,1);
			bg.setMovement(-0.1,0);
			
			titleColor = new Color(128,0,0);
			titleFont = new Font("Century Gothic", Font.PLAIN, 28);
			font = new Font("Arial",Font.PLAIN,12);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update() {

		bg.update();
		
	}

	public void draw(Graphics2D g) {

		// Dibujamos el fondo
		bg.draw(g);
		
		// Dibujamos el título
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString(Game.TITLE,38,70);
		
		// Dibujamos el menú de opciones
		g.setFont(font);
		for(int i = 0 ; i < options.length ; i++) {
			if(i == currentChoice)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.RED);
			g.drawString(options[i], 145, 140 + i * 15);
		}
		
	}

	private void select() {
		
		if(currentChoice == 0) {
			Game.getGame().getGsm().setState(GameStateManager.LEVEL_STATE);
		} else if (currentChoice == 1) {
			
		} else {
			System.exit(0);
		}
		
	}
	
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		
	}

	public void keyReleased(int k) {


		
	}

}
