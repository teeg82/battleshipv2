package model.tiles;

import java.awt.image.BufferedImage;

import model.ships.AbstractShip;
import model.tiles.shipTileTypes.AbstractShipTileType;

public abstract class AbstractShipTile extends AbstractTile {

	private static final long serialVersionUID = -4836930953573903480L;
	
	private AbstractShipTileType shipTileType;
	private AbstractShip ownerShip;

	public void setShipTileType(AbstractShipTileType shipTileType) {
		this.shipTileType = shipTileType;
	}

	public AbstractShipTileType getShipTileType() {
		return shipTileType;
	}
	
	@Override
	public BufferedImage getTileImage() {
		return shipTileType.getShipTileImage();
	}
	
	@Override
	public void setTileImage(BufferedImage image){
		shipTileType.setShipTileImage(image);
	}
	
	public void clear(){
		super.clear();
		this.shipTileType.clear();
		shipTileType = null;
	}

	public void setOwnerShip(AbstractShip ownerShip) {
		this.ownerShip = ownerShip;
	}

	public AbstractShip getOwnerShip() {
		return ownerShip;
	}
}