package de.jadr.ddragon;

import java.io.IOException;
import java.util.function.Consumer;

import org.json.JSONObject;

import de.jadr.ImprovedClientAPI;
import de.jadr.JUtils;
import de.jadr.ddragon.LolPatchList.LolPatch;

public class ChampionList {

	public final LolPatch p;
	public final Champion[] champions;

	/**
	 * 
	 * @author johnr
	 * Language of Championdatas
	 */
	public enum Language {
		cs_CZ, de_DE, el_GR, en_AU, en_GB, en_PH, en_SG, en_US, es_AR, es_ES, es_MX, fr_FR, hu_HU, it_IT, ja_JP, ko_KR,
		pl_PL, pt_BR, ro_RO, ru_RU, th_TH, tr_TR, vn_VN, zh_CN, zh_MY, zh_TW
	}

	/**
	 * 
	 * @param rawJson -> Championlist as raw json - Download the current patch and
	 *                parse it with this method
	 * @param p       -> LolPatch of this ChampionList
	 */
	public ChampionList(String rawJson, LolPatch p) {
		this.p = p;
		JSONObject jo = new JSONObject(rawJson).getJSONObject("data");
		champions = new Champion[jo.keySet().size()];
		int i = 0;
		for (String key : jo.keySet()) {
			Champion c = Champion.generate(jo.getJSONObject(key).toString());
			champions[i] = c;
			i++;
		}
	}
	/**
	 * @param name -> f.e "Dr. Mundo" (ignores case)
	 * @return champion if found - else null
	 */
	public Champion getByFullName(String name) {
		for (Champion champion : champions) {
			if(champion.name.equalsIgnoreCase(name))return champion;
		}
		return null;
	}
	
	/**
	 * @param name -> f.e "drmundo" (ignores case)
	 * @return champion if found - else null
	 */
	
	public Champion getByDataName(String name) {
		for (Champion champion : champions) {
			if(champion.id.equalsIgnoreCase(name))return champion;
		}
		return null;
	}
	
	/**
	 * @param key -> f.e 36
	 * @return champion if found - else null
	 */
	public Champion getByKey(int key) {
		String k = key+"";
		for (Champion champion : champions) {
			if(champion.key.equals(k))return champion;
		}
		return null;
	}
	

	/**
	 * 
	 * @param p -> Patch to download
	 * @throws IOException -> Error downloading championdata
	 */
	public ChampionList(LolPatch p, Language l) throws IOException {
		this(JUtils.getWebsiteContent(getDDragonChampionsURL(p.version, l)), p);
		// System.out.println(JUtils.getWebsiteContent(getDDragonChampionsURL(p.version)));
	}

	/**
	 * Download champions for current patch
	 * 
	 * @param api -> using patch list
	 * @throws IOException -> Error downloading championdata
	 */
	public ChampionList(ImprovedClientAPI api, Language l) throws IOException {
		this(api.IMPROVED.getLolPatchList().getLatest(), l);
	}

	public static String getDDragonChampionsURL(String patch, Language l) {
		return "http://ddragon.leagueoflegends.com/cdn/"+patch+".1/data/"+l+"/championFull.json";
	}

}
