package me.pepperbell.anycapes.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.pepperbell.anycapes.AnyCapes;
import net.fabricmc.loader.api.FabricLoader;

@SuppressWarnings("deprecation")
public class ModMenuApiImpl implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
			return new ClothConfigFactory(AnyCapes.getConfig());
		}
		return screen -> null;
	}
}