package controllers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.constants.ImageConstants;
import model.constants.ModelConstants;
import model.ships.AbstractShip;
import model.ships.Battleship;
import model.ships.Cruiser;
import model.ships.Destroyer;
import model.ships.Frigate;
import model.tiles.DynamicBlockedTile;
import model.tiles.DynamicShipTile;
import model.tiles.EmptyTile;
import model.tiles.shipTileTypes.AbstractShipTileType;
import model.tiles.shipTileTypes.EastShipTileType;
import model.tiles.shipTileTypes.FrigateShipTileType;
import model.tiles.shipTileTypes.InnerShipTileType;
import model.tiles.shipTileTypes.NorthShipTileType;
import model.tiles.shipTileTypes.SouthShipTileType;
import model.tiles.shipTileTypes.WestShipTileType;
import controller.interfaces.GridInterface;
import controller.interfaces.MessageInterface;
import controller.interfaces.SelectionInterface;

public class ControlsController extends AbstractGameController implements SelectionInterface, MessageInterface{

	private GridInterface gridController;
	private JLabel gameMessageLabel;
	private static Color messageColor = Color.BLACK;
	
	public ControlsController(GridInterface gridController){
		super();
		this.gridController = gridController;
	}
	
	public void setGameMessageLabel(JLabel gameMessageLabel){
		this.gameMessageLabel = gameMessageLabel;
	}
	
	@Override
	public void handleBlockedTileSelection() {
		selectedTileClass = DynamicBlockedTile.class;
		selectionImage = ImageConstants.dynamicBlockedTile;
		shipDirectionClass = null;		
	}

	@Override
	public void handleEmptyTileSelection() {
		selectedTileClass = EmptyTile.class;
		selectionImage = ImageConstants.emptyTile;
		shipDirectionClass = null;
	}

	@Override
	public void handleShipTileSelection(ActionEvent e) {
		List<Class<? extends AbstractShipTileType>> tileTypes = new ArrayList<Class<? extends AbstractShipTileType>>(6);
		tileTypes.add(FrigateShipTileType.class);
		tileTypes.add(InnerShipTileType.class);
		tileTypes.add(NorthShipTileType.class);
		tileTypes.add(SouthShipTileType.class);
		tileTypes.add(WestShipTileType.class);
		tileTypes.add(EastShipTileType.class);
		
		List<BufferedImage> tileImages = new ArrayList<BufferedImage>(8);
		tileImages.add(ImageConstants.frigateShipTile);
		tileImages.add(ImageConstants.innerShipTile);
		tileImages.add(ImageConstants.northShipTile);
		tileImages.add(ImageConstants.southShipTile);
		tileImages.add(ImageConstants.westShipTile);
		tileImages.add(ImageConstants.eastShipTile);
		tileImages.add(ImageConstants.dynamicBlockedTile);
		tileImages.add(ImageConstants.emptyTile);
		
		selectedTileClass = DynamicShipTile.class;
		if(e.getSource().getClass()== JComboBox.class){
			JComboBox box = (JComboBox) e.getSource();
			shipDirectionClass = tileTypes.get(box.getSelectedIndex());
			selectionImage = tileImages.get(box.getSelectedIndex());
		}else{
			shipDirectionClass = tileTypes.get(0);
			selectionImage = ImageConstants.innerShipTile;
		}		
	}
	
	@Override
	public void handleShipAlignmentToggle(ActionEvent e) {
		if(isHorizontal) isHorizontal = false;
		else isHorizontal = true;
	}

	@Override
	public void handleShipSelection(ActionEvent e) {
		if(e.getSource().getClass()== JComboBox.class){
			List<Class<? extends AbstractShip>> shipTypes = new ArrayList<Class<? extends AbstractShip>>(4);
			shipTypes.add(Battleship.class);
			shipTypes.add(Cruiser.class);
			shipTypes.add(Destroyer.class);
			shipTypes.add(Frigate.class);
			isHorizontal = true;
			if(e.getSource().getClass()== JComboBox.class){
				JComboBox box = (JComboBox) e.getSource();
				selectedShipClass = shipTypes.get(box.getSelectedIndex());
				shipSize = 4 - box.getSelectedIndex();
			}
		}else{
			selectedShipClass = Battleship.class;
			shipSize = 4;
		}
	}
	
