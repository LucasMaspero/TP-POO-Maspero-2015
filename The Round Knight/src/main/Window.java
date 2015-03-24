package main;

import javax.swing.JFrame;

public class Window {

	public static void main(String[] args) {
		
		JFrame window = new JFrame(Game.TITLE);
		window.setContentPane(Game.getGame());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
	}
	
}
