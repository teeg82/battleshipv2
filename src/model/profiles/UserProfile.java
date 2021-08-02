package model.profiles;

import java.io.Serializable;
import java.util.UUID;

import model.grid.GridFacade;

public class UserProfile implements Serializable{

	private static final long serialVersionUID = 7878689610652748969L;

	private UUID userID;
	private String userName;
	private Integer wins;
	private Integer gamesPlayed;
	private GridFacade grid;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getWins() {
		return wins;
	}
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	public Integer getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
	public UUID getUserID() {
		return userID;
	}
	public void clear() {
		userID = null;
		userName = null;
		wins = null;
		gamesPlayed = null;
	}
	
	public void accumulateWin(){
		wins++;
	}
	
	public void accumulateGamesPlayed(){
		gamesPlayed++;
	}
	
	public void setGrid(GridFacade grid) {
		this.grid = grid;
	}
	public GridFacade getGrid() {
		return grid;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer(userName);
		buffer.append(": ");
		buffer.append(wins);
		buffer.append(" puzzles solved, ");
		buffer.append(gamesPlayed);
		buffer.append(" games played.");
		
		if(grid!= null){
			buffer.append(" Saved puzzle available.");
		}
		
		return buffer.toString();
	}
}