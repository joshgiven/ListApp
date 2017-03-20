package data;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	private Map<String, Double> actualLatLong(Double lat, Double lng) {
		Map<String, Double> latLong = new HashMap<>();
		latLong.put("longitude", lng);
		latLong.put("latitude",  lat);
		
		return latLong;
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
	public List<Trail> searchBy(
			String city, String state, Integer radius, 
			Integer lengthMin, Integer lengthMax, 
			Double lat, Double lng) {

		boolean locationBased = false;
		if(Arrays.asList(city, state, lat, lng)
		         .stream().anyMatch(x -> x != null)) {
			locationBased = true;
		}
		
		List<String> qConditions = new ArrayList<>();
		Map<String, Object> qParams = new HashMap<>();
		Map<String, Double> latLong = null;

		if(lengthMin != null && lengthMin > 0) {
			qConditions.add(" AND t.length >= :lengthMin ");
			qParams.put("lengthMin", new Double(lengthMin.intValue()));
		}
		
		if(lengthMax != null && lengthMax > 0) {
			qConditions.add(" AND t.length <= :lengthMax ");
			qParams.put("lengthMax", new Double(lengthMax.intValue()));
		}
		
		
		boolean trimCorners = false;
		
		if(locationBased) {
			qConditions.add(" AND (1=1 ");
			
			if(city != null)
			{
				qConditions.add(" AND t.city = :city ");
				qParams.put("city", city);
			}
			
			if(state != null)
			{
				qConditions.add(" AND t.state = :state ");
				qParams.put("state", state);
			}
				
			if( ((lat != null && lng != null) || 
			     (city != null && state != null)) && 
				radius != null && radius > 0) {
				
				if(city != null || state != null) {
					qConditions.add(" OR ");
				}
				else {
					qConditions.add(" AND  ");
				}
				
				qConditions.add(" ( t.latitude  >= :minLat ");
				qConditions.add(" AND t.latitude  <= :maxLat ");
				qConditions.add(" AND t.longitude >= :minLong ");
				qConditions.add(" AND t.longitude <= :maxLong ) ");

				
				latLong = (lat != null && lng != null) ? actualLatLong(lat,lng) 
                                                       : sampleLatLong(city, state);

				LatLongDelta delta = new LatLongDelta(latLong.get("latitude"), 
				                                      latLong.get("longitude"), 
				                                      radius);

				qParams.put("minLat", delta.getMinLat());
				qParams.put("maxLat", delta.getMaxLat());
				qParams.put("minLong", delta.getMinLong());
				qParams.put("maxLong", delta.getMaxLong());
				trimCorners = true;
			}
			
			qConditions.add(") ");
		}
		
		if(qConditions.isEmpty()) {
			return Collections.emptyList();
		}

		String baseQuery = 
					"SELECT t " + 
					"FROM Trail t " + 
					"WHERE 1=1 ";
		
		StringBuilder query = new StringBuilder(baseQuery);
		
		qConditions.forEach((condition) -> {
			query.append(condition);
		});
		
		//System.out.println(query);
		
		TypedQuery<Trail> q = em.createQuery(query.toString(), Trail.class);

		qParams.forEach((param, val) -> {
			q.setParameter(param, val);
		});
		
		List<Trail> trails = q.getResultList();

		if(trimCorners) {
			double maxDistInKM = radius * 1.61;
			
			double oLat = latLong.get("latitude");
			double oLon = latLong.get("longitude");
			
			trails = trails.stream().filter((t) -> {
				double distance = haversine(t.getLatitude(),t.getLongitude(),oLat,oLon);
				return (t.getCity().equals(city) && t.getCity().equals(city)) ||
				        distance <= maxDistInKM;
			}).collect(Collectors.toList());	
		}
		
		return trails;
	}

	@Override
	public List<Trail> searchByKeywords(List<String> keywords) {
		List<Trail> trails = Collections.emptyList();
		
		Map<String, String> params = new HashMap<>();
		for(int i=0; i < keywords.size(); i++) {
			String kw = keywords.get(i);
			if(kw != null && kw.length() > 0) {
				String pname = "param" + i;
				params.put(pname, kw);
			}
		}
		
		if(params.size() > 0) {
			StringBuilder q = new StringBuilder(
				"SELECT t " +
				"FROM Trail t " +
				"WHERE 1=0 "
				);
			
			List<String> colNames = Arrays.asList("name"/*, "city", "state", "description"*/);
			colNames.forEach((col) -> {
				q.append(" OR ( 1=1 ");
				params.forEach((pname, pval) -> {
					q.append(" AND t." + col + " LIKE :" + pname + " ");		
				});
				q.append(" ) ");
			});
			
			TypedQuery<Trail> query = em.createQuery(q.toString(), Trail.class);
			params.forEach((pname, pval) -> {
				query.setParameter(pname, "%" + pval + "%");		
			});
			
			trails = query.getResultList();
		}
		
		return trails;
	}

}
