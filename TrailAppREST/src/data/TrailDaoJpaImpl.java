package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Trail;

@Transactional
@Repository
public class TrailDaoJpaImpl implements TrailDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Trail> index() {
		String query = 
				"SELECT t " + 
				"FROM Trail t";
			
		return em.createQuery(query, Trail.class)
		         .getResultList();
	}

	@Override
	public Trail show(int id) {
		return em.find(Trail.class, id);
	}

	@Override
	public Trail create(Trail trail) {
		em.persist(trail);
		em.flush();
		
		return show(trail.getId());
	}

	@Override
	public Trail update(int id, Trail trail) {
		Trail pTrail = show(id);
		
		if(pTrail != null) {
			pTrail.setCity(trail.getCity());
			pTrail.setState(trail.getState());
			pTrail.setName(trail.getName());
			pTrail.setDirections(trail.getDirections());
			pTrail.setLatitude(trail.getLatitude());
			pTrail.setLongitude(trail.getLongitude());
			pTrail.setDescription(trail.getDescription());
			pTrail.setLength(trail.getLength());
			pTrail.setImageUrl(trail.getImageUrl());
			
			String apiId = trail.getApiId();
			apiId = (apiId != null) ? apiId : "";
			pTrail.setApiId(apiId);
			
			em.flush();
		}
		
		return pTrail;
	}

	@Override
	public Trail destroy(int id) {
		Trail trail = em.find(Trail.class, id);
		
		if(trail != null) {
			em.remove(trail);
		}
		
		return trail;
	}

	@Override
	public List<Trail> searchByState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trail> searchByActivity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trail> searchByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trail> advancedSearch() {
		// TODO Auto-generated method stub
		return null;
	}

}
