package support.resolvers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Queue;

import utils.stringUtils.StringUtils;

import model.constants.ModelConstants;
import model.tiles.AbstractTile;
import controller.interfaces.GridActionQueueInterface;

public class TilePointResolver {

	public static boolean ALLOW_LOGGING = false;
	
	public static final void allowLogging(boolean allowLogging){
		ALLOW_LOGGING = allowLogging;
	}
	
	public Point getResolvedPoint(MouseEvent mouseEvent){
		Point mousePosition = mouseEvent.getPoint();
		Point position = getCurrentTilePoint(mouseEvent);
		if(position != null){
				this.currentTile.setLocation(position.getX(), position.getY());
				controller.setTile(currentTile);
			if(ALLOW_LOGGING){
				Class<? extends AbstractTile> currentTile = controller.getTileClass(currentTile);
				reportMousePositionInformation(String.valueOf(mousePosition.getX()), String.valueOf(mousePosition.getY()), currentTile);
			}			
		}
	}	
	
	private Point getCurrentTilePoint(MouseEvent mouseEvent){
		Point mousePosition = mouseEvent.getPoint();
		Double xMousePosition = mousePosition.getX();
		Double yMousePosition = mousePosition.getY();
		Point currentPosition = null;
		if(isWithinGrid(xMousePosition, yMousePosition)){
			Integer xPosition = (((Double)(xMousePosition/ModelConstants.standardTileDimensions.getWidth())).intValue())-1;
			Integer yPosition = (((Double)(yMousePosition/ModelConstants.standardTileDimensions.getHeight())).intValue())-1;
			currentPosition = new Point();
			currentPosition.setLocation(xPosition, yPosition);			
		}
		return currentPosition;
	}
	
	private void reportMousePositionInformation(String xMousePosition, String yMousePosition, Class<? extends AbstractTile> currentTile){
		StringBuffer output = new StringBuffer();
		output.append("Mouse at(X,Y): ");
		output.append(xMousePosition.toString());
		output.append(",");
		output.append(yMousePosition.toString());
		output.append(". Should be looking at tile(X,Y): ");
		output.append(String.valueOf(currentTile.getX()));
		output.append(",");
		output.append(String.valueOf(currentTile.getY()));
		output.append(" . Current tile is: ");
		output.append(StringUtils.getShortClassName(currentTile));
		System.out.println(output.toString());
	}
	
	
	private Boolean isWithinGrid(Double xPos, Double yPos){
		if(xPos >= controller.getGridArea().getLocation().getX() && yPos >= controller.getGridArea().getY()){
			return true;
		}
		return false;
	}
	
	public void stopRun(){
		
	}
}