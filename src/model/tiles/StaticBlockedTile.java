package model.tiles;


public class StaticBlockedTile extends AbstractBlockedTile {

	private static final long serialVersionUID = 3388103621861019809L;

	public StaticBlockedTile(){
		selectable = false;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public <T extends AbstractTile> T duplicateTile(AbstractTile tile) throws Exception {
//		T newTile = (T) tile.getClass().newInstance();
//		newTile.setPosition(new Point(tile.getPosition()));
//		newTile.setTileImage(tile.getTileImage());
//		return newTile;
//	}
}