import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Bars {
	public static ArrayList<String> barTitles;
	public static ArrayList<Double[]> latlngs;

	public Bars() {
		
		HashMap<String, Double[]> hashDB = new HashMap<>();

		hashDB.put("Pazzos", new Double[] { 41.85, -87.88 });
		hashDB.put("Oz's bar", new Double[] { 42.00, -87.60 });
		
		ArrayList<Double[]> coords = new ArrayList<Double[]>();
		for (Double[]v: hashDB.values()) {
			coords.add(v);
		}
		latlngs = coords;
		
		ArrayList<String> titles = new ArrayList<String>();
		for (String s : hashDB.keySet()) {
			titles.add(s);
		}
		barTitles = titles;
	}

	public ArrayList<String> getTitles() {return barTitles;	}
	public ArrayList<Double[]> getCoords(){return latlngs; }

}
