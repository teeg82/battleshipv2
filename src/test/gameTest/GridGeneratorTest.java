package test.gameTest;

import java.text.NumberFormat;

import controllers.GridController;

public class GridGeneratorTest {

	GridController gridController;
	
	public static void main(String[] args){
		new GridGeneratorTest();
	}
	
	public GridGeneratorTest(){
		gridController = new GridController(null);
		initTest();
	}
	
	private void initTest(){
		Integer testCount = 10;
		Integer errorCount = 0;
		
		Long currentTime = System.currentTimeMillis();
		Double expectedProcessingSecondsMin = (testCount * 15) * 0.001;
		Double expectedProcessingSecondsMax = (testCount * 32) * 0.001;
		System.out.println("Excepted processing time between " + expectedProcessingSecondsMin.toString() + " and " + expectedProcessingSecondsMax.toString());
		
		try {
			generateGameGrid();
		} 
		catch (Exception e2){errorCount++;}
		
		Integer matchCount = 0;
		
		for(int index = 0; index < testCount; index++){
			try {
				generateGameGrid();
				if(gridController.isSolved()){
					matchCount++;
				}
			} catch (Exception e) {
				errorCount++;
			}
		}
		Double processingTimeSeconds = (System.currentTimeMillis() - currentTime) * 0.001;
		System.out.println("Processing time: " + processingTimeSeconds.toString());
		
		StringBuffer outputMessage = new StringBuffer();
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		outputMessage.append(numberFormat.format(testCount));
		outputMessage.append(" tests performed. ");
		if(errorCount == 0){
			outputMessage.append("All tests performed successfully. No errors reported.");
			outputMessage.append("\nNumber of grid ID matches: ");
			outputMessage.append(matchCount.toString());
		}else{
			outputMessage.append(numberFormat.format(errorCount));
			outputMessage.append(" errors were detected.");
		}
		System.out.println(outputMessage.toString());
	}
	
	public void generateGameGrid() throws Exception{
		gridController.handleGenerateGameGrid(null);
	}
}