package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Trail;
import entities.User;

@Transactional
@Repository
public class UserDaoJpaImpl implements UserDAO {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public String ping(){
		return "pong";
	}

	@Override
	public List<User> index() {
		String query = "SELECT u FROM User u";
		return em.createQuery(query, User.class).getResultList();
	}

	@Override
	public User show(int id) {
		return em.find(User.class, id);
	}

	@Override
	public User create(User T) {
		em.persist(T);
		em.flush();
		return T;
	}

	@Override
	public User update(int id, User T) {
		User u = em.find(User.class, id);
		u.setDescription(T.getDescription());
		u.setFirstName(T.getFirstName());
		u.setLastName(T.getLastName());
		u.setPassword(T.getPassword());
		return u;
	}

	@Override
	public User destroy(int id) {
    	try {
    		User u = em.find(User.class, id);
    		em.remove(u);
    		return u;
		} catch (Exception e) {
			return null;
		}  
	}
	
	@Override
	public Set<Trail> userFavorites(int id){
		String q = "SELECT u FROM User u JOIN FETCH u.favorites WHERE u.id = :id";
		User u = em.createQuery(q, User.class).setParameter("id", id).getSingleResult();
		
		return u.getFavorites();
		
	}
	
	@Override
	public Set<Trail> addUserFavorite(int uid, int tid){
		System.out.println(uid);
		String q = "SELECT u FROM User u WHERE u.id = :id";
		User u = em.createQuery(q, User.class).setParameter("id", uid).getSingleResult();
		
		System.out.println(u.getFirstName());
		
		Trail t = em.find(Trail.class, tid);
		
		if(u.getFavorites() == null){
			u.setFavorites(new HashSet<Trail>());
		}
		u.getFavorites().add(t);
		
		em.flush();
		
		return u.getFavorites();
		
	}

}
