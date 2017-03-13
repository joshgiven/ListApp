package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Report;
import entities.StatusType;
import entities.TStatus;
import entities.User;

public class ReportTest {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("TrailApp");
		em = emf.createEntityManager();
	}
	
	@After
	public void tearDown() {
		if (em != null)
			em.close();
		if (emf != null)
			emf.close();
	}
	
	@Test
	public void test_findReport() {
		Report report = em.find(Report.class, 1);
		assertNotNull(report);
	}
	
	@Test
	public void test_ReportList() {
		String query = "SELECT r FROM Report r";
		
		List<Report> reports = em.createQuery(query, Report.class).getResultList();
		assertNotNull(reports);
		assertEquals(1, reports.size());
		
		Report lastReport = reports.get(reports.size()-1);
		assertNotNull(lastReport);
	}
	
	@Test
	public void test_report_fields() {
		Report report = em.find(Report.class, 1);
		assertEquals(1, report.getId());
		assertEquals("Trail conditions were awful!", report.getComment());
		assertEquals("Awful!", report.getHeading());
		
	}
	
	@Test
	public void test_report_statuses() {
		Report report = em.find(Report.class, 1);
		List<TStatus> tstatuses = report.getTStatuses();
		assertEquals(3, tstatuses.size());
		assertEquals(StatusType.snow, tstatuses.get(0).getStatusType());
		assertEquals("heavy snow", tstatuses.get(0).getName());
		
		
	}

//	@Test
//	public void test_fail() {
//		fail("meh");
//	}
}
