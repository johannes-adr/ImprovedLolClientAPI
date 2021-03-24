package de.jadr.ddragon;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stirante.lolclient.libs.com.google.gson.Gson;

public class Champion {

	public Image image; //Path to ddragon
	public String lore;
	public String partype;
	public String title;
	public String blurb;
	public List<String> allytips;
	public Passive passive;
	public List<String> tags;
	public List<Recommended> recommended;
	public List<Skin> skins;
	public Stats stats;
	public List<String> enemytips;
	public String name; // z.B Dr. Mundo
	public String id; // z.B DrMundo
	public List<Spell> spells;
	public String key; // z. B 36
	public Info info;

	public static Champion generate(String json) {
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		try {
			return om.readValue(json, Champion.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class Image {
		public int w;
		public String sprite;

		public int x;
		public int h;
		public int y;
		public String full;
		public String group;
	}

	public static class Passive {
		public Image image;
		public String name;
		public String description;
		@Override
		public String toString() {
			return "Passive [image=" + image + ", name=" + name + ", description=" + description + "]";
		}
		
	}

	public static class Item {
		public int count;
		public boolean hideCount;
		public String id;
	}

	public static class Block {
		public int maxSummonerLevel;
		public boolean recSteps;
		public int minSummonerLevel;
		public String showIfSummonerSpell;
		public String type;
		public String hideIfSummonerSpell;
		public List<Item> items;
		public boolean recMath;
		public List<String> hiddenWithAnyOf;
		public String appendAfterSection;
		public List<String> visibleWithAllOf;
	}

	public static class Recommended {
		public String mode;
		public int sortrank;
		public boolean extensionPage;
		public Object customPanel;
		public List<Block> blocks;
		public String customTag;
		public String title;
		public String type;
		public String map;
		public String champion;
		public boolean useObviousCheckmark;
	}

	public static class Skin {
		public boolean chromas;
		public int num;
		public String name;
		public String id;
	}

	public static class Stats {
		public int mpregen;
		public int attackdamageperlevel;
		public int mp;
		public int attackrange;
		public int hpperlevel;
		public int hp;
		public int hpregen;
		public int mpregenperlevel;
		public int spellblock;
		public int critperlevel;
		public int movespeed;
		public int mpperlevel;
		public int armor;
		public double armorperlevel;
		public int crit;
		public int attackdamage;
		public double attackspeed;
		public double spellblockperlevel;
		public int attackspeedperlevel;
		public double hpregenperlevel;
		@Override
		public String toString() {
			return "ChampionStats [mpregen=" + mpregen + ", attackdamageperlevel=" + attackdamageperlevel + ", mp=" + mp
					+ ", attackrange=" + attackrange + ", hpperlevel=" + hpperlevel + ", hp=" + hp + ", hpregen="
					+ hpregen + ", mpregenperlevel=" + mpregenperlevel + ", spellblock=" + spellblock
					+ ", critperlevel=" + critperlevel + ", movespeed=" + movespeed + ", mpperlevel=" + mpperlevel
					+ ", armor=" + armor + ", armorperlevel=" + armorperlevel + ", crit=" + crit + ", attackdamage="
					+ attackdamage + ", attackspeed=" + attackspeed + ", spellblockperlevel=" + spellblockperlevel
					+ ", attackspeedperlevel=" + attackspeedperlevel + ", hpregenperlevel=" + hpregenperlevel + "]";
		}
		
	}
	/**
	 * @author johnr
	 * Useless
	 */
	public static class Datavalues {
	}

	public static class Leveltip {
		public List<String> effect;
		public List<String> label;
	}

	public static class Spell {
		public String cooldownBurn;
		public Image image;
		public List<Integer> cost;
		public Datavalues datavalues;
		public String maxammo;
		public Leveltip leveltip;
		public String resource;
		public String rangeBurn;
		public String tooltip;
		public String description;
		public List<Integer> range;
		public int maxrank;
		public List<List<Double>> effect;
		public String costType;
		public String name;
		public List<Integer> cooldown;
		public String id;
		public String costBurn;
		public List<Object> vars;
		public List<String> effectBurn;

	}

	public static class Info {
		public int magic;
		public int difficulty;
		public int defense;
		public int attack;
		@Override
		public String toString() {
			return "ChampionInfo [magic=" + magic + ", difficulty=" + difficulty + ", defense=" + defense + ", attack=" + attack
					+ "]";
		}
	}
	
	@Override
	public String toString() {
		return "Champion " + name + " [key=" + key + "  id='" + id+ "'  title='" + title+"']";
	}

}
