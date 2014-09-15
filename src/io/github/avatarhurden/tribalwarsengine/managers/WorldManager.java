package io.github.avatarhurden.tribalwarsengine.managers;

import io.github.avatarhurden.tribalwarsengine.main.Configuration;
import io.github.avatarhurden.tribalwarsengine.objects.World;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class WorldManager {

	private static WorldManager instance;
	private ServerFileManager downloader;
	
	private List<World> worlds;
	private World selectedWorld;
	
	public static WorldManager get() {
		if (instance == null)
			instance = new WorldManager();
		return instance;
	}
	
	private WorldManager() {
		downloader = new ServerFileManager();
		loadConfigs();
	}
	
	private void loadConfigs() {
		JSONArray json = downloader.getServerJSON("config/servers/br", "http://www.tribalwars.com.br");
		
		worlds = new ArrayList<World>();
		for (int i = 0; i < json.length(); i++)
			worlds.add(new World(json.getJSONObject(i)));
	}
	
	public World getDefaultWorld() {
		String def = Configuration.get().getConfig("default_world_br", "");
			if (def.equals(""))
				return worlds.get(0);
	        return getWorldByName(def);
	}
	
	public void setDefaultWorld(World world) {
		Configuration.get().setConfig("default_world_br", world.getName());
	}
	
    public World getWorldByName(String name) {
        for (World server : worlds) {
            if (server.getName().equals(name)) {
                return server;
            }
        }
        return null;
    }
    
    public void setSelectedWorld(World world) {
    	selectedWorld = world;
    }
    
    public void setSelectedWorld(String world) {
    	setSelectedWorld(getWorldByName(world));
    }
    
    public static World getSelectedWorld() {
    	if (get().selectedWorld == null)
    		get().selectedWorld = get().getDefaultWorld();
    	return get().selectedWorld;
    }
    
    public List<World> getList() {
    	return worlds;
    }
}