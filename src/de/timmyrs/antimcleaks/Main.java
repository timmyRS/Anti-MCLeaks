package de.timmyrs.antimcleaks;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Plugin
{
	static boolean enabled = true;

	static boolean isMCLeaksAccount(String uuid)
	{
		URL url;
		try
		{
			url = new URL("https://doha.blueslime.fr/api/check/" + uuid);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Anti-MCLeaks");
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			if(br.readLine().equals("{\"exists\":\"true\"}"))
			{
				return true;
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void onEnable()
	{
		getProxy().getPluginManager().registerCommand(this, new GAML());
		getProxy().getPluginManager().registerListener(this, new EventListener(this));
	}
}
