package org.golde.bukkit.setplaceholder;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	private int value;

	@Override
	public void onEnable() {

		getCommand("set").setExecutor(this);

		saveDefaultConfig();

		value = getConfig().getInt("value");

		// Small check to make sure that PlaceholderAPI is installed
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
			new PapiPlaceholderSet(this).register();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(!sender.hasPermission("score.use")) {
			sender.sendMessage("No permission.");
			return true;
		}
		
		if(args == null || args.length == 0) {
			sender.sendMessage("/set <number>");
			return true;
		}
		
		try {
			value = Integer.parseInt(args[0]);
			sender.sendMessage("Set placeholder to '" + args[0] + "'");
			getConfig().set("value", value);
			saveConfig();
			return true;
		}
		catch(Exception e) {
			sender.sendMessage("'" + args[0] + "' is not a number!");
			return true;
		}
	}

	public int getValue() {
		return value;
	}


}