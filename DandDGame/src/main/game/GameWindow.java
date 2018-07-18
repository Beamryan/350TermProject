package main.game;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public final class GameWindow {
	
	/**
	 * 
	 */
	private GameWindow() {
		
	}
	
	/**
	 * 
	 * @param args arguments for main method.
	 * @throws IOException 
	 */
	public static void main (final String[] args) throws IOException {
		
		JFrame mainFrame = new JFrame();
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setSize(800,800);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(800, 800));

		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout(500, 500));
		  
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout(500, 300));
		  
		JPanel statPanel = new JPanel();
		statPanel.setLayout(new BorderLayout(150, 800));
		  
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout(150, 800));
		
		// Add action listeners
		JButton upButton = new JButton("Up");
		JButton downButton = new JButton("Down");
		JButton leftButton = new JButton("Left");
		JButton rightButton = new JButton("Right");
		JButton inventoryButton = new JButton("Inventory");
		JButton attackButton = new JButton("Attack");
		JButton shieldButton = new JButton("Shield");
		JButton runButton = new JButton("Run");
		
		buttonPanel.add(upButton, BorderLayout.CENTER);
		buttonPanel.add(downButton, BorderLayout.CENTER);
		buttonPanel.add(leftButton, BorderLayout.CENTER);
		buttonPanel.add(rightButton, BorderLayout.CENTER);
		buttonPanel.add(inventoryButton, BorderLayout.CENTER);
		buttonPanel.add(attackButton, BorderLayout.CENTER);
		buttonPanel.add(shieldButton, BorderLayout.CENTER);
		buttonPanel.add(runButton, BorderLayout.CENTER);
		
		gamePanel.setVisible(true);
		gamePanel.setBackground(Color.WHITE);
		
		textPanel.setVisible(true);
		textPanel.setBackground(Color.WHITE);
		
		statPanel.setVisible(true);
		statPanel.setBackground(Color.WHITE);
		
		buttonPanel.setVisible(true);
		buttonPanel.setBackground(Color.WHITE);
		
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		mainPanel.add(textPanel, BorderLayout.PAGE_END);
		mainPanel.add(statPanel, BorderLayout.LINE_START);
	 	mainPanel.add(buttonPanel, BorderLayout.LINE_END);	  
		mainPanel.setVisible(true);
		  
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		  
		Partition.main(null);
	}

}
