package de.jadr;

import java.io.IOException;

import com.stirante.lolclient.ApiResponse;
import com.stirante.lolclient.ClientApi;
import com.stirante.lolclient.ClientConnectionListener;

import de.jadr.ddragon.ChampionList;
import de.jadr.ddragon.LolPatchList;
import de.jadr.ddragon.ChampionList.Language;
import de.jadr.loggetter.LOGChampion;
import de.jadr.loggetter.LOGChampion.Rank;
import generated.LolGameflowGameflowPhase;
import generated.LolGameflowGameflowSession;
import generated.LolPerksPerkPageResource;

public class ImprovedClientAPI extends ClientApi {

	private LolPatchList lolpatchlist;
	private ChampionList championList;
	public final Improved IMPROVED = new Improved();
	
	/**
	 * @author johnr
	 * Useful methods to interact with the league client and ddragon
	 */
	public class Improved{
		private Improved() {}
		public LolPatchList getLolPatchList() {
			if(lolpatchlist == null)lolpatchlist = new LolPatchList();
			return lolpatchlist;
		}
		/**
		 * 
		 * @param language
		 * @return ChampionList with given language
		 * @throws IOException
		 */
		public ChampionList getLolChampionList(Language language) throws IOException {
			if(championList == null)championList = new ChampionList(getLolPatchList().getLatest(), language);
			return championList;
		}
		
		public LOGChampion getLeagueOfGraphsChampionDetails(String championName, Rank k) throws IOException {
			return new LOGChampion(championName, k);
		}
		
		public ApiResponse<LolPerksPerkPageResource[]> getRunePages() throws IOException {		
			return executeGet("/lol-perks/v1/pages", LolPerksPerkPageResource[].class);
		}
		
		public ApiResponse<Void> setRunePage(LolPerksPerkPageResource r) throws IOException {
			ApiResponse<LolPerksPerkPageResource[]> rp = getRunePages();
			if(!rp.isOk()) {
				return null;
			}
			if(rp.getResponseObject()[0].isDeletable) {
				deleteRune(rp.getResponseObject()[0]);
			}
			return executePost("/lol-perks/v1/pages/", r);
		}
		
		public ApiResponse<Void> deleteRune(LolPerksPerkPageResource r) {
			try {
				ApiResponse<Void> res = executeDelete("/lol-perks/v1/pages/"+r.id);
				return res;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		public LolGameflowGameflowPhase getCurrentGamePhase() {
			needsConnection();
			ApiResponse<LolGameflowGameflowSession> s = null;
			s = executeGet("/lol-gameflow/v1/session", LolGameflowGameflowSession.class);
			if(!s.isOk())return LolGameflowGameflowPhase.NONE;
			return s.getResponseObject().phase;
		}
		
		public void waitForConnection() {
			final Object o = this;
			addClientConnectionListener(new ClientConnectionListener() {
				public void onClientDisconnected() {
				}

				public void onClientConnected() {
					synchronized (o) {
						o.notify();
					}
				}
			});
			synchronized (o) {
				try {
					o.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	private void needsConnection() {
		if (!isConnected())
			throw new IllegalStateException("Not connected with league client!");
	}	
	@Override
	public <T> ApiResponse<T> executeGet(String path, Class<T> clz) {
		try {
			return super.executeGet(path, clz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
