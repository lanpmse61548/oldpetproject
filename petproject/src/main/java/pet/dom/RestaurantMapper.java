package pet.dom;

import org.mapstruct.Mapper;

import pet.dto.DishesDTO;
import pet.dto.RestaurantDTO;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
	RestaurantDTO sourceToDestination(Restaurant source);  
	DishesDTO sourceToDestinationDishes(Dishes source);  
}