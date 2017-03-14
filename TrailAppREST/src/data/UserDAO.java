package data;

import entities.User;

public interface UserDAO extends EntityCrudDAO<User>{

	public String ping();
	
}
