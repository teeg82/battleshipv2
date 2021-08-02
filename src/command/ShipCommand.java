package command;

import gameRules.validators.GridValidator;
import model.ships.AbstractShip;


public class ShipCommand extends AbstractCommand{
	private Boolean isHorizontal;
	private Integer shipSize;
	private GridValidator validator;
	private Class<? extends AbstractShip> selectedShipClass;
	
	public Boolean getIsHorizontal() {
		return isHorizontal;
	}
	public void setIsHorizontal(Boolean isHorizontal) {
		this.isHorizontal = isHorizontal;
	}
	public Integer getShipSize() {
		return shipSize;
	}
	public void setShipSize(Integer shipSize) {
		this.shipSize = shipSize;
	}
	public void setValidator(GridValidator validator) {
		this.validator = validator;
	}
	public GridValidator getValidator() {
		return validator;
	}
	public void setSelectedShipClass(Class<? extends AbstractShip> selectedShipClass) {
		this.selectedShipClass = selectedShipClass;
	}
	public Class<? extends AbstractShip> getSelectedShipClass() {
		return selectedShipClass;
	}
	
	@Override
	public void clear(){
		super.clear();
		isHorizontal = null;
		shipSize = null;
		validator = null;
		selectedShipClass = null;
	}
}