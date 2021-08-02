package model.constants;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import model.tiles.AbstractTile;
import model.tiles.DynamicBlockedTile;
import model.tiles.EmptyTile;
import model.tiles.StaticBlockedTile;
import model.tiles.shipTileTypes.AbstractShipTileType;
import model.tiles.shipTileTypes.EastShipTileType;
import model.tiles.shipTileTypes.FrigateShipTileType;
import model.tiles.shipTileTypes.InnerShipTileType;
import model.tiles.shipTileTypes.NorthShipTileType;
import model.tiles.shipTileTypes.SouthShipTileType;
import model.tiles.shipTileTypes.WestShipTileType;
import factories.ImageFactory;

public final class ImageConstants {

	private ImageConstants() {}
	
	public static final int DIRECTION_NORTH = 0;
	public static final int DIRECTION_EAST = 90;
	public static final int DIRECTION_SOUTH = 180;
	public static final int DIRECTION_WEST = 270;
	
	private static final String IMAGE_PACKAGE = "/images/";
//	private static final String relativePath = StringUtils.getRelativePath(ImageFactory.class, IMAGE_PACKAGE);
	private static final String relativePath = IMAGE_PACKAGE;
	
	public static final BufferedImage emptyTile = ImageFactory.createImage(relativePath + "EmptyTile.png");
	public static final BufferedImage dynamicBlockedTile = ImageFactory.createImage(relativePath + "DynamicBlockedTile.png");
	public static final BufferedImage staticBlockedTile = ImageFactory.createImage(relativePath + "StaticBlockedTile.png");
	public static final BufferedImage northShipTile = ImageFactory.createImage(relativePath + "DynamicShipTile.png");
	public static final BufferedImage southShipTile = ImageFactory.createImage(relativePath + "DynamicShipTile.png", DIRECTION_SOUTH);
	public static final BufferedImage eastShipTile = ImageFactory.createImage(relativePath + "DynamicShipTile.png", DIRECTION_EAST);
	public static final BufferedImage westShipTile = ImageFactory.createImage(relativePath + "DynamicShipTile.png", DIRECTION_WEST);
	public static final BufferedImage innerShipTile = ImageFactory.createImage(relativePath + "InnerShipTile.png");
	public static final BufferedImage frigateShipTile = ImageFactory.createImage(relativePath + "FrigateShipTile.png");
	
	public static final BufferedImage staticNorthShipTile = ImageFactory.createImage(relativePath + "StaticShipTile.png");
	public static final BufferedImage staticSouthShipTile = ImageFactory.createImage(relativePath + "StaticShipTile.png", DIRECTION_SOUTH);
	public static final BufferedImage staticEastShipTile = ImageFactory.createImage(relativePath + "StaticShipTile.png", DIRECTION_EAST);
	public static final BufferedImage staticWestShipTile = ImageFactory.createImage(relativePath + "StaticShipTile.png", DIRECTION_WEST);
	public static final BufferedImage staticInnerShipTile = ImageFactory.createImage(relativePath + "StaticInnerShipTile.png");
	public static final BufferedImage staticFrigateShipTile = ImageFactory.createImage(relativePath + "StaticFrigateShipTile.png");
	
//	public static final BufferedImage southShipTile = ImageFactory.createImage(relativePath + "SouthEndShipTile.png");
//	public static final BufferedImage eastShipTile = ImageFactory.createImage(relativePath + "EastEndShipTile.png");
//	public static final BufferedImage westShipTile = ImageFactory.createImage(relativePath + "WestEndShipTile.png");
//	public static final BufferedImage innerShipTile = ImageFactory.createImage(relativePath + "InnerShipTile.png");
//	public static final BufferedImage frigateShipTile = ImageFactory.createImage(relativePath + "FrigateShipTile.png");
	public static final Map<Class<? extends AbstractTile>, BufferedImage> TILE_IMAGE_MAP = createTileImageMap();
	public static final Map<Class<? extends AbstractShipTileType>, BufferedImage> DYNAMIC_SHIP_TILE_IMAGE_MAP = createDynamicShipTileImageMap();
	public static final Map<Class<? extends AbstractShipTileType>, BufferedImage> STATIC_SHIP_TILE_IMAGE_MAP = createStaticShipTileImageMap();
	
	private static Map<Class<? extends AbstractTile>, BufferedImage> createTileImageMap(){
		Map<Class<? extends AbstractTile>, BufferedImage> imageMap = new HashMap<Class<? extends AbstractTile>, BufferedImage>(2);
		imageMap.put(EmptyTile.class, emptyTile);
		imageMap.put(DynamicBlockedTile.class, dynamicBlockedTile);
		imageMap.put(StaticBlockedTile.class, staticBlockedTile);
		return imageMap;
	}

	private static Map<Class<? extends AbstractShipTileType>, BufferedImage> createDynamicShipTileImageMap() {
		Map<Class<? extends AbstractShipTileType>, BufferedImage> imageMap = new HashMap<Class<? extends AbstractShipTileType>, BufferedImage>(5);
		imageMap.put(NorthShipTileType.class, northShipTile);
		imageMap.put(SouthShipTileType.class, southShipTile);
		imageMap.put(EastShipTileType.class, eastShipTile);
		imageMap.put(WestShipTileType.class, westShipTile);
		imageMap.put(InnerShipTileType.class, innerShipTile);
		imageMap.put(FrigateShipTileType.class, frigateShipTile);
		return imageMap;
	}
	
	private static Map<Class<? extends AbstractShipTileType>, BufferedImage> createStaticShipTileImageMap() {
		Map<Class<? extends AbstractShipTileType>, BufferedImage> imageMap = new HashMap<Class<? extends AbstractShipTileType>, BufferedImage>(5);
		imageMap.put(NorthShipTileType.class, staticNorthShipTile);
		imageMap.put(SouthShipTileType.class, staticSouthShipTile);
		imageMap.put(EastShipTileType.class, staticEastShipTile);
		imageMap.put(WestShipTileType.class, staticWestShipTile);
		imageMap.put(InnerShipTileType.class, staticInnerShipTile);
		imageMap.put(FrigateShipTileType.class, staticFrigateShipTile);
		return imageMap;
	}
	
	public static Map<Integer, BufferedImage> getNumberImage(){
		Map<Integer, BufferedImage> imageMap = new HashMap<Integer, BufferedImage>();
		imageMap.put(0, ImageFactory.createImage(relativePath + "numberZero.png"));
		imageMap.put(1, ImageFactory.createImage(relativePath + "numberOne.png"));
		imageMap.put(2, ImageFactory.createImage(relativePath + "numberTwo.png"));
		imageMap.put(3, ImageFactory.createImage(relativePath + "numberThree.png"));
		imageMap.put(4, ImageFactory.createImage(relativePath + "numberFour.png"));
		imageMap.put(5, ImageFactory.createImage(relativePath + "numberFive.png"));
		imageMap.put(6, ImageFactory.createImage(relativePath + "numberSix.png"));
		imageMap.put(7, ImageFactory.createImage(relativePath + "numberSeven.png"));
		imageMap.put(8, ImageFactory.createImage(relativePath + "numberEight.png"));
		imageMap.put(9, ImageFactory.createImage(relativePath + "numberNine.png"));
		
		return imageMap;
	}
}