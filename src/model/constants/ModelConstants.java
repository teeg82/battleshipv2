package model.constants;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ships.AbstractShip;
import model.ships.Battleship;
import model.ships.Cruiser;
import model.ships.Destroyer;
import model.ships.Frigate;
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

public abstract class ModelConstants {

	private ModelConstants() {}

	public static final Dimension gridDimensions = new Dimension(10,10);
	public static final Dimension standardTileDimensions = new Dimension(64,64);
	public static final Dimension minimumGridDrawDistance = new Dimension(64, 64);
	
	public static Map<Class<? extends AbstractTile>, Integer> getTileIDValue(){
		Map<Class<? extends AbstractTile>, Integer> tileIDMap = new HashMap<Class<? extends AbstractTile>, Integer>();
		tileIDMap.put(EmptyTile.class, 1);
		tileIDMap.put(DynamicBlockedTile.class, 2);
		tileIDMap.put(StaticBlockedTile.class, 2);
		tileIDMap.put(DynamicShipTile.class, 3);
		tileIDMap.put(StaticShipTile.class, 3);
		return tileIDMap;
	}
	
	public static Map<Class<? extends AbstractShipTileType>, Integer> getShipTileTypeIDValue(){
		Map<Class<? extends AbstractShipTileType>, Integer> shipTileTypeIDMap = new HashMap<Class<? extends AbstractShipTileType>, Integer>();
		shipTileTypeIDMap.put(InnerShipTileType.class, 4);
		shipTileTypeIDMap.put(NorthShipTileType.class, 5);
		shipTileTypeIDMap.put(EastShipTileType.class, 6);
		shipTileTypeIDMap.put(SouthShipTileType.class, 7);
		shipTileTypeIDMap.put(WestShipTileType.class, 8);
		shipTileTypeIDMap.put(FrigateShipTileType.class, 9);
		return shipTileTypeIDMap;
	}

	public static List<Class<? extends AbstractShip>> getShipList() {
		List<Class<? extends AbstractShip>> shipList = new ArrayList<Class<? extends AbstractShip>>(4);
		shipList.add(Battleship.class);
		shipList.add(Cruiser.class);
		shipList.add(Destroyer.class);
		shipList.add(Frigate.class);
		return shipList;
	}
	
	public static Map<Class<?extends AbstractShip>, Integer> getShipSizeMap(){
		Map<Class<?extends AbstractShip>, Integer> map = new HashMap<Class<?extends AbstractShip>, Integer>(4);
		map.put(Battleship.class, 4);
		map.put(Cruiser.class, 3);
		map.put(Destroyer.class, 2);
		map.put(Frigate.class, 1);
		return map;
	}
	
	public static Map<Class<?extends AbstractShip>, Integer> getShipCountMap(){
		Map<Class<?extends AbstractShip>, Integer> map = new HashMap<Class<?extends AbstractShip>, Integer>(4);
		map.put(Battleship.class, 1);
		map.put(Cruiser.class, 2);
		map.put(Destroyer.class, 3);
		map.put(Frigate.class, 4);
		return map;
	}
}