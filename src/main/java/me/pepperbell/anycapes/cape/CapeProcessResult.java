package me.pepperbell.anycapes.cape;

import net.minecraft.client.texture.NativeImage;

public interface CapeProcessResult {
	NativeImage getProcessedImage();

	boolean hasElytra();

	class Impl implements CapeProcessResult {
		private NativeImage processedImage;
		private boolean hasElytra;

		public Impl(NativeImage processedImage, boolean hasElytra) {
			this.processedImage = processedImage;
			this.hasElytra = hasElytra;
		}

		@Override
		public NativeImage getProcessedImage() {
			return processedImage;
		}

		@Override
		public boolean hasElytra() {
			return hasElytra;
		}
	}
}
