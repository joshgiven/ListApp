package data;

import java.util.List;

public interface EntityCrudDAO<T> {
	public List<T> index();
	public T show(int id);
	public T create(T T);
	public T update(int id, T T);
	public T destroy(int id);
}
