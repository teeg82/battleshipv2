package model.tiles.shipTileTypes;

import java.awt.image.BufferedImage;

public abstract class AbstractShipTileType {

	private BufferedImage shipTileImage;
	
	protected abstract String getImagePath();
	
	public void setShipTileImage(BufferedImage shipTileImage) {
		this.shipTileImage = shipTileImage;
	}

	public BufferedImage getShipTileImage() {
		return shipTileImage;
	}
	
	public void clear(){
		shipTileImage = null;
	}
}