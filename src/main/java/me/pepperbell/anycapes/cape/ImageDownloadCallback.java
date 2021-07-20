package me.pepperbell.anycapes.cape;

import java.net.URL;

import net.minecraft.client.texture.NativeImage;

public interface ImageDownloadCallback {
	void onSuccess(NativeImage nativeImage, URL url);
}
