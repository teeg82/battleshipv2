package controllers;

import java.awt.image.BufferedImage;

import model.ships.AbstractShip;
import model.tiles.AbstractTile;
import model.tiles.shipTileTypes.AbstractShipTileType;

public abstract class AbstractGameController {

	public static final boolean ALLOW_LOGGING = false;
	
	protected static Class<? extends AbstractShip> selectedShipClass;
	protected static Class<? extends AbstractTile> selectedTileClass;
	protected static Class<? extends AbstractShipTileType> shipDirectionClass;
	protected BufferedImage selectionImage;
	
	protected static Boolean isTileMode;
	protected static Integer shipSize;
	protected static Boolean isHorizontal;
	
	public AbstractGameController(){
		shipSize = 4;
		isHorizontal = true;
	}
}