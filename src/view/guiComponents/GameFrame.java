package view.guiComponents;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import services.io.ProfileService;
import controller.interfaces.GridInterface;
import controller.interfaces.LoginInterface;
import controller.interfaces.SelectionInterface;

public class GameFrame extends JFrame{

	private static final long serialVersionUID = -439165068992166631L;

	private GamePanel gamePanel;
	private LoginPanel loginPanel;
	private JMenu fileOption;
	
	private GridInterface gridInterface;
	private SelectionInterface selectionInterface;
	private LoginInterface loginInterface;
	
	protected static final Dimension FRAME_SIZE = new Dimension(1128, 768);
	protected static final Dimension PANEL_SIZE = new Dimension(1128, 738);
	
	protected static final Integer GAME_MODE = 1;
	protected static final Integer PROFILE_MODE = 2;
	
	public GameFrame(){
		this.setTitle("BattleShip Puzzle");
		this.setPreferredSize(FRAME_SIZE);
	}
	
	public void setGridInterface(GridInterface gridInterface){
		this.gridInterface = gridInterface;
	}
	
	public void setSelectionInterface(SelectionInterface selectionInterface){
		this.selectionInterface = selectionInterface;
	}
	
	public void setLoginInterface(LoginInterface loginInterface){
		this.loginInterface = loginInterface;
	}

	public void init() {
		JMenuBar menuBar = createMenu(ProfileService.getInstance());
		this.setJMenuBar(menuBar);
		
		gamePanel = new GamePanel();
		gamePanel.setPreferredSize(PANEL_SIZE);
		gamePanel.setGridPanel(new GridPanel(gridInterface));
		gamePanel.setControlsPanel(new ControlsPanel(selectionInterface));
		gamePanel.init();
		
		loginPanel = new LoginPanel(loginInterface, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setGameMode();
			}
		});
		loginPanel.setVisible(true);
				
		this.add(gamePanel);
		this.add(loginPanel);
		
		this.setLoginMode();
	}	
	
	private JMenuBar createMenu(final ProfileService profileService){
		JMenuBar menuBar = new JMenuBar();
		
		fileOption = new JMenu("File");
		fileOption.setMnemonic(KeyEvent.VK_F);
		fileOption.getAccessibleContext().setAccessibleDescription("New game, save and load games, exit program");
		
		JMenuItem newGameItem = new JMenuItem("New Game");
		newGameItem.setMnemonic(KeyEvent.VK_N);
		newGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectionInterface.handleGenerateGameGrid(arg0);
				gamePanel.getControlsPanel().showSolutionButton(true);
			}
		});

		JMenuItem saveGameItem = new JMenuItem("Save Game");
		saveGameItem.setMnemonic(KeyEvent.VK_S);
		newGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gridInterface.saveGame();
			}
		});
		
		fileOption.add(newGameItem);
		fileOption.add(saveGameItem);
		fileOption.setVisible(true);
		menuBar.add(fileOption);
		menuBar.add(createProfilesMenu());
		menuBar.setVisible(true);
		return menuBar;
	}
	
	private JMenu createProfilesMenu(){
		JMenu loginOption = new JMenu("Profiles");
		loginOption.setMnemonic(KeyEvent.VK_L);
		loginOption.getAccessibleContext().setAccessibleDescription("Select a profile and log in");
		
		JMenuItem selectProfile = new JMenuItem("Select Profile");
		selectProfile.setMnemonic(KeyEvent.VK_P);
		selectProfile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setLoginMode();
			}
		});
		loginInterface.setProfileMenuItem(loginOption);
		loginOption.add(selectProfile);
		return loginOption;
	}
	
	private void setGameMode(){
		loginInterface.loginToProfile(loginPanel.getSelectedProfileID());
		fileOption.setVisible(true);
		gamePanel.setVisible(true);
		loginPanel.setVisible(false);
	}
	
	private void setLoginMode(){
		fileOption.setVisible(false);
		loginPanel.setTableData(loginInterface.getTableData());
		gamePanel.setVisible(false);
		loginPanel.setVisible(true);
	}
	
	public void runView(){	
		this.setLayout(new FlowLayout());
		this.pack();
        this.setVisible(true);
	}
}