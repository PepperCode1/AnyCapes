package me.pepperbell.anycapes.integration;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import me.pepperbell.anycapes.data.Config;
import me.pepperbell.anycapes.util.ParsingUtil;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.StringListListEntry.StringListCell;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Language;

public class ClothConfigFactory implements ConfigScreenFactory<Screen> {
	private Config config;
	
	public ClothConfigFactory(Config config) {
		this.config = config;
	}
	
	@Override
	public Screen create(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(new TranslatableText("anycapes.configuration.title"))
				.setSavingRunnable(() -> {
					config.save();
				});
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		
		ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("anycapes.category.general"));
		general.addEntry(entryBuilder.startStrList(new TranslatableText("anycapes.options.cape_urls"), config.getOptions().capeUrls)
				.setSaveConsumer((value) -> {
					config.getOptions().capeUrls = value;
				})
				.setTooltip(ParsingUtil.parseNewlines("anycapes.options.cape_urls.tooltip"))
				.setAddButtonTooltip(new TranslatableText("anycapes.options.cape_urls.add_url"))
				.setRemoveButtonTooltip(new TranslatableText("anycapes.options.cape_urls.remove_url"))
				.setCreateNewInstance((entry) -> {
					return new StringListCell(Language.getInstance().get("anycapes.options.cape_urls.new_url"), entry);
				})
				.setDefaultValue(Config.Options.DEFAULT.capeUrls)
				.setExpanded(true)
				.setInsertInFront(false)
				.build());
		general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("anycapes.options.use_caching"), config.getOptions().useCaching)
				.setSaveConsumer((value) -> {
					config.getOptions().useCaching = value;
				})
				.setTooltip(ParsingUtil.parseNewlines("anycapes.options.use_caching.tooltip"))
				.setDefaultValue(Config.Options.DEFAULT.useCaching)
				.build());
		
		return builder.build();
	}
}
