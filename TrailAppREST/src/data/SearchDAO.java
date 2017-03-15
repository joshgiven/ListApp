package data;

import java.util.List;

import entities.Trail;

public interface SearchDAO{
	
	public String ping();
	
	//public List<Trail> searchByState(String state);
	
	//public List<Trail> searchByCity(String s);
	
	public List<Trail> searchByLatLong(Double originLat, Double originLong, int rangeInMiles);

	public List<Trail> search(String s);

	public List<Trail> searchBy(String city, String state, Integer radius, Integer lengthMin, Integer lengthMax);
	
}
