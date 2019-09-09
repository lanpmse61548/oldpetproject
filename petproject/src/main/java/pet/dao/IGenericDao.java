package pet.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
	void setClazz(Class<T> clazzToSet);

	T findOne(Long id);

	List<T> findAll();

	List<T> findByLimitAll(int first, int last);

	void save(T entity);

	T update(T entity);

	void delete(T entity);

	void deleteById(Long entityId);
}