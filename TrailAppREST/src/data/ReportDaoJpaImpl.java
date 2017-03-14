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
//		String query = 
//				"SELECT t " + 
//				"FROM Trail t WHERE t.id = :trailId";
		Trail t = em.find(Trail.class, trailId);
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
		trail.setRecentReport(report);
		return em.find(Report.class, report.getId());
	}

	@Override
	public Report update(int id, Report report) {
		Report r = em.find(Report.class, id);
		Trail trail = r.getTrail();
		r.setComment(report.getComment());
		r.setHeading(report.getHeading());
		r.setTimestamp(report.getTimestamp());
		r.setTStatuses(report.getTStatuses());
		trail.setRecentReport(report);
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

	@Override
	public Report mostRecentReport(int tid) {
		Trail trail = em.find(Trail.class, tid);
		List<Report> reports = trail.getReports();
		return reports.get(reports.size()-1);
	}

}
