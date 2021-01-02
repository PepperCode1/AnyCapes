package me.pepperbell.anycapes.cape;

import java.io.File;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Executor;

import com.mojang.authlib.GameProfile;

import me.pepperbell.anycapes.AnyCapes;
import me.pepperbell.anycapes.util.ImageUtil;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.TextureManager;

public class CapeProvider extends AbstractCapeProvider {
	public CapeProvider(File skinCacheDir, TextureManager textureManager, Executor executor, Proxy proxy) {
		super(skinCacheDir, textureManager, executor, proxy);
	}

	@Override
	public List<String> getCapeUrls() {
		return AnyCapes.getConfig().getOptions().capeUrls;
	}

	@Override
	public boolean useCaching() {
		return AnyCapes.getConfig().getOptions().useCaching;
	}

	@Override
	public URL formatUrl(String urlStr, GameProfile gameProfile, String mojangCapeUrl) {
		if (urlStr.contains("{mojang}")) {
			if (mojangCapeUrl == null) {
				return null;
			} else {
				urlStr = urlStr.replace("{mojang}", mojangCapeUrl);
			}
		}
		
		urlStr = urlStr.replace("{username}", gameProfile.getName())
				.replace("{uuid}", gameProfile.getId().toString().replace("-", ""))
				.replace("{uuid-dash}", gameProfile.getId().toString());
		
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			AnyCapes.LOGGER.warn("Invalid URL: " + urlStr);
		}
		return url;
	}

	@Override
	public CapeTransformResult transformCapeImage(NativeImage capeImage) {
		NativeImage transformed;
		boolean hasElytra = true;
		
		if (capeImage.getWidth()%46==0 && capeImage.getHeight()%22==0) {
			int scale = capeImage.getWidth()/46;
			transformed = ImageUtil.resizeCanvas(capeImage, scale*64, scale*32);
		} else if (capeImage.getWidth()%22==0 && capeImage.getHeight()%17==0) {
			int scale = capeImage.getWidth()/22;
			transformed = ImageUtil.resizeCanvas(capeImage, scale*64, scale*32);
			hasElytra = false;
		} else if (capeImage.getWidth()%355==0 && capeImage.getHeight()%275==0) {
			int scale = capeImage.getWidth()/355;
			transformed = ImageUtil.cropAndResizeCanvas(capeImage, scale*1024, scale*512, scale*2, scale*2, scale, scale);
			hasElytra = false;
		} else if (capeImage.getWidth()%352==0 && capeImage.getHeight()%275==0) {
			int scale = capeImage.getWidth()/352;
			transformed = ImageUtil.cropAndResizeCanvas(capeImage, scale*1024, scale*512, 0, scale*2, 0, scale);
			hasElytra = false;
		} else if (capeImage.getWidth()%355==0 && capeImage.getHeight()%272==0) {
			int scale = capeImage.getWidth()/355;
			transformed = ImageUtil.cropAndResizeCanvas(capeImage, scale*1024, scale*512, scale*2, 0, scale, 0);
			hasElytra = false;
		} else {
			transformed = capeImage;
		}
		
		return new CapeTransformResult(transformed, hasElytra);
	}
}
