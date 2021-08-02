package model.grid;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import model.constants.ModelConstants;
import model.ships.AbstractShip;
import model.tiles.AbstractShipTile;
import model.tiles.AbstractTile;

public class Grid extends Observable{

	private AbstractTile[][] gridTiles;
	private Boolean isCleared;
	private Map<Integer, Integer> columnCount;
	private Map<Integer, Integer> rowCount;
	private Map<Class<? extends AbstractShip>, Map<Point, AbstractShip>> shipMap;
	private List<AbstractShip> shipList;
		
	public Grid(){}
	
	public Map<Integer, Integer> getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(Map<Integer, Integer> columnCount) {
		this.columnCount = columnCount;
	}

	public Map<Integer, Integer> getRowCount() {
		return rowCount;
	}

	public void setRowCount(Map<Integer, Integer> rowCount) {
		this.rowCount = rowCount;
	}
	
	public void setGridTiles(AbstractTile[][] gridTiles) {
		this.gridTiles = gridTiles;
		this.isCleared = false;
		super.setChanged();
		super.notifyObservers();
	}
	
	public void setGridTiles(AbstractTile[][] gridTiles, Boolean isCleared) {
		this.gridTiles = gridTiles;
		this.isCleared = isCleared;
		super.setChanged();
		super.notifyObservers();
	}
	
	public AbstractTile getTile(Point position){
		return this.gridTiles[position.x][position.y];
	}
	
	public AbstractTile getTile(Integer x, Integer y){
		return this.gridTiles[x][y];
	}

	public void setTile(Point position, AbstractTile tile){
		this.gridTiles[position.x][position.y] = tile;
		this.isCleared = false;
		super.setChanged();
		super.notifyObservers();
	}
	
	public void setTile(AbstractTile tile){
		this.setTile(tile.getPosition(), tile);
		this.isCleared = false;
	}
	
	public void repaint(Graphics graphics){
		repaint(graphics,0,0);
	}
	
	public void repaint(Graphics graphics, Integer xPos, Integer yPos){
		for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				gridTiles[rowIndex][colIndex].repaint(graphics, xPos, yPos);
			}
		}
	}

	public void clearGrid(){
		for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				gridTiles[rowIndex][colIndex].clear();
				gridTiles[rowIndex][colIndex] = null;
			}
		}
		this.shipList.clear();
		this.shipMap.clear();
		this.isCleared = true;
	}
	
	public boolean isGridSolved(Grid playerGrid){
		return this.checkGridSolution(playerGrid);
	}
	
	private boolean checkGridSolution(Grid playerGrid){
		for(int rowIndex = 0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
			for(int colIndex = 0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				if(!playerGrid.getTile(colIndex, rowIndex).getClass().equals(this.getTile(colIndex, rowIndex).getClass())){
					return false;
				}
			}
		}
		return true;
	}

	public Boolean isCleared() {
		return isCleared;
	}
	
	public void setIsCleared(Boolean isCleared){
		this.isCleared = isCleared;
	}
	
	public AbstractShip getShip(Class<? extends AbstractShip> shipClass, Point position){
		return shipMap.get(shipClass).get(position);
	}
	
	public Map<Class<? extends AbstractShip>, Map<Point, AbstractShip>> getShipMap() {
		return shipMap;
	}

	public void setShipMap(Map<Class<? extends AbstractShip>, Map<Point, AbstractShip>> shipMap) {
		this.shipMap = shipMap;
	}
	
	public AbstractShipTile[] getShipTiles(Class<? extends AbstractShip> shipClass, Point position){
		AbstractShip ship = getShip(shipClass, position);
		AbstractShipTile[] shipTiles = new AbstractShipTile[ship.getShipSize()];
		Point startingPosition = ship.getStartingPosition();
		int incrementX;
		int incrementY;
		if(ship.getIsHorizontal()){
			incrementX = 1;
			incrementY = 0;
		}else{
			incrementX = 0;
			incrementY = 1;
		}
		for(int index = 0; index < shipTiles.length; index++){
			shipTiles[index] = (AbstractShipTile) this.getTile(startingPosition.x+incrementX, startingPosition.y + incrementY);
		}
		return shipTiles;
	}

	public void setShipList(List<AbstractShip> shipList) {
		this.shipList = shipList;
	}

	public List<AbstractShip> getShipList() {
		return shipList;
	}
	
	public void addToShipList(AbstractShip ship){
		shipList.add(ship);
	}
}