package wrappers;

import java.awt.Dimension;
import java.awt.Point;

import model.constants.ModelConstants;

/**
 * A GridPoint extends the class Point, but provides the ability to
 * control the minimum and maximum values, ensuring that any Point values set
 * are within that range.
 * @author Paul Richter
 *
 */
public class GridPoint extends Point{
	private static final long serialVersionUID = 4110316262748664280L;
	
	private static Dimension maxGridBoundaries = ModelConstants.gridDimensions;
	
	public GridPoint() {
		this(0,0);
	}

	public GridPoint(GridPoint p) {
		setLocation(p);
	}

	public GridPoint(int x, int y) {
		setLocation(new GridPoint(x,y));
	}

	@Override
	public void setLocation(int x, int y){
		this.setLocation(new GridPoint(x,y));
	}
	
	@Override
	public void setLocation(Point p){
		if(p.x < 0)
			super.x = 0;
		else 
			if(p.x > (maxGridBoundaries.width-1))
				super.x = (maxGridBoundaries.width-1);
			else
				super.x = p.x;
		
		if(p.y < 0)
			super.y = 0;
		else 
			if (p.y > (maxGridBoundaries.height-1))
				super.y = (maxGridBoundaries.height-1);
			else
				super.y = p.y;
	}
	
	@Override
	public void move(int x, int y){
		this.setLocation(x, y);
	}
}