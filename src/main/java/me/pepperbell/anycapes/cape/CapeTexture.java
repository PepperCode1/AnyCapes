package me.pepperbell.anycapes.cape;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;

public class CapeTexture extends NativeImageBackedTexture {
	private boolean hasElytra = true;
	
	public CapeTexture(NativeImage image) {
		super(image);
	}
	
	public CapeTexture(NativeImage image, boolean hasElytra) {
		super(image);
		this.hasElytra = hasElytra;
	}
	
	public boolean hasElytra() {
		return hasElytra;
	}
}
