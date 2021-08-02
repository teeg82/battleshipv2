package test.pathTests;

import model.grid.Grid;

public class PathTest {

	public static void main(String[] args) {
		System.out.println("The grid class path should be something like: model.grid. It is actually: " + Grid.class.getPackage().getName());
	}
}