package de.timmyrs.antimcleaks;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Main extends Plugin
{
	static boolean enabled = true;
	private static String db = "";

	static void updateDB()
	{
		try
		{
			URL url = new URL("https://raw.githubusercontent.com/IamBlueSlime/DOHA/master/db.json");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = br.readLine()) != null)
			{
				sb.append(line);
			}
			db = sb.toString();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	static boolean isMCLeaksAccount(String uuid)
	{
		return db.contains(uuid);
	}

	@Override
	public void onEnable()
	{
		getProxy().getPluginManager().registerCommand(this, new GAML());
		getProxy().getPluginManager().registerListener(this, new EventListener(this));
		getProxy().getScheduler().schedule(this, Main::updateDB, 0, 1L, TimeUnit.HOURS);
	}
}
