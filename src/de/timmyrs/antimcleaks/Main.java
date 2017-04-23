package de.timmyrs.antimcleaks;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Plugin
{
	static boolean enabled = true;

	static boolean isMCLeaksAccount(String name)
	{
		URL url;
		try
		{
			url = new URL("https://mcleaks.themrgong.xyz/api/v2/isnamemcleaks");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			String input = "{\"name\":\"" + name + "\"}";
			byte[] data = input.getBytes("UTF-8");
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Content-Length", Integer.toString(data.length));
			con.setRequestProperty("charset", "utf-8");
			con.setRequestProperty("User-Agent", "MCLeaksApiClient");
			OutputStream os = con.getOutputStream();
			os.write(data);
			os.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			if(br.readLine().equals("{\"isMcleaks\":true}"))
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
