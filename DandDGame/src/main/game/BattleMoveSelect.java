package main.game;

import java.util.Observable;

/**
 *	Observes the battle moves from buttons.
 */
public class BattleMoveSelect extends Observable {
	
	/**
	 * The battle choice.
	 */
	public BattleChoices choice = null;
	
	/**
	 * Gets the battle choice.
	 * @return the battle choice
	 */
	public BattleChoices getChoice() {
		return this.choice;
	}
	
	/**
	 * Set the battle choice.
	 * @param choice the choice being set
	 */
	public void setChoice(final BattleChoices choice) {
		this.choice = choice;
		setChanged();
		notify();
	}

}
