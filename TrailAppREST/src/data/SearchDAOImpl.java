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
	public List<Trail> searchByState(String state) {
	
		String query = 
					"SELECT t " + 
					"FROM Trail t" + 
					"WHERE t.city = :state";
				
		List<Trail> trails = em.createQuery(query, Trail.class).setParameter("state", state)
			         .getResultList();
		
		return trails;
		}
	
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
		
		double maxDistInKM = rangeInMiles * 1.61;
		
		for (Trail trail : trails) {
			double dist = haversine(trail.getLatitude(), trail.getLongitude(), originLat, originLong);
			if (dist > maxDistInKM){
				trails.remove(trail);
			}
		}
		
		return trails;
	}
	
	public static double haversine(double lat1, double lng1, double lat2,  double lng2) {
	    int r = 6371; // average radius of the earth in km
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lng2 - lng1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	       Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
	      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = r * c;
	    return d;
	}
	
	

	@Override
	public List<Trail> search(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
