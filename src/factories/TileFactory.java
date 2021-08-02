package factories;

import java.awt.event.MouseListener;

import model.constants.ImageConstants;
import model.constants.ModelConstants;
import model.tiles.AbstractTile;
import model.tiles.EmptyTile;

public final class TileFactory {

	private TileFactory(){}
	
	public static AbstractTile createEmptyTile(){
		return null;		
	}
	
	/**
	 * Creates a new grid using the "Empty Tile" as the default tile.
	 * @return A generated grid using empty tiles.
	 */
	public static AbstractTile[][] createGrid(){
		return createGrid(EmptyTile.class, null);
	}
	
	/**
	 * Creates a new grid using the "Empty Tile" as the default tile.
	 * @return A generated grid using empty tiles.
	 */
	public static AbstractTile[][] createGrid(MouseListener mouseListener){
		return createGrid(EmptyTile.class, mouseListener);
	}	
	
	/**
	 * Creates a new grid using the specified tile as the default tile to be laid.
	 * @param tile Default tile used to generate a new grid
	 * @return A generated grid using the specified tile as a default
	 */
	public static AbstractTile[][] createGrid(Class<? extends AbstractTile> tile, MouseListener mouseListener){
		AbstractTile[][] grid = new AbstractTile[ModelConstants.gridDimensions.width][ModelConstants.gridDimensions.height];
		for(int rowIndex=0; rowIndex < ModelConstants.gridDimensions.height; rowIndex++){
			for(int colIndex=0; colIndex < ModelConstants.gridDimensions.width; colIndex++){
				AbstractTile desiredTile;
				try {
					desiredTile = tile.newInstance();
					desiredTile.setTileImage(ImageConstants.TILE_IMAGE_MAP.get(tile));
					desiredTile.setBounds(rowIndex, colIndex, ModelConstants.standardTileDimensions.width, ModelConstants.standardTileDimensions.height);
					if(mouseListener != null){
						desiredTile.addMouseListener(mouseListener);
					}
					grid[rowIndex][colIndex] = desiredTile;
				}catch(Exception e){e.printStackTrace();}
			}
		}
		return grid;
	}
	
	public static AbstractTile createTile(){
		return null;
	}
}