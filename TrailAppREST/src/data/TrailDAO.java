package data;

import java.util.List;

import entities.Trail;

public interface TrailDAO extends EntityCrudDAO<Trail> {
	List<Trail> searchByState();
	
	List<Trail> searchByActivity();
	
	List<Trail> searchByStatus();
	
	List<Trail> advancedSearch();
}
