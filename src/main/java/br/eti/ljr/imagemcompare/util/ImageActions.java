package br.eti.ljr.imagemcompare.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;

public class ImageActions {

	public static ImageComparisonResult comparaImg(BufferedImage expectedImage, BufferedImage actualImage) {

		ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();
		return imageComparisonResult;
	}

	public static void saveImage(BufferedImage image, String pathResultDestination) {

		File path = new File(pathResultDestination);
		ImageComparisonUtil.saveImage(path, image);
	}

	public static BufferedImage lerImg(String path) {

		return ImageComparisonUtil.readImageFromResources(path);
	}
	
	public static BufferedImage printTela(int x, int y, int width, int height) throws AWTException {

		Robot robot = new Robot();
		Rectangle ret = new Rectangle(x, y, width, height);
		return robot.createScreenCapture(ret);
	}
}
