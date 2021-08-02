package command;

import java.awt.event.MouseListener;

import model.grid.Grid;
import model.tiles.AbstractTile;

public abstract class AbstractCommand {
	private Grid grid;
	private AbstractTile tile;
	private MouseListener mouseListener;

	public void setTile(AbstractTile tile) {
		this.tile = tile;
	}

	public AbstractTile getTile() {
		return tile;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return grid;
	}
	
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}
	
	public void clear(){
		grid = null;
		tile = null;
		mouseListener = null;
	}
}