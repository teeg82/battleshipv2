package test.pathTests;

import model.grid.Grid;
import utils.stringUtils.StringUtils;

public class RelativeImagePathTest {

	public static void main(String[] args) {
		Grid grid = new Grid();
		System.out.println(StringUtils.getRelativePath(grid.getClass(), "images/"));
	}
}