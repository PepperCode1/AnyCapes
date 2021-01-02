package me.pepperbell.anycapes.util;

import net.minecraft.client.texture.NativeImage;

public class ImageUtil {
	public static NativeImage resizeCanvas(NativeImage nativeImage, int width, int height) {
		int minWidth = Math.min(nativeImage.getWidth(), width);
		int minHeight = Math.min(nativeImage.getHeight(), height);
		NativeImage resized = new NativeImage(width, height, true);
		for (int x=0; x<minWidth; x++) {
			for (int y=0; y<minHeight; y++) {
				resized.setPixelColor(x, y, nativeImage.getPixelColor(x, y));
			}
		}
		nativeImage.close();
		return resized;
	}
	
	public static NativeImage cropAndResizeCanvas(NativeImage nativeImage, int width, int height, int left, int top, int right, int bottom) {
		int minWidth = Math.min(nativeImage.getWidth()-left-right, width);
		int minHeight = Math.min(nativeImage.getHeight()-top-bottom, height);
		NativeImage resized = new NativeImage(width, height, true);
		for (int x=0; x<minWidth; x++) {
			for (int y=0; y<minHeight; y++) {
				resized.setPixelColor(x, y, nativeImage.getPixelColor(x+left, y+top));
			}
		}
		nativeImage.close();
		return resized;
	}
}
