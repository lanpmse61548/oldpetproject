package pet.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDao<T extends Serializable> {

	private Class<T> clazz;

	@Autowired
	SessionFactory sessionFactory;

	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(Long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public List<T> findByLimitAll(int first, int last) {
		return getCurrentSession().createQuery("from " + clazz.getName()).setFirstResult(first).setMaxResults(last)
				.list();
	}

	public void save(T entity) {
		getCurrentSession().persist(entity);
	}

	public T update(T entity) {
		getCurrentSession().merge(entity);
		return entity;
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
