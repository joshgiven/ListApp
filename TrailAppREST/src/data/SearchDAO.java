package data;

import java.util.List;

import entities.Trail;

public interface SearchDAO{
	
	public String ping();

	public List<Trail> search(String s);

	public List<Trail> searchBy(String city, String state, Integer radius, Integer lengthMin, Integer lengthMax);
	
}
