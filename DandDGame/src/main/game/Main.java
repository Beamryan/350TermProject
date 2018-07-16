package main.game;

import java.io.IOException;

/**
 * Main class to run the game through.
 */
public final class Main {

	/**
	 * Main
	 */
	private Main() {
		
	}
	
	/**
	 * 
	 * @param args arguments for main method.
	 * @throws IOException exception thrown for running partition files
	 */
	public static void main(final String[] args) throws IOException {
		Partition.main(null);
	}

}
