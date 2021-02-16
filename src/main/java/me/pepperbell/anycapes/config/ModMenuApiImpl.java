package me.pepperbell.anycapes.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.pepperbell.anycapes.AnyCapes;
import net.fabricmc.loader.api.FabricLoader;

public class ModMenuApiImpl implements ModMenuApi {
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
			return new ClothConfigFactory(AnyCapes.getConfig());
		}
		return (screen) -> null;
	}
}