package de.jadr;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stirante.lolclient.libs.com.google.gson.Gson;
import com.stirante.lolclient.libs.com.google.gson.JsonElement;

import de.jadr.ddragon.ChampionList;
import de.jadr.ddragon.LolPatchList;
import de.jadr.loggetter.LOGChampion.Rank;
import de.jadr.loggetter.Rune;
import de.jadr.ddragon.ChampionList.Language;

public class StiratePPTest {

	public static void main(String[] args) throws IOException {
		
		ImprovedClientAPI api = new ImprovedClientAPI();
		api.IMPROVED.waitForConnection();
		
		Rune r = api.IMPROVED.getLeagueOfGraphsChampionDetails("Yasuo", Rank.MASTER).getMostPopularRune();
		api.IMPROVED.setRunePage(LORUtils.convertLogRuneToRune(r, "LOR: Vladimir"));
		
		System.exit(0);
	}
	
}
