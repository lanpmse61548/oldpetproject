package pet.service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.mysema.query.jpa.impl.JPAQuery;

import pet.dao.TourRepository;
import pet.dom.QTour;
import pet.dom.QTourShort;
import pet.dom.Tour;
import pet.dom.TourShort;

@Service
//@Transactional(rollbackOn = Throwable.class)
public class TourService implements ITourService {
//	@PersistenceContext
//	private EntityManager em;
	
	private SessionFactory sf;

	@Resource
	private TourRepository tourDAO;
	
	public Iterable<Tour> searchAll() {
		//Iterable<Project> rs = new ArrayList<>();
   //      JPAQuery tmp = new JPAQuery(em);
        
    //     tourDAO.findAll(predicate);
	//	return  tmp.list(QTour.tour);
        
	//	return tmp.from(QTour.tour).list(QTour.tour);
         return null;

	}
	
	public Iterable<TourShort> searchSortAll() {
//		Iterable<Project> rs = new ArrayList<>();
//         JPAQuery tmp = new JPAQuery(em);
//         tourDAO.findAll(predicate);
//		return  tmp.list(QTour.tour);
//         Tour tmpTour = new Tour();
//         tmpTour.setPrice(new Long(11111));
//         tmpTour.setTourname("newtour");
//         tourDAO.save(tmpTour);
//		return tmp.from(QTourShort.tourShort).list(QTourShort.tourShort);

		 return null;
	}
}
