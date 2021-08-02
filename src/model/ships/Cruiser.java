package model.ships;

import model.constants.ModelConstants;

public class Cruiser extends AbstractShip {

	public Cruiser(){
		super.setShipSize(ModelConstants.getShipSizeMap().get(this.getClass()));
	}
}