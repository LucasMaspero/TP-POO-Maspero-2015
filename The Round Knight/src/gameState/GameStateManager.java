package gameState;

import gameInterfaces.GameFundamental;
import gameInterfaces.KeyManager;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameStateManager implements GameFundamental, KeyManager {
	
	// Esta clase maneja las "etapas" del juego.
	private GameState[] gameStates;
	private int currentState;
	
	// Estados del juego
	public static final int NUM_GAME_STATES = 2;
	public final static int MENU_STATE = 0;
	public final static int LEVEL_STATE = 1;
	
	public GameStateManager() {
		
		init();
		
	}
	
	public void init() {
	
		gameStates = new GameState[NUM_GAME_STATES];
		
		// El juego inicia con el estado de menú
		currentState = MENU_STATE;
		loadState(currentState);
		
	}
	
	public void update() {

		gameStates[currentState].update();
		
	}
	
	public void draw(Graphics2D g) {

		gameStates[currentState].draw(g);
		
	}
	
	private void loadState(int state) {
		
		if(state == MENU_STATE) {
			gameStates[state] = new MenuState();
		}
		if(state == LEVEL_STATE) {
			gameStates[state] = new LevelState();
		}
		
	}
	
	private void unloadState(int state) {
		
		gameStates[state] = null;
		
	}

	public void setState(int state) {
		
		unloadState(currentState);  // Liberamos el estado anterior
		currentState = state;	
		loadState(currentState);	// Para cargar el nuevo estado
		
	}
	
	public void keyPressed(int keycode) {

		gameStates[currentState].keyPressed(keycode);
		
	}

	public void keyReleased(int keycode) {

		gameStates[currentState].keyReleased(keycode);
		
	}
	
}
