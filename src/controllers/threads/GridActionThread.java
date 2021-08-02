package controllers.threads;

import command.TileCommand;

import controller.interfaces.GridActionQueueInterface;

public class GridActionThread extends AbstractGameThread {

	private GridActionQueueInterface controller;
	private TileCommand command;
	
	public GridActionThread(GridActionQueueInterface controller, TileCommand command) {
		this.controller = controller;
		this.command = command;
	}

	@Override
	public void run(){
		handleStartOperation();
		try{processGridActions();}
		catch(Exception e){}
		handleCloseOperation();
	}
	
	protected void handleStartOperation(){
//		isRunning = true;
	}
	
	private void processGridActions() throws Exception {
//		Queue<TileCommand> gridActionQueue = controller.getGridActionQueue();
//		int actionsHandled = 0;
//			TileCommand currentCommand = gridActionQueue.poll();
			if(command != null){
				Long startTime = System.nanoTime();
				controller.handleTileGenerator(command);
//				actionsHandled++;
				Long processTime = System.nanoTime() - startTime;
				System.out.println("Click action required " + processTime + " nano seconds.");
			}
//			Thread.sleep(1);
//		}while(isRunning);
//		}while(!gridActionQueue.isEmpty());
		//System.out.println("[GridActionThread] I have handled " + actionsHandled + " click actions.");
	}
	
	public void stopRun() {
//		controller.getGridActionQueue().clear();
	}
}