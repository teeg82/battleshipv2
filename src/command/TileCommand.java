package command;

import java.awt.image.BufferedImage;
import java.util.Map;

import model.tiles.AbstractTile;
import model.tiles.shipTileTypes.AbstractShipTileType;


public class TileCommand extends AbstractCommand{
	private Class<? extends AbstractTile> selectedTileClass;
	private Class<? extends AbstractShipTileType> shipDirectionClass;
	private Map<Class<? extends AbstractShipTileType>, BufferedImage> shipTileImage;
	
	public Class<? extends AbstractTile> getSelectedTileClass() {
		return selectedTileClass;
	}
	public void setSelectedTileClass(Class<? extends AbstractTile> selectedTileClass) {
		this.selectedTileClass = selectedTileClass;
	}
	public Class<? extends AbstractShipTileType> getShipDirectionClass() {
		return shipDirectionClass;
	}
	public void setShipDirectionClass(Class<? extends AbstractShipTileType> shipDirectionClass) {
		this.shipDirectionClass = shipDirectionClass;
	}
	
	public void setShipTileImage(Map<Class<? extends AbstractShipTileType>, BufferedImage> shipTileImage) {
		this.shipTileImage = shipTileImage;
	}
	public Map<Class<? extends AbstractShipTileType>, BufferedImage> getShipTileImage() {
		return shipTileImage;
	}
	@Override
	public void clear(){
		super.clear();
		selectedTileClass = null;
		shipDirectionClass = null;
		shipTileImage = null;
	}
}