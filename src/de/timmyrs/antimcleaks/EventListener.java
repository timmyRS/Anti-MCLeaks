package de.timmyrs.antimcleaks;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class EventListener implements Listener
{
	private Main plugin;

	EventListener(Main main)
	{
		this.plugin = main;
	}

	private static void handlePlayer(ProxiedPlayer p)
	{
		System.out.println(p.getUniqueId().toString());
		if(Main.isMCLeaksAccount(p.getUniqueId().toString(), true))
		{
			p.disconnect(new ComponentBuilder("We suspect that your account might not be *your* account.").color(ChatColor.RED).create());
		}
	}

	@EventHandler
	@SuppressWarnings("unused")
	public void onPostLogin(final PostLoginEvent e)
	{
		if(Main.enabled)
		{
			ProxyServer.getInstance().getScheduler().runAsync(plugin, ()->
					EventListener.handlePlayer(e.getPlayer()));
		}
	}
}
