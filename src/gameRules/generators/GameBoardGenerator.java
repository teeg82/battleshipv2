package gameRules.generators;

import factories.TileFactory;
import gameRules.validators.GridValidator;
import gameRules.validators.errorHandlers.GenericGridError;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.constants.ImageConstants;
import model.constants.ModelConstants;
import model.grid.Grid;
import model.grid.GridFacade;
import model.ships.AbstractShip;
import model.tiles.AbstractBlockedTile;
import model.tiles.AbstractShipTile;
import model.tiles.AbstractTile;
import model.tiles.DynamicBlockedTile;
import model.tiles.DynamicShipTile;
import model.tiles.EmptyTile;
import model.tiles.StaticBlockedTile;
import model.tiles.StaticShipTile;
import model.tiles.shipTileTypes.AbstractShipTileType;
import model.tiles.shipTileTypes.EastShipTileType;
import model.tiles.shipTileTypes.FrigateShipTileType;
import model.tiles.shipTileTypes.InnerShipTileType;
import model.tiles.shipTileTypes.NorthShipTileType;
import model.tiles.shipTileTypes.SouthShipTileType;
import model.tiles.shipTileTypes.WestShipTileType;

import command.GeneratorCommand;
import command.ShipCommand;
import command.TileCommand;

public class GameBoardGenerator {

	private MouseListener mouseListener;
	
	public GameBoardGenerator(){}

	public void setMouseListener(MouseListener mouseListener){
		this.mouseListener = mouseListener;
	}
	
	public void generateGameGrid(GeneratorCommand command) throws Exception{
		Grid grid = command.getGrid();
		if(command == null || grid == null){
			throw new Exception("Grid and command object cannot be null");
		}else{
			if(!grid.isCleared()){
				this.handleClearGrid(grid);
			}
			Map<Class <? extends AbstractShip>, Integer> shipSizeList = command.getShipSizeMap();
			Map<Class<? extends AbstractShip>, Integer> shipCountList = command.getShipCountMap();
			List<Class<? extends AbstractShip>> shipClassList = command.getShipClassList();
			
			for(Class<? extends AbstractShip> shipClass : shipClassList){
				for(int index=0; index < shipCountList.get(shipClass); index++){
					List<AbstractTile> horizontalTileList = getLegalTileList(shipSizeList.get(shipClass), grid, true);
					List<AbstractTile> verticalTileList = getLegalTileList(shipSizeList.get(shipClass), grid, false);
					Boolean isHorizontal;
					if(horizontalTileList.size() == 0){
						isHorizontal = false;
					}else {
						if(verticalTileList.size() == 0){
							throw new Exception("No space on grid.");
						}else{
							isHorizontal = new Random().nextBoolean();
						}
					}
					ShipCommand shipCommand = new ShipCommand();
					shipCommand.setGrid(command.getGrid());
					shipCommand.setIsHorizontal(isHorizontal);
					shipCommand.setMouseListener(command.getMouseListener());
					AbstractTile tile;
					if(isHorizontal){
						 tile = horizontalTileList.get(new Random().nextInt(horizontalTileList.size()));
					}else{
						tile = verticalTileList.get(new Random().nextInt(verticalTileList.size()));
					}
					shipCommand.setTile(tile);
					shipCommand.setShipSize(shipSizeList.get(shipClass));
					shipCommand.setValidator(GridValidator.getInstance());
					shipCommand.setSelectedShipClass(shipClass);
					handleGenerateShip(shipCommand, true);
					shipCommand.clear();
					shipCommand = null;
				}
			}
			handleBlockedTileFill(grid);
		}
		generateHeaderMaps(grid);
	}
	
