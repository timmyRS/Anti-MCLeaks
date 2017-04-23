package de.timmyrs.antimcleaks;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class GAML extends Command
{
	GAML()
	{
		super("gaml", "antimcleaks.command.gaml");
	}

	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(args.length == 0)
		{
			if(Main.enabled)
			{
				sender.sendMessage(new ComponentBuilder("Anti-MCLeaks is currently enabled.").color(ChatColor.GREEN).create());
			} else
			{
				sender.sendMessage(new ComponentBuilder("Anti-MCLeaks is currently disabled.").color(ChatColor.RED).create());
			}
		} else
		{
			switch(args[0])
			{
				case "disable":
					Main.enabled = false;
					sender.sendMessage(new ComponentBuilder("Anti-MCLeaks is now disabled.").color(ChatColor.RED).create());
					break;
				case "enable":
					Main.enabled = true;
					sender.sendMessage(new ComponentBuilder("Anti-MCLeaks is now enabled.").color(ChatColor.GREEN).create());
					break;
				default:
					boolean uuid = args[0].length() > 16;
					if(Main.isMCLeaksAccount(args[0], uuid))
					{
						sender.sendMessage(new ComponentBuilder("\"" + args[0] + "\" seems to be an MCLeaks account.").color(ChatColor.RED).create());
					} else
					{
						sender.sendMessage(new ComponentBuilder("\"" + args[0] + "\" doesn't seem to be an MCLeaks account.").color(ChatColor.GREEN).create());
					}
					break;
			}
		}
	}
}
