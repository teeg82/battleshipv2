package controller.interfaces;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public interface SelectionInterface extends ControllerInterface {

	public void handleBlockedTileSelection();
	
	public void handleEmptyTileSelection();
	
	public void handleShipTileSelection(ActionEvent e);
	
	public void handleShipSelection(ActionEvent e);
	
	public BufferedImage getImage();
	
	public void setMode(Boolean isTileMode);

	public void handleShipAlignmentToggle(ActionEvent e);

	public void handleBlockedTileFill(ActionEvent e);

	public void handleGenerateGameGrid(ActionEvent e);

	public void handleClearGrid(ActionEvent e);

	public void toggleBoardDisplay();
	
	public void setGameMessageLabel(JLabel gameMessageLabel);
	
//	public String getGameStatusMessage();
	
	public void repaint(Graphics graphics, int x, int y);

//	public Color getMessageColour();
	
	public void updateMessages();

	public void handleCheckPuzzleIsSolved();
}