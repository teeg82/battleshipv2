package gameRules.validators;

import gameRules.validators.errorHandlers.GenericGridError;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import model.constants.ModelConstants;
import model.tiles.AbstractShipTile;
import model.tiles.AbstractTile;
import wrappers.ScanArea;

import command.ShipCommand;

/**
 * This class is used to validate the placement of a ship of any size on the
 * grid using two criteria:
 * <ul>
 * <li>Whether the placement of the ship, given its length, is being placed</li>
 * <li>Whether the ship is being placed, at least, once tile away from any existing
 * ships already on the board (as per the game rules)</li>
 * </ul>
 * Note: This class is implemented as a singleton. Use the method "getInstance()"
 * to retrieve the instance of the class;
 * Since this class represents the fundamental rules of the game, this class
 * cannot be overridden. 
 * 
 * @author Paul Richter
 * @version 1.0
 * @since March 19, 2010
 */
public final class GridValidator {

	private static GridValidator validator = new GridValidator();
	
	private GridValidator(){}
	
	public static GridValidator getInstance(){return validator;}
	
	/**
	 * Entry point for any grid validation. This method controls validation
	 * according to the fundamental game rules.
	 * @param command The command object responsible for encapsulating all the
	 * data required by the validators (including ship size, ship alignment, placement position, etc)
	 * @return A GenericGridError object representing any errors received during validation.
	 */
	public GenericGridError validateGrid(final ShipCommand command){
		GenericGridError errors = new GenericGridError();
		errors = testGridBoundries(command, errors);
		errors = testShipBoundaries(command, errors);
		return errors;
	}
	
	/**
	 * Tests the outer boundaries of the grid. Returns false if the ship is larger than the available
	 * space in either the X or Y coordinates, depending on whether the ship is oriented horizontal
	 * or vertical. Returns true if space is available. 
	 * @param isHorizontal The orientation of the ship, true if horizontal.
	 * @return True if space is available on the grid, false if not.
	 */
	private GenericGridError testGridBoundries(final ShipCommand command, GenericGridError errors){
		AbstractTile tile = command.getTile();
		if(command.getIsHorizontal()){
			if((tile.getPosition().getX()) + command.getShipSize() > ModelConstants.gridDimensions.width){
				errors.addErrorMessage("NOT ENOUGH X-AXIS GRID SPACE");
			}
		}else{
			if((tile.getPosition().getY()) + command.getShipSize() > ModelConstants.gridDimensions.height){
				errors.addErrorMessage("NOT ENOUGH Y-AXIS GRID SPACE");
			}
		}
		return errors;
	}

	/**
	 * Scans the surrounding tiles for other ship tiles. If any exist, according to the rules of the
	 * game, a ship of this size cannot be placed in this position, and the method returns false.
	 * Returns true if the surrounding space is clear.
	 * @param isHorizontal The orientation of the ship, true if horizontal.
	 * @return True if the surrounding tiles are clear of other ship tiles, false if they are not.
	 */
	private GenericGridError testShipBoundaries(final ShipCommand command, GenericGridError errors){
		Integer xCount;
		Integer yCount;
		if(command.getIsHorizontal()){
			xCount = command.getShipSize()+2;
			yCount = 3;
		} else{
			xCount = 3;
			yCount = command.getShipSize()+2;
		}
		Point currentPosition = command.getTile().getPosition();
		ScanArea scanArea = new ScanArea(
							new Point((currentPosition.x - 1), (currentPosition.y - 1)), 
							new Dimension(xCount, yCount));
		
		List<Point> positionList = scanArea.getScanAreaPositions();
		
		for(Point position : positionList){
			AbstractTile currentTile = command.getGrid().getTile(position);
			if(AbstractShipTile.class.isInstance(currentTile)){
				errors.addErrorMessage("SHIP TILE DETECTED AT POSITION(X,Y): " 
						+ currentTile.getPosition().x + ", " 
						+ currentTile.getPosition().y 
						+ "\nRULE VIOLATION: SHIPS CANNOT BE PLACED ADJACENT TO OTHER SHIPS (INCLUDING DIAGONAL)");
			}
		}		
		return errors;
	}
}