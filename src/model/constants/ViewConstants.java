package model.constants;

import java.awt.Dimension;

public final class ViewConstants {

	private ViewConstants() {}

	public static final Dimension buttonSize = new Dimension(130,20);
	
//	public static final Dimension gridPanelDimensions = new Dimension(
//			);
//	
//	public static final Dimension gridDimensions = new Dimension( 
//				ModelConstants.gridDimensions.height * ModelConstants.standardTileDimensions.height,
//				ModelConstants.gridDimensions.width * ModelConstants.standardTileDimensions.width);
//	
//	public static final Dimension controlPanelDimensions = new Dimension(384, 
//				ModelConstants.gridDimensions.height * 
//				ModelConstants.standardTileDimensions.height);
	
	public static final Dimension getControlPanelDimension(){
		return new Dimension(384, 
				ModelConstants.gridDimensions.height * 
				ModelConstants.standardTileDimensions.height);
	}
	
	public static final Dimension getGridPanelDimension(){
		Dimension gridPanelDimensions = getGridDimensions();
		gridPanelDimensions.setSize(ModelConstants.standardTileDimensions.width + gridPanelDimensions.width, 
									ModelConstants.standardTileDimensions.width + gridPanelDimensions.width);
		
		return gridPanelDimensions;
	}
	
	public static final Dimension getGridDimensions(){
		Dimension gridDimensions = new Dimension(
				ModelConstants.gridDimensions.height * ModelConstants.standardTileDimensions.height,
				ModelConstants.gridDimensions.width * ModelConstants.standardTileDimensions.width);
		return gridDimensions;
	}
}