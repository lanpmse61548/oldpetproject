package pet.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tour")
public class Tour {
	@Id
	@GeneratedValue
	@Column(name = "tour_id")
    private Long id;
	
	 @Column(name = "tour_name",nullable = false)
	 private String tourname;
	 

	 @Column(name = "price",nullable = false)
	 private Long price;
	 
	 @Column(name = "start_place",nullable = false)
	 private String startplace;

	 @Column(name = "end_place",nullable = false)
	 private String endplace;

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getStartplace() {
		return startplace;
	}

	public void setStartplace(String startplace) {
		this.startplace = startplace;
	}

	public String getEndplace() {
		return endplace;
	}

	public void setEndplace(String endplace) {
		this.endplace = endplace;
	}
	 
	 
}
