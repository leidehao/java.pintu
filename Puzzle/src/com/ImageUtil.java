package com;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {

	public static boolean cutImage(File sourcePath, int cutNumber, String savePath) {
		try {
			BufferedImage image = ImageIO.read(sourcePath);
			int allWidth = image.getWidth();
			int allHeight = image.getHeight();
			int width = allWidth / cutNumber;
			int height = allHeight / cutNumber;
			for (int i = 0; i < cutNumber; i++) {
				for (int j = 0; j < cutNumber; j++) {
					ImageIO.write(image.getSubimage(j * width, i * height, width, height), "jpg",
							new File(savePath + "\\" + (i * cutNumber + j) + ".jpg"));
				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImageUtil.cutImage(new File("img\\type1\\index.jpg"), 4, "img\\type1\\4");
	}

}
