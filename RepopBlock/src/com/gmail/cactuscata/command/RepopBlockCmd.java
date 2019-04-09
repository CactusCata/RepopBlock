package com.gmail.cactuscata.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.cactuscata.utils.RepopBlock;

public final class RepopBlockCmd implements CommandExecutor {

	private final RepopBlock repopBlock;

	public RepopBlockCmd(RepopBlock repopBlock) {
		this.repopBlock = repopBlock;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {
			sender.sendMessage("§cVeuillez préciser un argument !");
			return true;
		}

		if (args[0].equalsIgnoreCase("reload")) {
			for (final Location location : this.repopBlock.getCache().keySet()) {

				final World world = location.getWorld();

				if (!world.isChunkLoaded(world.getChunkAt(location)))
					world.loadChunk(world.getChunkAt(location));

				location.getBlock().setType(this.repopBlock.getCache().get(location));
				this.repopBlock.getCache().remove(location);
			}
			return true;
		}

		return true;
	}

}
