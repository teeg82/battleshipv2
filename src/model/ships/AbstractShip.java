package model.ships;

import java.awt.Point;

import model.tiles.AbstractShipTile;

public abstract class AbstractShip{

	private Integer shipSize;
	private Boolean isHorizontal;
	private Point startingPosition;
	private AbstractShipTile[] shipTiles;
	
	public Integer getShipSize() {
		return shipSize;
	}
	public void setShipSize(Integer shipSize) {
		this.shipSize = shipSize;
	}
	public Boolean getIsHorizontal() {
		return isHorizontal;
	}
	public void setIsHorizontal(Boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}
	public Point getStartingPosition() {
		return startingPosition;
	}
	public void setStartingPosition(Point startingPosition) {
		this.startingPosition = startingPosition;
	}
	public void setShipTiles(AbstractShipTile[] shipTiles) {
		this.shipTiles = shipTiles;
	}
	public AbstractShipTile[] getShipTiles() {
		return shipTiles;
	}
}