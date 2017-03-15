package data;

import java.util.List;

import entities.Trail;

public interface SearchDAO{
	
	public String ping();

	public List<Trail> searchByCity(String s);
	public List<Trail> search(String s);
	
}
