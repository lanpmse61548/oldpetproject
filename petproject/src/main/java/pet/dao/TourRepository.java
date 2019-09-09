package pet.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import pet.dom.Tour;


public interface TourRepository extends JpaRepository<Tour, Long>, QueryDslPredicateExecutor<Tour> {

}
