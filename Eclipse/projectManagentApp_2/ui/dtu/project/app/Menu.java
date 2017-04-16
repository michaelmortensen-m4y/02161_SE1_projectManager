package dtu.project.app;

import exceptions.WrongInputFormatException;

public abstract class Menu {
	
	protected JFrameUI UI;
	
	public Menu(JFrameUI UI){
		this.UI = UI;
	}
	
	public abstract Menu readInput(String input) throws WrongInputFormatException;
	
	public abstract String toString();

}
