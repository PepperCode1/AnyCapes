package me.pepperbell.anycapes.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.pepperbell.anycapes.AnyCapes;

public class Config {
	private static final Gson GSON = new GsonBuilder()
			.setPrettyPrinting()
			.create();
	
	private File file;
	private Options options;
	
	public Config(File file) {
		this.file = file;
	}
	
	public Options getOptions() {
		return options;
	}
	
	public void load() {
		if (file.exists()) {
			try (FileReader reader = new FileReader(file)) {
				options = GSON.fromJson(reader, Options.class);
			} catch (IOException e) {
				AnyCapes.LOGGER.error("Error loading config", e);
			}
		} else {
			options = new Options();
			save();
		}
	}
	
	public void save() {
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(GSON.toJson(options));
		} catch (IOException e) {
			AnyCapes.LOGGER.error("Error saving config", e);
		}
	}
	
	public static class Options {
		public static final Options DEFAULT = new Options();
		
		public List<String> capeUrls = Arrays.asList(
				"{mojang}",
				"http://s.optifine.net/capes/{username}.png",
				"https://minecraftcapes.co.uk/profile/{uuid}/cape",
				"https://dl.labymod.net/capes/{uuid-dash}"
		);
		public boolean useCaching = false;
	}
}
