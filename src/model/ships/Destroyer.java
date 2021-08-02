package model.ships;

import model.constants.ModelConstants;

public class Destroyer extends AbstractShip {

	public Destroyer(){
		super.setShipSize(ModelConstants.getShipSizeMap().get(this.getClass()));
	}
}