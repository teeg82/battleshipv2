package model.tiles;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import model.constants.ModelConstants;

public abstract class AbstractTile extends Component{

	private static final long serialVersionUID = 1450420753656437157L;
	
	private Point position;
	private BufferedImage tileImage;
	protected Boolean selectable;

	public AbstractTile(){}
	
	/**
	 * Ensure that "selectable" is set properly in the constructors of subclasses
	 * @return A boolean indicating whether the tile can be selected
	 */
	public Boolean isSelectable(){
		return selectable;
	}
	
	public void setPosition(Point position) {this.position = position;}
	public Point getPosition() {return position;}
	
	public void setTileImage(BufferedImage tileImage) {this.tileImage = tileImage;}

	public BufferedImage getTileImage() {return tileImage;}
	
	public void repaint(Graphics graphics){
		repaint(graphics,0,0);
	}
	
	public void repaint(Graphics graphics, Integer xPos, Integer yPos){
		Point position = super.getBounds().getLocation();
		graphics.drawImage(this.getTileImage(), (position.x*ModelConstants.standardTileDimensions.width)+xPos, (position.y*ModelConstants.standardTileDimensions.height)+yPos, null);		
	}
	
	public void clear(){
//		this.position = null;
		this.tileImage = null;
		this.selectable = null;
	}
}