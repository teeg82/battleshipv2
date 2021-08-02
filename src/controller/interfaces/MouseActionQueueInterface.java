package controller.interfaces;

import java.awt.Point;
import java.awt.Rectangle;

import model.tiles.AbstractTile;

public interface MouseActionQueueInterface {

//	void setPosition(Point position);
	
	Rectangle getGridArea();
	
	Class<? extends AbstractTile> getTileClass(Point currentPosition);
	
//	Point getPosition();
}