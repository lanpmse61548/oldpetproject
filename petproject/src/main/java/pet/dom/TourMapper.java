package pet.dom;

import org.mapstruct.Mapper;

import pet.dto.TourDTO;

@Mapper(componentModel = "spring")
public interface TourMapper {
    TourDTO tourToTourDTO(Tour source);
   // Tour destinationToSource(TourDTO destination);
}