package factories;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public final class ImageFactory {

	private ImageFactory(){}		

	public static BufferedImage createImage(String path){
		BufferedImage image;
		try {
			URL imageURL = ImageFactory.class.getResource(path);
			image = ImageIO.read(imageURL);
			return image;
		} catch (IOException e) {
			return null;
		}catch(IllegalArgumentException iae){
			System.out.println(path);
			return null;
		}
	}

	public static BufferedImage createImage(String path, int angleDegrees){
		BufferedImage image = createImage(path);
		
	    AffineTransform tx = new AffineTransform();
	    tx.rotate(Math.toRadians(angleDegrees), image.getWidth() / 2, image.getHeight() / 2);

	    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	    image = op.filter(image, null);
		
		return image;
	}
}