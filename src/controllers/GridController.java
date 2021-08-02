package controllers;

import factories.TileFactory;
import gameRules.generators.GameBoardGenerator;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import model.constants.ImageConstants;
import model.constants.ModelConstants;
import model.constants.ViewConstants;
import model.grid.Grid;
import model.grid.GridFacade;
import model.ships.AbstractShip;
import model.tiles.AbstractTile;
import model.tiles.EmptyTile;
import utils.stringUtils.StringUtils;

import command.GeneratorCommand;
import command.TileCommand;

import controller.interfaces.GridInterface;
import controller.interfaces.LoginInterface;
import controller.interfaces.MessageInterface;
import controllers.constants.DomainControllerConstants;

public class GridController extends AbstractGameController implements GridInterface {

	public static final boolean ALLOW_LOGGING = DomainControllerConstants.ALLOW_LOGGING;
	
	private AbstractTile currentTile;
//	private Point position;
	private static Rectangle gridArea;
	private final GameBoardGenerator generator;
	protected GridFacade grid;
	private Boolean isGameEmpty = true;
	
	private MessageInterface messageController;
	private LoginInterface loginController;
	
	private Queue<TileCommand> gridClickQueue;
//	private TilePointResolver tilePointResolver;
	//private Queue<Point> mousePositionQueue;
	
	private GameThreadMonitor gameThreadMonitor;
	
	private MouseListener mouseListener;
	
	public GridController(LoginInterface loginController){
		super();
		this.loginController = loginController;
		
		// Set up the game grid and the grid facade
		initGrid();

		generator = new GameBoardGenerator();
		generator.setMouseListener(mouseListener);
		generator.generateHeaderMaps(grid);
		gridArea = createGridAreaRectangle();
//		position = new Point(gridArea.getLocation());
		gridClickQueue = new LinkedBlockingQueue<TileCommand>();
		//mousePositionQueue = new LinkedBlockingQueue<Point>();
		
		gameThreadMonitor = new GameThreadMonitor(this);
	}
	
	private void initGrid(){
		Grid gameGrid = new Grid();
		gameGrid.setGridTiles(TileFactory.createGrid());
		gameGrid.setShipList(new ArrayList<AbstractShip>(getShipListSize()));
		gameGrid.setShipMap(new HashMap<Class<? extends AbstractShip>, Map<Point, AbstractShip>>());
		
		mouseListener = new TileListener();
		
		grid = new GridFacade(gameGrid);
		grid.setGridTiles(TileFactory.createGrid(mouseListener));
		grid.setShowGameBoard(false);
		grid.setShipMap(new HashMap<Class<? extends AbstractShip>, Map<Point, AbstractShip>>());
		grid.setShipList(new ArrayList<AbstractShip>());
	}
	
	private Integer getShipListSize(){
		Integer shipListSize = 0;
		List<Class<? extends AbstractShip>> shipList = ModelConstants.getShipList();
		Map<Class<? extends AbstractShip>, Integer> shipCountMap = ModelConstants.getShipCountMap();
		for(Class<? extends AbstractShip> shipClass : shipList){
			shipListSize += shipCountMap.get(shipClass);
		}
		return shipListSize;
	}
	
	public void setMessageController(MessageInterface messageController){
		this.messageController = messageController;
	}
	
	public void addObserver(Observer obs) {
		grid.addObserver(obs);		
	}
	
	private Rectangle createGridAreaRectangle(){
		Rectangle gridArea = new Rectangle();
		gridArea.setLocation(new Point(ModelConstants.standardTileDimensions.width, 
									   ModelConstants.standardTileDimensions.height));
		gridArea.setSize(ViewConstants.getGridDimensions());
		return gridArea;
	}
	
	private TileCommand setUpTileCommandObject(){
		TileCommand command = new TileCommand();
		command.setGrid(grid);
		command.setTile(currentTile);
		command.setSelectedTileClass(selectedTileClass);
		command.setShipDirectionClass(shipDirectionClass);
		command.setShipTileImage(ImageConstants.DYNAMIC_SHIP_TILE_IMAGE_MAP);
		return command;
	}
	
	private GeneratorCommand setUpGeneratorCommandObject(){
		GeneratorCommand command = new GeneratorCommand();
		command.setGrid(grid.getGrid());
		AbstractTile tile = new EmptyTile();
		// tile.set
		tile.setPosition(new Point(0,0));
		command.setTile(tile);
		command.setShipClassList(ModelConstants.getShipList());
		command.setShipCountMap(ModelConstants.getShipCountMap());
		command.setShipSizeMap(ModelConstants.getShipSizeMap());
		command.setMouseListener(mouseListener);
		return command;
	}

