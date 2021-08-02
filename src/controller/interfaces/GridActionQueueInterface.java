package controller.interfaces;

import java.util.Queue;

import command.TileCommand;

public interface GridActionQueueInterface extends MouseActionQueueInterface{

	public Queue<TileCommand> getGridActionQueue();

	public void handleTileGenerator(TileCommand currentCommand);
}