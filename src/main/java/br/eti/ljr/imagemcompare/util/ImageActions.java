package br.eti.ljr.imagemcompare.util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;

public class ImageActions {

	public static ImageComparisonResult comparaImg(String pathExpectedImage, String pathActualImage) {
		
		BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(pathExpectedImage);
		BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(pathActualImage);

		ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();

		return imageComparisonResult;
	}

	public static void saveImageResult(ImageComparisonResult imageComparisonResult, String pathResultDestination) {

		File resultDestination = new File(pathResultDestination);

		ImageComparisonUtil.saveImage(resultDestination, imageComparisonResult.getResult());
	}
	
	public static void saveImage(BufferedImage image, String pathResultDestination) {

		File resultDestination = new File(pathResultDestination);

		ImageComparisonUtil.saveImage(resultDestination, image);
	}

	public static BufferedImage printTela() {

		try {

			Robot robot = new Robot();

			BufferedImage bi = robot.createScreenCapture(
					new Rectangle(Variaveis.telaWidth.intValue(), Variaveis.telaHeight.intValue()));

			return bi;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage printRecorte(BufferedImage imageRecorte) {

		try {

			BufferedImage biRec = imageRecorte.getSubimage(Variaveis.x, Variaveis.y, Variaveis.w,Variaveis.h);
			
			saveImage(biRec, "C:\\Users\\DEV\\Desktop\\Imagens\\Antonio pastor Café da Manhã.jpg");
			
			return biRec;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
