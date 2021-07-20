package me.pepperbell.anycapes.mixinterface;

import java.io.File;

import me.pepperbell.anycapes.cape.CapeProvider;
import net.minecraft.client.texture.TextureManager;

public interface PlayerSkinProviderAccess {
	CapeProvider getCapeProvider();

	void setCapeProvider(CapeProvider capeProvider);

	TextureManager getTextureManager();

	File getSkinCacheDir();
}
