
public class BarRadius {
	public static Double swLat, neLat, swLong, neLong;
	public static boolean inRange = false;

	
	public BarRadius(String southwestLat, String northeastLat, String southwestLong, String northeastLong){
		swLat = Double.parseDouble(southwestLat);
		neLat = Double.parseDouble(northeastLat);
		swLong = Double.parseDouble(southwestLong);
		neLong = Double.parseDouble(northeastLong);
	}
	
	public Double getSWLat(){
		return swLat;
	}
	public Double getNELat(){
		return neLat;
	}
	public Double getSWLong(){
		return swLong;
	}
	public Double getNELong(){
		return neLong;
	}
	
	public boolean withinRange(Double lat, Double lng){
		if(lat > swLat & lat<neLat){
			if(lng < neLong & lng>swLong){
				inRange = true;
			}
		}
		else{
			inRange=false;
		}
		return inRange;
	}
	
	
}
