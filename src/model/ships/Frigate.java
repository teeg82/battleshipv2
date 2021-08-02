package model.ships;

import model.constants.ModelConstants;

public class Frigate extends AbstractShip {

	public Frigate(){
		super.setShipSize(ModelConstants.getShipSizeMap().get(this.getClass()));
	}
}