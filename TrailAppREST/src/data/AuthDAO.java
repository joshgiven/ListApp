package data;

import javax.persistence.NoResultException;

import entities.User;

public interface AuthDAO {
	public User create(User user);
	public User authenticateUser(User user) throws NoResultException ;
}
