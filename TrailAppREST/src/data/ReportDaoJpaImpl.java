package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.TrailReport;

@Transactional
@Repository
public class ReportDaoJpaImpl implements ReportDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<TrailReport> index(int trailId) {
		String query = 
				"SELECT tr " + 
				"FROM TrailReport tr JOIN Trail t" +
		        "WHERE t = :trailId";
			
			return em.createQuery(query, TrailReport.class)
					.setParameter("trailId", trailId)
					.getResultList();
	}

}
