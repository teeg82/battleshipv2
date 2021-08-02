package controller.interfaces;

import java.util.UUID;

import javax.swing.JMenu;

import model.grid.GridFacade;

public interface LoginInterface {

	public void incrementWin();
	
	public void incrementGamesPlayed();
	
	public String getUserName();

	public Object[][] getTableData();

	public void setProfileMenuItem(JMenu selectProfile);

	public void loginToProfile(UUID profileID);

	public void deleteProfile(UUID selectedID);
	
	public Object[] createNewProfile(String name);

	public void saveGame(GridFacade grid);
}