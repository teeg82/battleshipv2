package wrappers;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.constants.ModelConstants;

/**
 * Somewhat similar to the data representation of the Rectangle class, this
 * class represents an (X,Y) coordinate on the game grid, and a rectangular area
 * in which the validator scans for existing ship tiles.
 * @author Paul Richter
 *
 */
public class ScanArea{
	private Point upperLeftTile;
	
	private Dimension scanRectangle;
	
	public ScanArea(){
		this(new Point(0,0), new Dimension(0,0));
	}
	
	public ScanArea(Point startingPoint, Dimension scanArea){
		setScanArea(startingPoint, scanArea);
	}
	
	public void setScanArea(Point startingPoint, Dimension scanArea){
		this.scanRectangle = scanArea;
		this.upperLeftTile = this.getAdjustedPointAndScanRectangle(startingPoint);
	}
	
	public List<Point> getScanAreaPositions(){
		List<Point> scanAreaPositions = new ArrayList<Point>(scanRectangle.height * scanRectangle.width);
		
		Integer xPos = upperLeftTile.x;
		Integer yPos = upperLeftTile.y;
		for(int xIndex=0; xIndex < scanRectangle.width; xIndex++){
			for(int yIndex=0; yIndex < scanRectangle.height; yIndex++){
				scanAreaPositions.add(new Point(xPos, yPos));
				yPos++;
			}
			xPos++;
			yPos = upperLeftTile.y;
		}				

		return scanAreaPositions;
	}
	
	private Point getAdjustedPointAndScanRectangle(Point position){
		Dimension maxGridBoundaries = ModelConstants.gridDimensions;
		Integer adjustedScanWidth = scanRectangle.width;
		Integer adjustedScanHeight = scanRectangle.height;
		if(position.x < 0){
			position.x = 0;
			adjustedScanWidth -= 1;
		}
		else
			if((scanRectangle.width + position.x) > maxGridBoundaries.width)
				adjustedScanWidth -= (scanRectangle.width + position.x) - maxGridBoundaries.width;
	
		if(position.y < 0){
			position.y = 0;
			adjustedScanHeight -= 1;
		}
		else
			if((scanRectangle.height + position.y) > maxGridBoundaries.height)
				adjustedScanHeight -= (scanRectangle.height + position.y) - maxGridBoundaries.height;

		scanRectangle.setSize(adjustedScanWidth, adjustedScanHeight);
		return position;
	}
}