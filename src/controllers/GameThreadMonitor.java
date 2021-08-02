package controllers;

import command.TileCommand;

import controller.interfaces.GridActionQueueInterface;
import controllers.constants.DomainControllerConstants;
import controllers.threads.GridActionThread;

public class GameThreadMonitor {
	
	private static final boolean ALLOW_LOGGING = DomainControllerConstants.ALLOW_LOGGING;
	private GridActionQueueInterface controller;
	
	public GameThreadMonitor(GridActionQueueInterface controller){
//		gridActionThread = new GridActionThread(controller);
//		this.controller = controller;
	}
	
	public void handleGridAction(TileCommand command){
		long threadTime = 0l;
		if(ALLOW_LOGGING){
			threadTime = System.nanoTime();
			System.out.print("Starting a new grid action thread...");
		}		
		(new GridActionThread(controller, command)).start(); 
		if(ALLOW_LOGGING){
			threadTime = (System.nanoTime() - threadTime);
			System.out.println("Complete. Process time: " + String.valueOf(threadTime));
		}
//		gridClickQueue.add(command);
//		if(!gridActionThread.isAlive()){
//			if(ALLOW_LOGGING){
//				System.out.println("Grid Action Thread was dormant. Running thread now...");
//			}
//			gridActionThread = new GridActionThread(controller);
//			gridActionThread.start();
//		}
	}
}