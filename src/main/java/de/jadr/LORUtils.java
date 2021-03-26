package de.jadr;

import java.util.ArrayList;

import de.jadr.loggetter.Rune;
import generated.LolPerksPerkPageResource;

public class LORUtils {

	public static LolPerksPerkPageResource convertLogRuneToRune(Rune s, String name) {
		LolPerksPerkPageResource r = new LolPerksPerkPageResource();
		r.name = name;
		r.id = (int) 1.347237827E9;
		r.isActive = false;
		r.isDeletable = true;
		
		r.lastModified = System.currentTimeMillis();
		r.order = (int) s.order;
		r.primaryStyleId = s.primary.keyStone;
		ArrayList<Integer> perks = new ArrayList<Integer>();
		for (Integer i : s.getSelectedPerkIds())perks.add(i);
		r.selectedPerkIds = perks;
		r.subStyleId = s.secondary.keyStone;
		return r;
	}
	
}
