package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Trail;

public class SearchDAOImpl implements SearchDAO {
	
	@PersistenceContext
	EntityManager em;


	@Override
	public String ping() {
		return "pong from SearchDAOImpl";
	}
	
	//

	@Override
	public List<Trail> searchByCity(String city) {
	
		String query = 
					"SELECT t " + 
					"FROM Trail t" + 
					"WHERE t.city = :city";
				
		List<Trail> trails = em.createQuery(query, Trail.class).setParameter("city", city)
			         .getResultList();
		
		return trails;
		}
	
	

	@Override
	public List<Trail> search(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
