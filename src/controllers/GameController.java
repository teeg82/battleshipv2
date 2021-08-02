package controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.guiComponents.GameFrame;

public class GameController {

	public GameController(){
		initGameFrame();
	}
	
	private void initGameFrame(){
		
		GameFrame gameFrame = new GameFrame();
		final LoginController loginController = new LoginController();
		final GridController gridController = new GridController(loginController);
		ControlsController selectionController = new ControlsController(gridController);
		gridController.setMessageController(selectionController);
		
		gameFrame.addWindowListener(new WindowAdapter(){
			@Override
            public void windowClosing(WindowEvent e) {
				loginController.handleCloseOperation();
				gridController.handleCloseOperation();
                System.exit(0);
            }
		});
		
		gameFrame.setGridInterface(gridController);
		gameFrame.setSelectionInterface(selectionController);
		gameFrame.setLoginInterface(loginController);
		gameFrame.init();
		gameFrame.runView();
	}
}