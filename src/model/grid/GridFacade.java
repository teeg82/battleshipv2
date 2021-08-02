package model.grid;

import java.util.Map;

import model.tiles.AbstractTile;

public class GridFacade extends Grid {

	private Grid gameGrid;
	private Boolean showGameBoard;

	public GridFacade() {}
	
	public GridFacade(Grid grid) {
		this.setGrid(grid);
	}
	
	public void setGrid(Grid grid) {
		this.gameGrid = grid;
	}
	
	public Boolean getShowGameBoard() {
		return showGameBoard;
	}

	public void setShowGameBoard(Boolean showGameBoard) {
		this.showGameBoard = showGameBoard;
		this.setChanged();
		this.notifyObservers();
	}

	public Grid getGrid() {
		return gameGrid;
	}
	
	@Override
	public void setGridTiles(AbstractTile[][] gridTiles){
		super.setGridTiles(gridTiles);
	}
	
	@Override
	public Map<Integer, Integer> getColumnCount() {
		return gameGrid.getColumnCount();
	}

	@Override
	public void setColumnCount(Map<Integer, Integer> columnCount) {
		gameGrid.setColumnCount(columnCount);
	}

	@Override
	public Map<Integer, Integer> getRowCount() {
		return gameGrid.getRowCount();
	}

	@Override
	public void setRowCount(Map<Integer, Integer> rowCount) {
		gameGrid.setRowCount(rowCount);
	}
	
	public boolean isSolved(){		
		return gameGrid.isGridSolved(this);	
	}
	
	@Override
	public boolean equals(Object obj){
		return gameGrid.equals(obj);
	}
	
//	@Override
//	public void clearGrid(){
//		gameGrid.clearGrid();
//		super.clearGrid();
//	}
}