package view.guiComponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Box.Filler;

import model.constants.ViewConstants;
import controller.interfaces.SelectionInterface;

public class ControlsPanel extends JPanel{

	private static final long serialVersionUID = -7507910412032255443L;
	
	private SelectionInterface controller;
	private static final Dimension fillerSize = new Dimension(800,10);
	private JPanel tileModePanel;
	private JPanel shipModePanel;
	private JPanel gameModePanel;
	private static final String TILE_ACTION_COMMAND = "tile";
	private static final String SHIP_ACTION_COMMAND = "ship";
	private static final String GAME_ACTION_COMMAND = "game";
	private JButton checkPuzzleIsSolved;
	
	public ControlsPanel(SelectionInterface controller){
		this.controller = controller;
		this.controller.handleEmptyTileSelection();
		controller.setMode(Boolean.TRUE);
		this.initGui();
		this.setPreferredSize(ViewConstants.getControlPanelDimension());
		this.setVisible(true);
	}
	
	private void update(){
		this.repaint();
	}
	
	private void initGui(){
		JRadioButton gameModeRadioButton = createGameModeRadioButton();
		JRadioButton tileModeRadioButton = createTileModeRadioButton();
		JRadioButton shipModeRadioButton = createShipModeRadioButton();
		ButtonGroup group = new ButtonGroup();
		group.add(gameModeRadioButton);
		group.add(tileModeRadioButton);
		group.add(shipModeRadioButton);
		this.add(gameModeRadioButton);
		this.add(tileModeRadioButton);
		this.add(shipModeRadioButton);		

		JButton emptyTileButton = createEmptyTilebutton();
		JButton blockedTileButton = createBlockedButton();
		JButton shipTileButton = createShipTileSelectionButton();		
		JComboBox shipTileSelectionBox = createShipTileSelectionBox();
		JButton blockedTileFillButton = createBlockedTileFillButton();
		JButton clearGridButton = createClearGridButton();
		JButton generateGameBoardButton = createGenerateGameGridButton(null);
		JButton revealGameBoardButton = createRevealGameBoardButton();
		checkPuzzleIsSolved = createCheckPuzzleIsSolvedButton();
		JLabel gameMessageLabel = new JLabel();
		gameMessageLabel.setVisible(true);
		controller.setGameMessageLabel(gameMessageLabel);		
		controller.updateMessages();
		JLabel selectedTileImageLabel = createTileImageLabel();
		
		gameModePanel = new JPanel();
		gameModePanel.setPreferredSize(ViewConstants.getControlPanelDimension());
//		gameModePanel.setLayout(new BoxLayout(gameModePanel.getParent(), BoxLayout.Y_AXIS));
		gameModePanel.add(createFiller(new Dimension(fillerSize.width, 25)));
		gameModePanel.add(createEmptyTilebutton());
		gameModePanel.add(createFiller());
		gameModePanel.add(createBlockedButton());
		gameModePanel.add(createFiller());
		gameModePanel.add(new JLabel("Select a ship tile type for placement: "));
//		gameModePanel.add(createFiller(new Dimension(fillerSize.width, 0)));
		gameModePanel.add(createShipTileSelectionBox());
		
		JPanel tileImagePanel = new TileImagePanel();
		tileImagePanel.setPreferredSize(new Dimension(64, 64));
		tileImagePanel.setVisible(true);
		gameModePanel.add(createFiller());
		gameModePanel.add(selectedTileImageLabel);
		gameModePanel.add(tileImagePanel);
		gameModePanel.add(createFiller(new Dimension(fillerSize.width, 100)));
		gameModePanel.add(gameMessageLabel);
		gameModePanel.add(checkPuzzleIsSolved);
		gameModePanel.setVisible(true);	
		this.add(gameModePanel);
		
		tileModePanel = new JPanel();
		tileModePanel.setPreferredSize(ViewConstants.getControlPanelDimension());
		tileModePanel.add(emptyTileButton);
		tileModePanel.add(blockedTileButton);
		tileModePanel.add(shipTileButton);
		tileModePanel.add(shipTileSelectionBox);
		tileModePanel.add(blockedTileFillButton);
		tileModePanel.add(clearGridButton);
		tileModePanel.add(generateGameBoardButton);
		tileModePanel.add(revealGameBoardButton);
//		tileModePanel.setVisible(true);
		this.add(tileModePanel);
		
		shipModePanel = new JPanel();
		shipModePanel.setPreferredSize(ViewConstants.getControlPanelDimension());
		JButton shipSelectionButton = createShipAlignmentBotton();
		JComboBox shipSelectionBox = createShipSelectionBox();
		shipModePanel.add(shipSelectionButton);
		shipModePanel.add(shipSelectionBox);
//		shipModePanel.setVisible(true);
		this.add(shipModePanel);
		
		gameModeRadioButton.setSelected(true);
		tileModeRadioButton.setSelected(false);
		shipModeRadioButton.setSelected(false);
	}
	
