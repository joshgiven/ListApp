package data;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

@Transactional
public class AuthDAOImpl implements AuthDAO {

	@PersistenceContext
	EntityManager em;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public User create(User user) {
		if( user == null || 
		    user.getPassword() == null || 
		    user.getEmail() == null ) {
			
			return null;
		}
		
		// is email already in use?
		try {
			User u = em.createQuery(
			                "SELECT u " + 
			                "FROM User u " + 
			                "WHERE email = :email", User.class)
			           .setParameter("email", user.getEmail())
			           .getSingleResult();
			
			if(u != null) {
				return null;
			}
		} 
		catch (NoResultException e) {
			// this is good, eat exception
		}
		
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		// persist the user & force EntityManager to persist immediately
		em.persist(user);
		em.flush();
		
		return user;
	}

	@Override
	public User authenticateUser(User user) throws NoResultException {
		if(user == null || user.getPassword() == null) {
			return null;
		}
		
		// Find the managed user by its username
		User u = em.createQuery("SELECT u FROM User u where email = :email", User.class)
		           .setParameter("email", user.getEmail())
		           .getSingleResult();
		
		if(u == null || u.getPassword() == null) {
			return null;
		}
		
		// One-way encrypt the provided password, see if the result matches the
		// persisted password value
		if(passwordEncoder.matches(user.getPassword(), u.getPassword())) {
			return u;
		}
		
		return null;
	}
}
