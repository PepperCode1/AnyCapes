package me.pepperbell.anycapes;

import java.io.File;
import java.nio.file.Path;

import me.pepperbell.anycapes.cape.CapeProvider;
import me.pepperbell.anycapes.data.Config;
import me.pepperbell.anycapes.mixinduck.PlayerSkinProviderAccess;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Util;

public class AnyCapes implements ClientModInitializer {
	private static Config config;
	
	public static Config getConfig() {
		if (config == null) {
			loadConfig();
		}
		return config;
	}
	
	private static void loadConfig() {
		Path configPath = FabricLoader.getInstance().getConfigDir();
		File configFile = new File(configPath.toFile(), "anycapes.json");
		config = new Config(configFile);
		config.load();
	}

	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register((client) -> {
			PlayerSkinProviderAccess skinProviderAccess = (PlayerSkinProviderAccess)(Object)client.getSkinProvider();
			skinProviderAccess.setCapeProvider(new CapeProvider(
				skinProviderAccess.getSkinCacheDir(),
				skinProviderAccess.getTextureManager(),
				Util.getMainWorkerExecutor(),
				client.getNetworkProxy()
			));
		});
	}
}