	public void generateHeaderMaps(Grid grid){
		Map<Integer, Integer> headerMap = new HashMap<Integer, Integer>(ModelConstants.gridDimensions.width);
		for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
			Integer colCount = 0;
			for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
				if(AbstractShipTile.class.isInstance(grid.getTile(colIndex, rowIndex))){
					colCount++;
				}
			}
			headerMap.put(colIndex, colCount);
		}
		grid.setColumnCount(headerMap);
		headerMap = new HashMap<Integer, Integer>(ModelConstants.gridDimensions.height);
		
		for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
			Integer rowCount = 0;
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				if(AbstractShipTile.class.isInstance(grid.getTile(colIndex, rowIndex))){
					rowCount++;
				}
			}
			headerMap.put(rowIndex, rowCount);
		}
		grid.setRowCount(headerMap);
	}
	
	private List<AbstractTile> getLegalTileList(Integer shipSize, Grid grid, Boolean isHorizontal){
		List<AbstractTile> tileList = new ArrayList<AbstractTile>();
		GridValidator validator = GridValidator.getInstance();
		for(int rowIndex = 0; rowIndex< ModelConstants.gridDimensions.height; rowIndex++){
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				AbstractTile tile = grid.getTile(colIndex, rowIndex);
				if(tile == null){
					tile = new EmptyTile();
					tile.setPosition(new Point(colIndex, rowIndex));
				}
				ShipCommand command = setUpShipCommand(shipSize, grid, tile, isHorizontal);
				if(validator.validateGrid(command).getErrorCount() == 0){
					tileList.add(tile);
				}
				command.clear();
				command = null;
			}
		}
		return tileList;
	}
	
	private ShipCommand setUpShipCommand(Integer shipSize, Grid grid, AbstractTile tile, Boolean isHorizontal){
		ShipCommand command = new ShipCommand();
		command.setGrid(grid);
		command.setIsHorizontal(isHorizontal);
		command.setTile(tile);
		command.setShipSize(shipSize);
		return command;
	}
	
	public void handleGenerateTile(TileCommand command){
		setGridTile(createNewTileForInsertion(command), command.getGrid());
	}
	
	private void setGridTile(AbstractTile selectedTile, Grid grid){
		grid.setTile(selectedTile);
	}
	
	private AbstractTile createNewTileForInsertion(TileCommand command){
		try {
			AbstractTile selectedTile;
			//FIXME: This should probably be the AbstractShipTile class, oui?
			if(command.getSelectedTileClass().equals(DynamicShipTile.class) || command.getSelectedTileClass().equals(StaticShipTile.class)){
				AbstractShipTile shipTile = (AbstractShipTile) command.getSelectedTileClass().newInstance();
				shipTile.setShipTileType(command.getShipDirectionClass().newInstance());
				shipTile.setTileImage(command.getShipTileImage().get(command.getShipDirectionClass()));
				selectedTile = shipTile;
			}else{
				selectedTile = command.getSelectedTileClass().newInstance();
				selectedTile.setTileImage(ImageConstants.TILE_IMAGE_MAP.get(command.getSelectedTileClass()));
			}
			selectedTile.setPosition(new Point(command.getTile().getPosition()));
			selectedTile.addMouseListener(this.mouseListener);
			return selectedTile;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}	
	
	public void handleGenerateShip(ShipCommand command) {
		handleGenerateShip(command, false);
	}
	
	public void handleGenerateShip(ShipCommand command, Boolean ignoreValidation){
		GenericGridError errors;
		if(ignoreValidation)
			errors = new GenericGridError();
		else
			errors = command.getValidator().validateGrid(command);

		if(errors.getErrorCount() == 0){
			AbstractTile tile = command.getTile();
			try {
				AbstractShip selectedShip = command.getSelectedShipClass().newInstance();
				selectedShip.setIsHorizontal(command.getIsHorizontal());
				selectedShip.setShipTiles(createShipTiles(selectedShip, tile.getPosition()));
				handleShipTileCreation(selectedShip, command.getGrid());
			} catch (Exception e){
				e.printStackTrace();
			}
		}else{
			for(String message : errors.getErrorMessages())
				System.out.println(message);
		}
	}
	
	private AbstractShipTile[] createShipTiles(AbstractShip ship, Point position){
		final Integer shipSize = ship.getShipSize();
		AbstractShipTile[] tiles = new AbstractShipTile[shipSize];
		if(shipSize == 1){
			tiles[0] = new DynamicShipTile();
			tiles[0].setShipTileType(new FrigateShipTileType());
			tiles[0].setPosition(position);
			tiles[0].setTileImage(ImageConstants.DYNAMIC_SHIP_TILE_IMAGE_MAP.get(tiles[0].getShipTileType().getClass()));
			tiles[0].addMouseListener(mouseListener);
		}else{
			AbstractShipTileType firstShipTile;
			AbstractShipTileType lastShipTile;
			Integer xIncrement;
			Integer yIncrement;
			if(ship.getIsHorizontal()){
				firstShipTile = new WestShipTileType();
				lastShipTile = new EastShipTileType();
				xIncrement = 1;
				yIncrement = 0;
			}else{
				firstShipTile = new NorthShipTileType();
				lastShipTile = new SouthShipTileType();
				xIncrement = 0;
				yIncrement = 1;
			}
			firstShipTile.setShipTileImage(ImageConstants.DYNAMIC_SHIP_TILE_IMAGE_MAP.get(firstShipTile.getClass()));
			lastShipTile.setShipTileImage(ImageConstants.DYNAMIC_SHIP_TILE_IMAGE_MAP.get(lastShipTile.getClass()));
			tiles[0] = new DynamicShipTile();
			tiles[0].setShipTileType(firstShipTile);
			tiles[0].setPosition(position);
			tiles[0].setOwnerShip(ship);
			for(int index = 1; index < (shipSize-1); index++){
				tiles[index] = new DynamicShipTile();
				AbstractShipTileType shipTileType = new InnerShipTileType();
				shipTileType.setShipTileImage(ImageConstants.DYNAMIC_SHIP_TILE_IMAGE_MAP.get(shipTileType.getClass()));
				tiles[index].setShipTileType(shipTileType);
				tiles[index].setPosition(new Point(position.x + (xIncrement*index), position.y + (yIncrement*index)));
				tiles[index].setOwnerShip(ship);
				tiles[index].addMouseListener(mouseListener);
			}
			tiles[(tiles.length-1)] = new DynamicShipTile();
			tiles[(tiles.length-1)].setShipTileType(lastShipTile);
			tiles[(tiles.length-1)].setPosition(new Point(position.x + (xIncrement*(tiles.length-1)), position.y + (yIncrement*(tiles.length-1))));
			tiles[(tiles.length-1)].setOwnerShip(ship);
			tiles[(tiles.length-1)].addMouseListener(mouseListener);
		}
		
		return tiles;
	}
	
	private void handleShipTileCreation(AbstractShip selectedShip, Grid grid){
		AbstractShipTile[] selectedTileArray = selectedShip.getShipTiles();
		for(AbstractShipTile shipTile : selectedTileArray){
			grid.addToShipList(selectedShip);
			grid.setTile(shipTile.getPosition(), shipTile);
		}
	}
	
	public void handleBlockedTileFill(Grid grid) {
		for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.width; rowIndex++){
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.height; colIndex++){
				Point currentPosition = new Point(rowIndex, colIndex);
				AbstractTile tile = grid.getTile(currentPosition);
				if(tile.getClass().isAssignableFrom(EmptyTile.class)){
					currentPosition = tile.getPosition();
					tile = new DynamicBlockedTile();
					tile.setPosition(currentPosition);
					tile.setTileImage(ImageConstants.TILE_IMAGE_MAP.get(tile.getClass()));
					tile.addMouseListener(mouseListener);
					grid.setTile(currentPosition, tile);
				}
			}
		}
	}
	
	public void handleClearGrid(Grid grid){
		grid.clearGrid();
		grid.setGridTiles(TileFactory.createGrid(), true);
	}
	
	public void generateRandomClues(GridFacade gridFacade){
//		Integer totalClueCount = Double.valueOf((Math.random()*4)+2).intValue();
//		Integer shipClueCount = Double.valueOf(Math.random()*(totalClueCount-1)).intValue();
//		Integer blockedClueCount = Double.valueOf(Math.random()*(totalClueCount-shipClueCount)).intValue();
//		Integer totalClueCount = 6;
		Integer shipClueCount = 3;
		Integer blockedClueCount = 3;
		List<AbstractShip> shipList = gridFacade.getGrid().getShipList();
		for(int index = 0; index < shipClueCount; index++){
			AbstractShip selectedShip = shipList.get(new Random().nextInt(shipList.size()));
			AbstractShipTile revealedTile = selectedShip.getShipTiles()[new Random().nextInt(selectedShip.getShipSize())];
			TileCommand tileCommand = new TileCommand();
			tileCommand.setGrid(gridFacade);
			tileCommand.setTile(revealedTile);
			tileCommand.setSelectedTileClass(StaticShipTile.class);
//			tileCommand.setSelectedTileClass(revealedTile.getClass());
			tileCommand.setShipDirectionClass(revealedTile.getShipTileType().getClass());
			tileCommand.setShipTileImage(ImageConstants.STATIC_SHIP_TILE_IMAGE_MAP);
			gridFacade.setTile(this.createNewTileForInsertion(tileCommand));
			shipList.remove(selectedShip);
		}
		List<AbstractBlockedTile> blockedTileList = getBlockedTileList(gridFacade.getGrid());
		for(int index = 0; index < blockedClueCount; index++){
			AbstractBlockedTile revealedTile = blockedTileList.get(new Random().nextInt(blockedTileList.size()));
			TileCommand tileCommand = new TileCommand();
			tileCommand.setGrid(gridFacade);
			tileCommand.setTile(revealedTile);
			tileCommand.setSelectedTileClass(StaticBlockedTile.class);
//			tileCommand.setSelectedTileClass(revealedTile.getClass());
			gridFacade.setTile(this.createNewTileForInsertion(tileCommand));
			shipList.remove(revealedTile);
		}
	}
	
	private List<AbstractBlockedTile> getBlockedTileList(Grid grid){
		List<AbstractBlockedTile> blockedTileList = new ArrayList<AbstractBlockedTile>();
		for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				Point currentPosition = new Point(colIndex, rowIndex);
				AbstractTile tile = grid.getTile(currentPosition);
				if(AbstractBlockedTile.class.isInstance(tile)){
					blockedTileList.add((AbstractBlockedTile) tile);
				}
			}
		}
		return blockedTileList;
	}
}