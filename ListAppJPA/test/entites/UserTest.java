package entites;

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

import entities.User;

public class UserTest {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("ListApp");
		em = emf.createEntityManager();
	}
	
	@After
	public void tearDown() {
		if (em != null)
			em.close();
		if (em != null)
			emf.close();
	}
	
	@Test
	public void test_findUser() {
		User user = em.find(User.class, 1);
		assertNotNull(user);
	}
	
	@Test
	public void test_findUserList() {
		String query = "SELECT u FROM User u";
		
		List<User> users = em.createQuery(query, User.class).getResultList();
		assertNotNull(users);
		assertEquals(5, users.size());
		
		User lastUser = users.get(users.size()-1);
		assertNotNull(lastUser);
	}

	@Test
	public void test_fail() {
		fail("meh");
	}
}
