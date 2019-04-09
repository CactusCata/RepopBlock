package com.gmail.cactuscata.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public final class Config {

	private final Map<World, Map<Material, Integer>> repopBlocks = new HashMap<>();

	public Config(Plugin plugin) {

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();

		for (String world : plugin.getConfig().getConfigurationSection("worlds").getKeys(false)) {

			Map<Material, Integer> blocs = new HashMap<>();

			for (String material : plugin.getConfig().getConfigurationSection("worlds." + world).getKeys(false))
				blocs.put(Material.getMaterial(material),
						plugin.getConfig().getInt("worlds." + world + "." + material));

			this.repopBlocks.put(Bukkit.getWorld(world), blocs);

		}

	}

	public Map<World, Map<Material, Integer>> getRepopBlockMap() {
		return this.repopBlocks;
	}

}
