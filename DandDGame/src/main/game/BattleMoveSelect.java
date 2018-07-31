package main.game;

import java.util.Observable;

public class BattleMoveSelect extends Observable{
	public BattleChoices choice = null;
	
	public BattleChoices getChoice() {
		return this.choice;
	}
	
	public void setChoice(BattleChoices choice) {
		this.choice = choice;
		setChanged();
		notify();
	}

}
