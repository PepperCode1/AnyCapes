package me.pepperbell.anycapes.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;

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
				.setTitle(new TranslatableText("screen.anycapes.config.title"))
				.setSavingRunnable(() -> {
					config.save();
				});
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.anycapes.general"));
		general.addEntry(entryBuilder.startStrList(new TranslatableText("option.anycapes.cape_urls"), config.getOptions().capeUrls)
				.setSaveConsumer((value) -> {
					config.getOptions().capeUrls = value;
				})
				.setTooltip(ParsingUtil.parseNewlines("option.anycapes.cape_urls.tooltip"))
				.setAddButtonTooltip(new TranslatableText("option.anycapes.cape_urls.add_url"))
				.setRemoveButtonTooltip(new TranslatableText("option.anycapes.cape_urls.remove_url"))
				.setCreateNewInstance((entry) -> {
					return new StringListCell(Language.getInstance().get("option.anycapes.cape_urls.new_url"), entry);
				})
				.setDefaultValue(Config.Options.DEFAULT.capeUrls)
				.setExpanded(true)
				.setInsertInFront(false)
				.build());
		general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.anycapes.use_caching"), config.getOptions().useCaching)
				.setSaveConsumer((value) -> {
					config.getOptions().useCaching = value;
				})
				.setTooltip(ParsingUtil.parseNewlines("option.anycapes.use_caching.tooltip"))
				.setDefaultValue(Config.Options.DEFAULT.useCaching)
				.build());

		return builder.build();
	}
}
