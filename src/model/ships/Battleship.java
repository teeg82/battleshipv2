package model.ships;

import model.constants.ModelConstants;

public class Battleship extends AbstractShip {

	public Battleship(){
		super.setShipSize(ModelConstants.getShipSizeMap().get(this.getClass()));
	}
}