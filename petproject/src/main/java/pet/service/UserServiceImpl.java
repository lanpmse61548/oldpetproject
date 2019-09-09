package pet.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pet.dao.IGenericDao;
import pet.dom.UserEntity;

@Service
@Transactional(rollbackOn = Throwable.class)
public class UserServiceImpl implements IUserService {

	@Autowired
	SessionFactory sessionFactory;
	
	IGenericDao<UserEntity> dao;

	@Autowired
	@Qualifier("hibernateDao")
	public void setDao(IGenericDao<UserEntity> daoToSet) {
		dao = daoToSet;
		dao.setClazz(UserEntity.class);
	}
	
	@Override
	public UserEntity findByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findByUsernamer(String username) {
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<UserEntity>   query =builder.createQuery(UserEntity.class);
		Root<UserEntity> root =  query.from( UserEntity.class );
		
		query = query.select( root )
		.where(builder.equal( root.get( "username" ),  username));
		
		UserEntity entity=	sessionFactory.getCurrentSession().createQuery( query).getSingleResult();
		
		return entity;
	}

}
