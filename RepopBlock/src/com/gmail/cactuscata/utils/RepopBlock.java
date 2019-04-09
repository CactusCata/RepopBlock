package com.gmail.cactuscata.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;

public final class RepopBlock {

	private final Map<Location, Material> cache = new HashMap<>();

	public Map<Location, Material> getCache() {
		return this.cache;
	}
	
}
