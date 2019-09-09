package pet.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pet.dom.Tour;
import pet.dom.TourShort;


public interface ITourService {
	public Iterable<Tour> searchAll() ;
	public Iterable<TourShort> searchSortAll();
}
