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
		mainFrame.setSize(2000, 1000);

		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(1500, 800));
		
		JPanel textPanel = new JPanel();
		textPanel.setPreferredSize(new Dimension(1500, 200));
		  
		JPanel statPanel = new JPanel();
		statPanel.setPreferredSize(new Dimension(250, 1000));
		  
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(250, 1000));
		
		// Add action listeners
		JButton upButton = new JButton("Up");
		upButton.setPreferredSize(new Dimension(250, 90));
		upButton.setFont(new Font("Arial", Font.PLAIN, 40));
		upButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move up method
		  }
		});
		
		JButton downButton = new JButton("Down");
		downButton.setPreferredSize(new Dimension(250, 90));
		downButton.setFont(new Font("Arial", Font.PLAIN, 40));
		downButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move down method
		  }
		});
		
		JButton leftButton = new JButton("Left");
		leftButton.setPreferredSize(new Dimension(250, 90));
		leftButton.setFont(new Font("Arial", Font.PLAIN, 40));
		leftButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move left method
		  }
		});
		
		JButton rightButton = new JButton("Right");
		rightButton.setPreferredSize(new Dimension(250, 90));
		rightButton.setFont(new Font("Arial", Font.PLAIN, 40));
		rightButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move right method
		  }
		});
		
		JButton inventoryButton = new JButton("Inventory");
		inventoryButton.setPreferredSize(new Dimension(250, 90));
		inventoryButton.setFont(new Font("Arial", Font.PLAIN, 40));
		inventoryButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move inventory method
		  }
		});
		
		JButton attackButton = new JButton("Attack");
		attackButton.setPreferredSize(new Dimension(250, 90));
		attackButton.setFont(new Font("Arial", Font.PLAIN, 40));
		attackButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move attack method
		  }
		});
		
		JButton shieldButton = new JButton("Shield");
		shieldButton.setPreferredSize(new Dimension(250, 90));
		shieldButton.setFont(new Font("Arial", Font.PLAIN, 40));
		shieldButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move shield method
		  }
		});
		
		JButton restButton = new JButton("Rest");
		restButton.setPreferredSize(new Dimension(250, 90));
		restButton.setFont(new Font("Arial", Font.PLAIN, 40));
		restButton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // Call the move run method
		  }
		});
		
		JButton fleeButton = new JButton("Flee");
		fleeButton.setPreferredSize(new Dimension(250, 90));
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
		
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		mainFrame.add(textPanel, BorderLayout.PAGE_END);
		mainFrame.add(statPanel, BorderLayout.WEST);
		mainFrame.add(buttonPanel, BorderLayout.EAST);	  
		
		mainFrame.pack();
		mainFrame.setVisible(true);
		  
		Partition.main(null);
	}

}
