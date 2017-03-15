package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Trail;

public class SearchDAOImpl implements SearchDAO {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public String ping() {
		return "pong from SearchDAOImpl";
	}
	
	public List<Trail> searchByCity(String city, String state) {
		return searchBy(city, state, null, null, null);
	}

//	@Override
//	public List<Trail> searchByState(String state) {
//	
//		String query = 
//					"SELECT t " + 
//					"FROM Trail t" + 
//					"WHERE t.city = :state";
//				
//		List<Trail> trails = em.createQuery(query, Trail.class).setParameter("state", state)
//			         .getResultList();
//		
//		return trails;
//		}
//	
//	@Override
//	public List<Trail> searchByCity(String city) {
//		
//		String query = 
//				"SELECT t " + 
//						"FROM Trail t" + 
//						"WHERE t.city = :city";
//		
//		List<Trail> trails = em.createQuery(query, Trail.class).setParameter("city", city)
//				.getResultList();
//		
//		return trails;
//	}
	
	@Override
	public List<Trail> searchByLatLong(Double originLat, Double originLong, int rangeInMiles) {
		final double MILES_IN_DEG_LAT = 69.1703234283616;
		final double DEG_LAT_PER_MILE = 1 / MILES_IN_DEG_LAT;
		double milesPerDegreeLong = MILES_IN_DEG_LAT * Math.cos(Math.toRadians(originLat));
		double degLongPerMile = 1 / milesPerDegreeLong;
		
		double minLat = originLat - (DEG_LAT_PER_MILE * rangeInMiles);
		double maxLat = originLat + (DEG_LAT_PER_MILE * rangeInMiles);
		double minLong = originLong - (degLongPerMile * rangeInMiles);
		double maxLong = originLong + (degLongPerMile * rangeInMiles);
	

		String query = 
				"SELECT t " + 
						"FROM Trail t" + 
						" WHERE t.latitude > :minLat" +
						" AND t.latitude < :maxLat" +
						" AND t.longitude > :minLong" +
						" AND t.longitude < :maxLong";
		
		List<Trail> trails = em.createQuery(query, Trail.class).setParameter("minLat", minLat)
				.setParameter("maxLat", maxLat).setParameter("minLong", minLong).setParameter("maxLong", maxLong)
				.getResultList();
		
	

		
		return trails;
	}
	
	@Override
	public List<Trail> searchBy(String city, String state, Integer radius, Integer lengthMin, Integer lengthMax) {
		
		StringBuffer query = new StringBuffer(
					"SELECT t " + 
					"FROM Trail t " + 
					"WHERE 1 = 1 ");
		
		
		if(city != null && city.length() > 0)
			query.append("  AND t.city = :city ");
		
		if(state != null && state.length() > 0)
			query.append("  AND t.state = :state ");
			
		if(lengthMin != null && lengthMin > 0)
			query.append("  AND t.length >= :lengthMin ");
		
		if(lengthMax != null && lengthMax > 0)
			query.append("  AND t.length <= :lengthMax ");

		// "radius": 25,        // t.longitude   <= west(lat, radius)  AND 
		//                      //   t.longitude >= east(lat, radius)  AND 
		//                      //   t.latitude  <= north(radius) AND 
		//                      //   t.latitude  >= south(radius)

//		if(radius != null && radius > 0)
//			query.append("  AND t.state = :state ");

		TypedQuery<Trail> q = em.createQuery(query.toString(), Trail.class);
		
		if(city != null && city.length() > 0)
			q.setParameter("city", city);
		
		if(state != null && state.length() > 0)
			q.setParameter("state", state);
		
		if(lengthMin != null && lengthMin > 0)
			q.setParameter("lengthMin", new Double(lengthMin.intValue()));
		
		if(lengthMax != null && lengthMax > 0)
			q.setParameter("lengthMax", new Double(lengthMax.intValue()));

//		if(radius != null && radius > 0)
//			query.append("  AND t.state = :state ");

		List<Trail> trails = q.getResultList();
		return trails;
	}

	@Override
	public List<Trail> search(String s) {
		// TODO Auto-generated method stub
		return null;
	}

}
