package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Report;
import entities.Trail;
import entities.User;

@Transactional
@Repository
public class ReportDaoJpaImpl implements ReportDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Report> index(int trailId) {
		String query = 
				"SELECT t " + 
				"FROM Trail t WHERE t.id = :trailId";
		Trail t = em.createQuery(query, Trail.class)
					.setParameter("trailId", trailId)
					.getSingleResult();
		return t.getReports();
	}

	@Override
	public Report show(int id) {
		return em.find(Report.class, id);
	}

	@Override
	public Report create(Report report, int tid, int uid) {
		Trail trail = em.find(Trail.class, tid);
		User user = em.find(User.class, uid);
		report.setTrail(trail);
		report.setUser(user);
		em.persist(report);
		em.flush();
		return em.find(Report.class, report.getId());
	}

	@Override
	public Report update(int id, Report report) {
		Report r = em.find(Report.class, id);
		r.setComment(report.getComment());
		r.setHeading(report.getHeading());
		r.setTimestamp(report.getTimestamp());
		r.setTStatuses(report.getTStatuses());
		return r;
	}

	@Override
	public Report destroy(int id) {
    	try {
    		Report u = em.find(Report.class, id);
    		em.remove(u);
    		return u;
		} catch (Exception e) {
			return null;
		}  
	}

}
