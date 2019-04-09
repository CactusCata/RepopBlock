package com.gmail.cactuscata;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.cactuscata.command.RepopBlockCmd;
import com.gmail.cactuscata.listener.BlockBreakListener;
import com.gmail.cactuscata.utils.Config;
import com.gmail.cactuscata.utils.RepopBlock;

public final class BlockBreak extends JavaPlugin {

	@Override
	public final void onEnable() {

		RepopBlock repopBlock = new RepopBlock();
		Config config = new Config(this);

		super.getServer().getPluginManager().registerEvents(new BlockBreakListener(this, config, repopBlock),
				this);
		super.getCommand("repopblock").setExecutor(new RepopBlockCmd(repopBlock));

	}

	@Override
	public final void onDisable() {

		
		
	}

}
