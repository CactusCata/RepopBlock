package com.gmail.cactuscata.listener;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import com.gmail.cactuscata.utils.Config;
import com.gmail.cactuscata.utils.RepopBlock;

public class BlockBreakListener implements Listener {

	private final Config config;
	private final Plugin plugin;
	private final RepopBlock repopBlock;

	public BlockBreakListener(Plugin plugin, Config config, RepopBlock repopBlock) {
		this.config = config;
		this.plugin = plugin;
		this.repopBlock = repopBlock;
	}

	@EventHandler
	public final void blockBreakEvent(BlockBreakEvent event) {

		final World world = event.getBlock().getWorld();
		final Map<Material, Integer> blocs = this.config.getRepopBlockMap().get(world);

		if (blocs == null)
			return;

		final Material material = event.getBlock().getType();
		final int timeToWait = blocs.get(material);

		if (timeToWait == 0)
			return;

		Location location = event.getBlock().getLocation();
		this.repopBlock.getCache().put(location, material);

		Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {

			@Override
			public final void run() {

				if (BlockBreakListener.this.repopBlock.getCache().containsKey(location)) {

					if (!world.isChunkLoaded(world.getChunkAt(location)))
						world.loadChunk(world.getChunkAt(location));

					location.getBlock().setType(material);
					BlockBreakListener.this.repopBlock.getCache().remove(location);

				}

			}
		}, timeToWait * 20);

	}

}