	private BufferedImage createShipImage(){
		BufferedImage firstTile;
		BufferedImage lastTile;
		Integer xIncrement;
		Integer yIncrement;
		BufferedImage shipImage;
		if(isHorizontal){
			firstTile = ImageConstants.westShipTile;
			lastTile = ImageConstants.eastShipTile;
			xIncrement = ModelConstants.standardTileDimensions.width;
			yIncrement = 0;
			shipImage = new BufferedImage((ImageConstants.innerShipTile.getWidth()*getShipSize()), ImageConstants.innerShipTile.getHeight(), ImageConstants.innerShipTile.getType());
		}else{
			firstTile = ImageConstants.northShipTile;
			lastTile = ImageConstants.southShipTile;
			xIncrement = 0;
			yIncrement = ModelConstants.standardTileDimensions.width;
			shipImage = new BufferedImage(ImageConstants.innerShipTile.getWidth(), (ImageConstants.innerShipTile.getHeight()*getShipSize()), ImageConstants.innerShipTile.getType());
		}
		Graphics2D g = shipImage.createGraphics();
		
		if(shipSize == 1) g.drawImage(ImageConstants.innerShipTile, 0, 0, null);
		else{			
			g.drawImage(firstTile, 0, 0, null);			
			for(int index = 0; index < (shipSize-2); index++)
				g.drawImage(ImageConstants.innerShipTile, (xIncrement + (xIncrement*index)), yIncrement + (yIncrement*index), null);
			
			g.drawImage(lastTile, xIncrement*(shipSize-1), yIncrement*(shipSize-1), null);
		}
		return shipImage;
	}
	
	@Override
	public BufferedImage getImage() {
		if(isTileMode) return selectionImage;
		else return createShipImage();
	}
	
	public Integer getShipSize(){ return shipSize;}
	public Boolean getIsHorizontal(){return isHorizontal;}

	@Override
	public void setMode(Boolean isTileMode) {
		gridController.setMode(isTileMode);
	}

	@Override
	public void handleBlockedTileFill(ActionEvent e) {
		gridController.handleBlockedTileFill(e);
	}
	
	@Override
	public void handleGenerateGameGrid(ActionEvent e) {
		try {gridController.handleGenerateGameGrid(e);} 
		catch (Exception e1){e1.printStackTrace();}
	}
	
	@Override
	public void handleClearGrid(ActionEvent e){
		gridController.handleClearGrid(e);
	}
	
	public Boolean isSolved(){
		return gridController.isSolved();
	}

//	protected static List getTestClassList(String basePackage) {
//		try{
//			if (!basePackage.startsWith("/")) {
//				basePackage = "/" + basePackage;
//			} 
//			String pckgname = basePackage.replace('.', '/');
//			URL url = AbstractWebTest.class.getResource(pckgname);
//			return listFileFromDirectory(url,new ClassFilter(".class",pckgname));
//	}
//
//	private static List listFileFromDirectory( URL url, final ClassFilter fileFilter) throws Exception {
//		List result = new ArrayList();
//		File directory = new File(url.getFile());
//		File[] childs = directory.listFiles(fileFilter);
//		for (int i=0; i<childs.length;i++){
//			if (childs[i].isDirectory()){
//				ClassFilter newFilter = new ClassFilter(fileFilter.getExtension(),fileFilter.getPackageFolder()+File.separator+childs[i].getName());
//				result.addAll(listFileFromDirectory(childs[i].toURL(), newFilter));
//			}else{
//				String fileName = childs[i].getName();
//				int end = fileName.length()- fileFilter.getExtension().length();
//				String classname = fileFilter.getPackageName() + "." + fileName.substring(0,end);
//				Class clazz = Class.forName(classname);
//				result.add(clazz);
//			}
//		}
//
//		return result;
//	}
	
	@Override
	public void repaint(Graphics graphics){
		graphics.drawImage(this.getImage(), 100, 200, null);
	}
	
	@Override
	public void repaint(Graphics graphics, int x, int y){
		graphics.drawImage(this.getImage(), x, y, null);
	}

	@Override
	public void toggleBoardDisplay() {
		gridController.handleToggleBoardDisplay();
	}

	@Override
	public void updateMessages() {
		gameMessageLabel.setText(getGameStatusMessage());
		gameMessageLabel.setForeground(messageColor);
	}
	
	private String getGameStatusMessage() {
		if(gridController.isEmpty()){
			messageColor = Color.BLACK;
			return "Click File / New Game to start playing...";
		}else{
			if(gridController.isSolved()){
				messageColor = Color.GREEN;
				gridController.incrementWin();
				return "CONGRATULATIONS! YOU SOLVED THE PUZZLE!!!";
			}else{
				messageColor = Color.RED;
				return "Puzzle not solved...";
			}
		}
	}
	
	@Override
	public void handleCheckPuzzleIsSolved() {
		JOptionPane.showMessageDialog(null, getGameStatusMessage(), "Puzzle Solution", JOptionPane.INFORMATION_MESSAGE);
	}
}