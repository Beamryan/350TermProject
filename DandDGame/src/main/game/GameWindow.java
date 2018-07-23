package main.game;

import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
		
		int currentPartition = 0;
		int playerX = 5;
		int playerY = 1;
		int xDim = 5;
		int yDim = 5;
		int width = 10;
		int length = 10;
		Warrior player = new Warrior();

		Partition foo = new Partition(width, length, currentPartition, xDim, yDim, player, playerX, playerY);
		
		JFrame mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(2000, 1200));

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(1500, 900));
		
		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1500, 300));
		  
		JPanel statPanel = new JPanel();
		statPanel.setPreferredSize(new Dimension(250, 900));
		  
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(250, 900));
		
		JTextArea textArea = new JTextArea();
		textArea.setSize(new Dimension(1500, 300));
		textArea.setFont(textArea.getFont().deriveFont(14f));
		textPanel.add(textArea);
			
		JButton upButton = new JButton("Up");
		upButton.setPreferredSize(new Dimension(250, 80));
		upButton.setFont(new Font("Arial", Font.PLAIN, 40));
		upButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  try {
				  foo.moveNorth(); 
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  }
		});
		
		JButton downButton = new JButton("Down");
		downButton.setPreferredSize(new Dimension(250, 80));
		downButton.setFont(new Font("Arial", Font.PLAIN, 40));
		downButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  try {
				  foo.moveSouth(); 
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }		 
		  }
		});
		
		JButton leftButton = new JButton("Left");
		leftButton.setPreferredSize(new Dimension(250, 80));
		leftButton.setFont(new Font("Arial", Font.PLAIN, 40));
		leftButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  try {
				  foo.moveEast(); 
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }		 
		  }
		});
		
		JButton rightButton = new JButton("Right");
		rightButton.setPreferredSize(new Dimension(250, 80));
		rightButton.setFont(new Font("Arial", Font.PLAIN, 40));
		rightButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  try {
				  foo.moveWest(); 
			  } catch(Exception ex) {
				  ex.printStackTrace();
			  }
		  }
		});
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setPreferredSize(new Dimension(250, 80));
		inventoryButton.setFont(new Font("Arial", Font.PLAIN, 40));
		inventoryButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move inventory method
		  }
		});
		
		JButton attackButton = new JButton("Attack");
		attackButton.setPreferredSize(new Dimension(250, 80));
		attackButton.setFont(new Font("Arial", Font.PLAIN, 40));
		attackButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move attack method
		  }
		});
		
		JButton shieldButton = new JButton("Shield");
		shieldButton.setPreferredSize(new Dimension(250, 80));
		shieldButton.setFont(new Font("Arial", Font.PLAIN, 40));
		shieldButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move shield method
		  }
		});
		
		JButton restButton = new JButton("Rest");
		restButton.setPreferredSize(new Dimension(250, 80));
		restButton.setFont(new Font("Arial", Font.PLAIN, 40));
		restButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move run method
		  }
		});
		
		JButton fleeButton = new JButton("Flee");
		fleeButton.setPreferredSize(new Dimension(250, 80));
		fleeButton.setFont(new Font("Arial", Font.PLAIN, 40));
		fleeButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move flee method
		  }
		});
		
		buttonPanel.add(upButton);
		buttonPanel.add(downButton);
		buttonPanel.add(leftButton);
		buttonPanel.add(rightButton);
		buttonPanel.add(inventoryButton);
		buttonPanel.add(attackButton);
		buttonPanel.add(shieldButton);
		buttonPanel.add(restButton);
		buttonPanel.add(fleeButton);
		
		buttonPanel.setBackground(Color.GRAY);
		textPanel.setBackground(Color.WHITE);
		statPanel.setBackground(Color.GRAY);
		gamePanel.setBackground(Color.BLACK);
		
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(textPanel, BorderLayout.PAGE_END);
		mainFrame.add(statPanel, BorderLayout.LINE_START);
		mainFrame.add(buttonPanel, BorderLayout.LINE_END);	  
		
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.setVisible(true);
		  
		Partition.main(null);
	}

}
