package me.pepperbell.anycapes.integration;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.pepperbell.anycapes.AnyCapes;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuImpl implements ModMenuApi {
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
			return new ClothConfigFactory(AnyCapes.getConfig());
		}
		return (screen) -> null;
	}
}