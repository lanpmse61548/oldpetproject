package pet.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tour")
public class TourShort {
	@Id
	@GeneratedValue
	@Column(name = "tour_id")
    private Long id;
	
	 @Column(name = "tour_name",nullable = false)
	 private String tourname;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTourname() {
		return tourname;
	}

	public void setTourname(String tourname) {
		this.tourname = tourname;
	}
	 
	 
}
