package test.gameTest;

import controllers.GridController;

public class IDNumberTest {

	public static void main(String[] args0) throws Exception{
		GridController controller = new GridController(null);
		
		controller.handleGenerateGameGrid(null);
		Integer gridID = controller.getGridID();
		Integer testID = controller.getGridID();
		
		StringBuffer testString = new StringBuffer();
		testString.append("Current ID value: ").append(gridID.toString()).append(". Test ID Value: ").append(testID.toString());
		
		if(controller.gridEquals(testID)){
			testString.append(". Match successful!");
		}else{
			testString.append(". Match not successful.:(");
		}
		
		System.out.println(testString.toString());
	}
}