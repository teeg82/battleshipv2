package test.guiTests;

import java.awt.image.BufferedImage;

import model.tiles.EmptyTile;

public class EmptyTileTestImage {

	public static void main(String[] args){
		EmptyTile tile = new EmptyTile();
		BufferedImage image = tile.getTileImage();
		
		if(image == null){
			System.out.println("Image is null. Didn't work.");
		}
		else{
			System.out.println("Image successfully loaded!");
			System.out.println("The image is: " + String.valueOf(image.getWidth()) + " pixels wide, and " + String.valueOf(image.getHeight() + " pixels high."));
		}
	}
}
