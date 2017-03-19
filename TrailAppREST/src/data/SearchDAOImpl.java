package data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		return searchBy(city, state, null, null, null, null, null);
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
	
	class LatLongDelta {
		private double minLat;
		private double maxLat;
		private double minLong;
		private double maxLong;
		
		LatLongDelta(Double originLat, Double originLong, int rangeInMiles) {
			final double MILES_IN_DEG_LAT = 69.1703234283616;
			final double DEG_LAT_PER_MILE = 1.0 / MILES_IN_DEG_LAT;
			double milesPerDegreeLong = MILES_IN_DEG_LAT * Math.cos(Math.toRadians(originLat));
			double degLongPerMile = 1.0 / milesPerDegreeLong;
			
			minLat = originLat - (DEG_LAT_PER_MILE * rangeInMiles);
			maxLat = originLat + (DEG_LAT_PER_MILE * rangeInMiles);
			minLong = originLong - (degLongPerMile * rangeInMiles);
			maxLong = originLong + (degLongPerMile * rangeInMiles);
		}

		public double getMinLat() {
			return minLat;
		}

		public double getMaxLat() {
			return maxLat;
		}

		public double getMinLong() {
			return minLong;
		}

		public double getMaxLong() {
			return maxLong;
		}
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
	
	private Map<String, Double> sampleLatLong(String city, String state) {
		String s = 
				"SELECT avg(t.longitude), avg(t.latitude) " + 
				"FROM Trail t " + 
				"WHERE t.city = :city " + 
				"  AND t.state = :state " +
				"  AND t.longitude <> 0 " +
				"  AND t.latitude <> 0 "
				;
		
		Object[] vals = em.createQuery(s, Object[].class)
				          .setParameter("city", city)
				          .setParameter("state", state)
				          .getSingleResult();
		
		Map<String, Double> latLong = new HashMap<>();
		latLong.put("longitude", (Double)vals[0]);
		latLong.put("latitude",  (Double)vals[1]);
		
		return latLong;
	}
	
	@Override
	public List<Trail> searchBy(String city, String state, Integer radius, Integer lengthMin, Integer lengthMax, Double lat, Double lng) {
		
		System.out.println(lat);
		System.out.println(lng);
		
		String baseQuery = 
					"SELECT t " + 
					"FROM Trail t " + 
					"WHERE 1 = 1 ";
		
		StringBuffer query = new StringBuffer(baseQuery);
		
		if(city != null && city.length() > 0 && radius == null)
			query.append("  AND t.city = :city ");
		
		if(state != null && state.length() > 0 && radius == null)
			query.append("  AND t.state = :state ");
			
		if(lengthMin != null && lengthMin > 0)
			query.append("  AND t.length >= :lengthMin ");
		
		if(lengthMax != null && lengthMax > 0)
			query.append("  AND t.length <= :lengthMax ");
		 
		if(city != null && state != null && radius != null && radius > 0) {
			query.append("  AND t.latitude  >= :minLat ");
			query.append("  AND t.latitude  <= :maxLat ");
			query.append("  AND t.longitude >= :minLong ");
			query.append("  AND t.longitude <= :maxLong ");
		}

		if(query.toString().equals(baseQuery)) {
			return Collections.emptyList();
		}
		
		TypedQuery<Trail> q = em.createQuery(query.toString(), Trail.class);

		if(city != null && city.length() > 0 && radius == null)
			q.setParameter("city", city);
		
		if(state != null && state.length() > 0 && radius == null)
			q.setParameter("state", state);
		
		if(lengthMin != null && lengthMin > 0)
			q.setParameter("lengthMin", new Double(lengthMin.intValue()));
		
		if(lengthMax != null && lengthMax > 0)
			q.setParameter("lengthMax", new Double(lengthMax.intValue()));

		Map<String, Double> latLong = null;
		if(city != null && state != null && radius != null && radius > 0) {
			latLong = sampleLatLong(city, state);
			LatLongDelta delta = new LatLongDelta(latLong.get("latitude"), latLong.get("longitude"), radius);
			
			q.setParameter("minLat", delta.getMinLat());
			q.setParameter("maxLat", delta.getMaxLat());
			q.setParameter("minLong", delta.getMinLong());
			q.setParameter("maxLong", delta.getMaxLong());
			
		}

		List<Trail> trails = q.getResultList();

		if(city != null && state != null && radius != null && radius > 0) {
			double maxDistInKM = radius * 1.61;
			
			double oLat = latLong.get("latitude");
			double oLon = latLong.get("longitude");
			
			trails = 
				trails.stream()
				      .filter(t -> maxDistInKM > haversine(t.getLatitude(),t.getLongitude(),oLat,oLon))
				      .collect(Collectors.toList());	
		}
		
		return trails;
	}

	@Override
	public List<Trail> search(String s) {

		String query = 
		"SELECT t FROM Trail t " +
		"WHERE 1 = 1 " +
		"AND t.latitude  >= :minLat " +
		"AND t.latitude  <= :maxLat " +
		"AND t.longitude >= :minLong " +  
		"AND t.longitude <= :maxLong ";
		
		double minLat = 39.686183325077884;
		double maxLat = 40.40903667492211;
		double minLong = -105.81344837787128;
		double maxLong = -104.8691716221287;
	
		return em.createQuery(query, Trail.class)
		         .setParameter("minLat", minLat)
		         .setParameter("maxLat", maxLat)
		         .setParameter("minLong", minLong)
		         .setParameter("maxLong", maxLong)
		         .getResultList();
	}

}
