package command;

import java.util.List;
import java.util.Map;

import model.ships.AbstractShip;

public class GeneratorCommand extends AbstractCommand {

	private List<Class<? extends AbstractShip>> shipClassList ;	
	private Map<Class<?extends AbstractShip>, Integer> shipSizeMap;	
	private Map<Class<?extends AbstractShip>, Integer> shipCountMap;
	
	public List<Class<? extends AbstractShip>> getShipClassList() {
		return shipClassList;
	}
	public void setShipClassList(List<Class<? extends AbstractShip>> shipClassList) {
		this.shipClassList = shipClassList;
	}
	public Map<Class<? extends AbstractShip>, Integer> getShipSizeMap() {
		return shipSizeMap;
	}
	public void setShipSizeMap(
			Map<Class<? extends AbstractShip>, Integer> shipSizeMap) {
		this.shipSizeMap = shipSizeMap;
	}
	public Map<Class<? extends AbstractShip>, Integer> getShipCountMap() {
		return shipCountMap;
	}
	public void setShipCountMap(
			Map<Class<? extends AbstractShip>, Integer> shipCountMap) {
		this.shipCountMap = shipCountMap;
	}
	
	@Override
	public void clear(){
		super.clear();
		shipClassList.clear();
		shipClassList = null;
		shipSizeMap.clear();
		shipSizeMap = null;
		shipCountMap.clear();
		shipCountMap = null;
	}
}