	private JButton createCheckPuzzleIsSolvedButton() {
		JButton button = new JButton("Check Puzzle Solution");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.handleCheckPuzzleIsSolved();
			}			
		});
		Dimension widerButtonSize = new Dimension(ViewConstants.buttonSize.width+30, ViewConstants.buttonSize.height);
		button.setPreferredSize(widerButtonSize);
		button.setVisible(false);
		return button;
	}

	private JLabel createTileImageLabel(){
		JLabel label = new JLabel();
		label.setText("Selected tile: ");
		label.setVisible(true);
		return label;
	}
	
	private Box.Filler createFiller(){
		return createFiller(fillerSize);
	}
	
	private Box.Filler createFiller(Dimension prefSize){
		Box.Filler filler = new Filler(new Dimension(0,0), prefSize, prefSize);

		return filler;
	}

	private JRadioButton createGameModeRadioButton(){
		JRadioButton button = new JRadioButton("Game Mode");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				gameModePanel.setVisible(true);
				tileModePanel.setVisible(false);
				shipModePanel.setVisible(false);
				controller.setMode(Boolean.TRUE);
			}
		});
		button.setActionCommand(GAME_ACTION_COMMAND);
		button.setVisible(true);
		return button;
	}

	private JRadioButton createTileModeRadioButton() {
		JRadioButton button = new JRadioButton("Tile Mode");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				tileModePanel.setVisible(true);
				shipModePanel.setVisible(false);
				gameModePanel.setVisible(false);
				controller.setMode(Boolean.TRUE);
			}			
		});
		button.setActionCommand(TILE_ACTION_COMMAND);
		button.setVisible(true);
		return button;
	}

	private JRadioButton createShipModeRadioButton() {
		JRadioButton button = new JRadioButton("Ship Mode");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				tileModePanel.setVisible(false);
				gameModePanel.setVisible(false);
				shipModePanel.setVisible(true);
				controller.setMode(Boolean.FALSE);
				controller.handleShipSelection(e);
			}			
		});
		button.setActionCommand(SHIP_ACTION_COMMAND);
		button.setVisible(true);
		return button;
	}
	
	private JButton createEmptyTilebutton(){
		JButton button = new JButton();
		button.setPreferredSize(ViewConstants.buttonSize);
		button.setText("Empty Tile");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.handleEmptyTileSelection();
				update();
			}			
		});
		button.setVisible(true);
		return button;
	}
	
	private JButton createBlockedButton(){
		JButton button = new JButton();
		button.setPreferredSize(ViewConstants.buttonSize);
		button.setText("Blocked Tile");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.handleBlockedTileSelection();
				update();
			}			
		});
		button.setVisible(true);
		return button;
	}
	
	private JButton createShipTileSelectionButton(){
		JButton button = new JButton();
		button.setPreferredSize(ViewConstants.buttonSize);
		button.setText("Ship Tile");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.handleShipTileSelection(arg0);
				update();
			}			
		});
		button.setVisible(true);
		return button;
	}
	
	private JComboBox createShipTileSelectionBox(){
		String[] comboBoxOptions = {"Frigate Ship Tile", "Inner Ship Tile", "North Ship Tile", "South Ship Tile", "West Ship Tile", "East Ship Tile"};
		
		JComboBox shipTileSelection = new JComboBox(comboBoxOptions);
		shipTileSelection.setSelectedIndex(0);
		shipTileSelection.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleShipTileSelection(e);
				update();
			}			
		});
		shipTileSelection.setVisible(true);
		return shipTileSelection;
	}
	
	private JButton createShipAlignmentBotton() {
		JButton button = new JButton();
		button.setPreferredSize(ViewConstants.buttonSize);
		button.setText("Ship Alignment");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleShipAlignmentToggle(e);
				update();
			}			
		});
		button.setVisible(true);
		return button;
	}
	
	private JComboBox createShipSelectionBox(){
		String[] comboBoxOptions = {"Battleship", "Cruiser", "Destroyer", "Frigate"};
		JComboBox shipSelection = new JComboBox(comboBoxOptions);
		shipSelection.setSelectedIndex(0);
		shipSelection.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleShipSelection(e);
				update();
			}
		});
		shipSelection.setVisible(true);
		return shipSelection;
	}
	
	private JButton createBlockedTileFillButton(){
		JButton button = new JButton();
		button.setPreferredSize(ViewConstants.buttonSize);
		button.setText("Fill Blocked Tiles");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.handleBlockedTileFill(e);
				update();
			}
		});
		button.setVisible(true);
		return button;
	}
	
	private JButton createGenerateGameGridButton(String displayText){
		String buttonText = "Generate Game Grid";
		if (displayText != null){
			if(!displayText.isEmpty()){
				buttonText = displayText;
			}
		}
		
		JButton button = new JButton();
		Dimension widerButtonSize = new Dimension(ViewConstants.buttonSize.width+20, ViewConstants.buttonSize.height);
		button.setPreferredSize(widerButtonSize);
		button.setText(buttonText);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				controller.handleGenerateGameGrid(e);
				update();
			}
		});
		button.setVisible(true);
		return button;
	}
	
	private JButton createClearGridButton(){
		JButton button = new JButton();
		button.setPreferredSize(ViewConstants.buttonSize);
		button.setText("Clear Grid");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				controller.handleClearGrid(e);
			}
		});
		return button;
	}	

	private JButton createRevealGameBoardButton() {
		JButton button = new JButton("Reveal Board");
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				controller.toggleBoardDisplay();
			}
		});
		button.setVisible(true);
		return button;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
//		controller.repaint(tileImagePanelGraphics);		
	}
	
	private class TileImagePanel extends JPanel{
		private static final long serialVersionUID = -5352157236533058040L;

		public void paint(Graphics g){
			super.paint(g);
			controller.repaint(g, 0, 0);
		}
	}

	public void showSolutionButton(boolean showButton) {
		checkPuzzleIsSolved.setVisible(showButton);
	}
}