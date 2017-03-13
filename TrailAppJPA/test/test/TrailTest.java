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

import entities.Trail;
import entities.User;

public class TrailTest {
	
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
	public void test_findTrail() {
		Trail trail = em.find(Trail.class, 1);
		assertNotNull(trail);
	}
	
	@Test
	public void test_TrailList() {
		String query = "SELECT t FROM Trail t";
		
		List<Trail> trails = em.createQuery(query, Trail.class).getResultList();
		assertNotNull(trails);
		assertEquals(1, trails.size());
		
		Trail lastTrail = trails.get(trails.size()-1);
		assertNotNull(lastTrail);
	}
	
	@Test
	public void test_trail_fields() {
		Trail trail = em.find(Trail.class, 1);
		assertEquals(1, trail.getId());
		assertEquals("Orange Rocks", trail.getName());
		assertEquals("Colorado", trail.getState());
		
	}
	@Test
	public void test_trail_foreign_fields() {
		Trail trail = em.find(Trail.class, 1);
		assertEquals(1, trail.getReports().size());
		assertEquals("Awful!", trail.getReports().get(0).getHeading());
		
		
		
	}

//	@Test
//	public void test_fail() {
//		fail("meh");
//	}
}