	@Override
	public void handleMouseMove(final MouseEvent mouseEvent){
//		this.position = tilePointResolver.getResolvedPoint(mouseEvent);

	}
	
	@Override
	public void handleMouseClick(MouseEvent mouseEvent) {
//		if(grid.getTile(position).isSelectable()){
		if(currentTile.isSelectable()){
			TileCommand command = setUpTileCommandObject();
			gameThreadMonitor.handleGridAction(command);
		}
	}	
	
	@Override
	public void handleMouseDrag(final MouseEvent mouseEvent){
//		handleMouseMove(mouseEvent);
		handleMouseClick(mouseEvent);
	}
	
	@Override
	public void handleTileGenerator(TileCommand command){
		generator.handleGenerateTile(command);
	}
	
	@Override
	public void handleClearGrid(ActionEvent e){
		handleClearGrid(e, this.grid);
	}
	
	public void handleClearGrid(ActionEvent e, Grid grid){
		generator.handleClearGrid(grid);
		isGameEmpty = true;
		messageController.updateMessages();
	}
	
	@Override
	public void handleGenerateGameGrid(ActionEvent e) throws Exception{
		loginController.incrementGamesPlayed();
		handleClearGrid(null, this.grid);
		handleClearGrid(null, this.grid.getGrid());
		GeneratorCommand command = setUpGeneratorCommandObject();
		generator.generateGameGrid(command);
		command.clear();
		command = null;
		generator.generateRandomClues(grid);
		isGameEmpty = false;
		messageController.updateMessages();
	}
	
	@Override
	public void repaint(Graphics graphics) {
		paintColumnAndRowHeaders(graphics);
		if(grid.getShowGameBoard()){
			grid.getGrid().repaint(graphics, gridArea.x, gridArea.y);
		}else{
			grid.repaint(graphics, gridArea.x, gridArea.y);			
		}		
	}

	public void paintColumnAndRowHeaders(Graphics graphics){
		Map<Integer, Integer> headerMap = grid.getColumnCount();
		for(int index=0; index < headerMap.size(); index++){
			graphics.drawImage(ImageConstants.getNumberImage().get(headerMap.get(index)), (index * ModelConstants.standardTileDimensions.width)+ModelConstants.minimumGridDrawDistance.width, 0, null);
		}
		headerMap = grid.getRowCount();
		for(int index=0; index < headerMap.size(); index++){
			graphics.drawImage(ImageConstants.getNumberImage().get(headerMap.get(index)), 0, (index * ModelConstants.standardTileDimensions.height)+ModelConstants.minimumGridDrawDistance.height, null);	
		}
	}
	
	@Override
	public void setMode(Boolean isTileMode) {
		AbstractGameController.isTileMode = isTileMode;		
	}

	@Override
	public void handleBlockedTileFill(ActionEvent e) {
		generator.handleBlockedTileFill(grid);
	}
	
	@Override
	public Boolean isSolved(){
		if(isGameEmpty){
			return false;
		}else{
			if(grid.isSolved())
				return true;
			else
				return false;
		}
	}

	@Override
	public void handleToggleBoardDisplay() {
		if(grid.getShowGameBoard()){
			grid.setShowGameBoard(false);
		}else{
			grid.setShowGameBoard(true);
		}
	}

	@Override
	public Boolean isEmpty() {
		return isGameEmpty;
	}

	@Override
	public void saveGame() {
		loginController.saveGame(grid);
	}

	@Override
	public void incrementWin() {
		loginController.incrementWin();
	}

	@Override
	public Queue<TileCommand> getGridActionQueue() {
		return gridClickQueue;
	}
	
	@Override
	public Rectangle getGridArea(){
		return gridArea;
	}
	
	@Override
	public Class<? extends AbstractTile> getTileClass(Point currentPosition){
		return grid.getTile(currentPosition).getClass();
	}

	public void handleCloseOperation() {
		this.gridClickQueue.clear();
		
		this.clear();
	}
	
	public void clear(){
		gridClickQueue = null;
	}
	
	private class TileListener implements MouseListener{
		@Override
		public void mouseEntered(MouseEvent e) {
			AbstractTile tile = (AbstractTile) e.getSource();
			currentTile = tile;
			if(ALLOW_LOGGING){
				Point tilePosition = tile.getPosition();
				System.out.println("Entered " + StringUtils.getShortClassName(currentTile.getClass()) + " at location: (" + tilePosition.getX() + ", " + tilePosition.getY() + ")");
			}
		}
		@Override public void mouseClicked(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}
		@Override public void mousePressed(MouseEvent e) {}
		@Override public void mouseReleased(MouseEvent e) {}		
	}
}
