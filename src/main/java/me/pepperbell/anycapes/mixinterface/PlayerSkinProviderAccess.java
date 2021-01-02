package me.pepperbell.anycapes.mixinterface;

import java.io.File;

import me.pepperbell.anycapes.cape.AbstractCapeProvider;
import net.minecraft.client.texture.TextureManager;

public interface PlayerSkinProviderAccess {
	public AbstractCapeProvider getCapeProvider();
	
	public void setCapeProvider(AbstractCapeProvider capeProvider);
	
	public TextureManager getTextureManager();
	
	public File getSkinCacheDir();
}
