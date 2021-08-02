package controllers;

import java.util.UUID;

import javax.swing.JMenu;

import model.grid.GridFacade;
import services.io.ProfileService;
import controller.interfaces.LoginInterface;

public class LoginController implements LoginInterface {

	private ProfileService profileService;
	private JMenu profileMenuOption;

	public LoginController(){
		profileService = ProfileService.getInstance();
	}
	
	@Override
	public String getUserName() {
		return profileService.getUserName();
	}

	@Override
	public void incrementGamesPlayed() {
		profileService.incrementGamesPlayed();
		updateMenuText();
	}

	@Override
	public void incrementWin() {
		profileService.incrementWin();
		updateMenuText();
	}

	@Override
	public Object[][] getTableData() {
		return profileService.getTableProfileData();
	}

	@Override
	public void setProfileMenuItem(JMenu profileMenuOption) {
		this.profileMenuOption = profileMenuOption;
	}
	
	private void updateMenuText(){
		profileMenuOption.setText(profileService.getUserProfileText());
	}

	@Override
	public Object[] createNewProfile(String name) {
		return profileService.createNewProfile(name);
	}

	@Override
	public void loginToProfile(UUID profileID) {
		profileService.loginToProfile(profileID);
		updateMenuText();
	}

	@Override
	public void deleteProfile(UUID selectedID) {
		profileService.deleteProfile(selectedID);
	}

	public void handleCloseOperation() {
		profileService.handleCloseOperation();
	}

	@Override
	public void saveGame(GridFacade grid) {
		profileService.saveGame(grid);
	}
}