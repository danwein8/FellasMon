
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestInfo {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "https://pokemondb.net/";

	



	public static void main(String[] args) throws IOException {
		Map<String, Integer> bro = getBaseStats("charmander");
		Map<String, Map<String, String>> baseStatsMap = getAllAttacks();
		Map<Integer, String> something = getAttacks("charmander");
		ArrayList<String[]> laako = listOfFellasInArea("route-1");
		String some = type("charmander");
	
		
		

	}

	private static String sendGET(String pokemon) throws IOException {
		URL obj = new URL(GET_URL + pokemon);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append("\n" + inputLine);
			}
			
			in.close();

			return response.toString();
		} else {
		
			return "";
		}

	}
	
	
	
	static Map<String, Integer>getBaseStats(String pokemon) throws IOException {
		String s = sendGET("pokedex/" + pokemon);
		Map<String, Integer> baseStatsMap = new HashMap<String, Integer>();
		int occurence =  s.indexOf("<h2>Base stats</h2>");
		occurence =  s.indexOf("<tr>", occurence);
		int initialTh = 0;
		int endTh = 0;
		int initialTd = 0;
		int endTd = 0;
		int normalTagLength = "<th>".length();
		int otherTagLength = "<td class=\"cell-num\">".length();
		Integer val;
		for(int i = 0; i < 6; i++) {
			initialTh = s.indexOf("<th>", occurence);
			endTh = s.indexOf("</th>", occurence);
			initialTd = s.indexOf("<td class=\"cell-num\">", occurence);
			endTd = s.indexOf("</td>", occurence);
			val = Integer.valueOf(s.substring(initialTd + otherTagLength, endTd));
			baseStatsMap.put(s.substring(initialTh + normalTagLength, endTh), val);
			occurence = s.indexOf("<tr>", endTd);
			
		}
				
		return baseStatsMap;
		
	}
	

	static String type(String pokemon) throws IOException {
		String s = sendGET("pokedex/" + pokemon);
		int oc = s.indexOf("<h2>Pok??dex data</h2>");
		int normalTagLength = "href=\"/type/".length();

		
		int initialTh = s.indexOf("href=\"/type/", oc);
		int endTh = s.indexOf("\">",initialTh);
			
			
		
		
		return s.substring(initialTh + normalTagLength, endTh);
		
	}
	
	
	
	
	static Map<Integer, String> getAttacks(String pokemon) throws IOException {
		String s = sendGET("pokedex/" + pokemon);
		Map<Integer, String> learnLevelAttack = new HashMap<Integer, String>();
		int occurence =  s.indexOf("<h3>Moves learnt by level up</h3");
		occurence =  s.indexOf("<tr>", occurence);
		int initialLv = 0;
		int endLv = 0;
		int initialN = 0;
		int endN = 0;
		int initialT = 0;
		int endT = 0;
		int normalTagLength = "<th>".length();
		int moveLength = "href=\"/move/".length();
		int typeLength = "href=\"/type/".length();
		int otherTagLength = "<td class=\"cell-num\">".length();
		Integer val;
		for(int i = 0; i < 22; i++) {
			initialLv = s.indexOf("<td class=\"cell-num\">", occurence);
			endLv = s.indexOf("</td>", initialLv + 1);
			initialN = s.indexOf("href=\"/move/", occurence);
			endN = s.indexOf("title", initialN + 1);
			initialT = s.indexOf("href=\"/type/", occurence);
			endT = s.indexOf(">", initialT + 1);
			try {
				learnLevelAttack.put(Integer.valueOf(s.substring(initialLv + otherTagLength, endLv)),
						s.substring(initialN + moveLength, endN - 2));
			}catch(Exception e) {
				
			}
			
			occurence = s.indexOf("<tr>", endT);
			
		}
		

	

		
		return learnLevelAttack;
	}
	
	
	static Map<String, Map<String, String>>getAllAttacks() throws IOException {
		URL obj = new URL("https://pokemondb.net/move/all");
		
		String s = sendGET("move/all");
		Map<String, Map<String, String>> baseStatsMap = new HashMap<String, Map<String, String>>();
		int occurence =  s.indexOf("<tbody>");
		occurence =  s.indexOf("<tr>", occurence);
		int initialM = 0;
		int endM = 0;
		int initialP = 0;
		int endP = 0;
		int initialT = 0;
		int endT = 0;
		int moveLength = "href=\"/move/".length();
		int typeLength = "href=\"/type/".length();
		int otherTagLength = "<td class=\"cell-num\">".length();
		Integer val;
		while(occurence != -1) {
			initialM = s.indexOf("href=\"/move/", occurence);
			endM = s.indexOf("title", initialM + 1);
			initialT = s.indexOf("href=\"/type/", endM);
			endT = s.indexOf(">", initialT + 1);
			initialP = s.indexOf("<td class=\"cell-num\">", endT);
			endP = s.indexOf("</td>", initialP + 1);
			Map<String, String> specificAtkMap = new HashMap<String, String>();
			specificAtkMap.put("power", s.substring(initialP + otherTagLength, endP));
			specificAtkMap.put("type", s.substring(initialT + typeLength, endT - 1));
			baseStatsMap.put(s.substring(initialM + moveLength, endM - 2),specificAtkMap );
			occurence =  s.indexOf("<tr>", endP);


			
		}
		
	
		
		return baseStatsMap;
		
	}
	
	
	static ArrayList<String[]>  listOfFellasInArea(String loc) throws IOException {
		String s = sendGET("location/kanto-" + loc);
		ArrayList<String[]> randomer = new ArrayList<String[]>();
		int occurence =  s.indexOf("id=\"gen4\"");
		occurence =  s.indexOf("<tbody>", occurence);
		int initialName = 0;
		int endName = 0;
		int initialLvs = 0;
		int endLvs = 0;
		
		int nameLength = "href=\\\"/pokedex/".length() - 1;
		int levelLength = "cell-num\\\">".length() - 1;

		Integer val;
		for(int i = 0; i < 22; i++) {
			initialName = s.indexOf("href=\"/pokedex/", occurence);
			endName = s.indexOf("\" title", initialName + 1);
			initialLvs = s.indexOf("cell-num\">", occurence);
			endLvs = s.indexOf("</td>", initialLvs + 1);
	
			try {
				String[] newArr = { s.substring(initialName + nameLength, endName), s.substring(initialLvs + levelLength, endLvs )};
				randomer.add(newArr);
			}catch(Exception e) {
				
			}
			
			occurence = s.indexOf("<tr>", endLvs);
			
		}
		
		
		return randomer;
	}

	
}