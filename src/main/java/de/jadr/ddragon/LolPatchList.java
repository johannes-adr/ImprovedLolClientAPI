package de.jadr.ddragon;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import de.jadr.JUtils;

public class LolPatchList {
	
	private LolPatch[] patches;
	
	public LolPatchList() {		
		try {
			String content = JUtils.getWebsiteContent("https://raw.githubusercontent.com/CommunityDragon/Data/master/patches.json");
			JSONArray jo = new JSONObject(content).getJSONArray("patches");
			patches = new LolPatch[jo.length()];
			for(int i = 0;i < jo.length();i++) {
				JSONObject jp = jo.getJSONObject(i);
				patches[i] = new LolPatch(jp.getString("name"), jp.getInt("season"), jp.getLong("start"));
			}
		} catch (IOException e) {
			System.err.println("An error occured downloading lol patches from 'https://raw.githubusercontent.com/CommunityDragon/Data/master/patches.json'");
			System.err.println("Maybe this site isn't avaible anymore?");
		}
	}
	
	public LolPatch[] getPatches() {
		return patches;
	}
	
	public LolPatch getLatest() {
		return patches[patches.length-1];
	}
	
	public class LolPatch {
		public final String version;
		public final int season;
		public final long release;
		public LolPatch(String version, int season, long release) {
			super();
			this.version = version;
			this.season = season;
			this.release = release;
		}
		@Override
		public String toString() {
			return "LolPatch [version=" + version + ", season=" + season + ", release=" + release + "]";
		}
	}
	
}
