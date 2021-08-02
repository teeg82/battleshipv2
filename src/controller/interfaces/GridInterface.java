package controller.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Observer;

public interface GridInterface extends ControllerInterface, MouseActionQueueInterface, GridActionQueueInterface {

	public void addObserver(Observer obs);
	
	public void handleMouseClick(MouseEvent mouseEvent);
	
	public void handleMouseDrag(MouseEvent mouseEvent);
	
	public void handleBlockedTileFill(ActionEvent e);

	public void handleGenerateGameGrid(ActionEvent e) throws Exception;
	
	public void setMode(Boolean isTileMode);

	public void handleClearGrid(ActionEvent e);

	public void handleToggleBoardDisplay();
	
	public Boolean isSolved();
	
	public Boolean isEmpty();
	
	public void saveGame();

	public void incrementWin();

	public void handleMouseMove(MouseEvent arg0);
}