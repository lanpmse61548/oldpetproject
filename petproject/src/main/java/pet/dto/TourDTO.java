package pet.dto;

public class TourDTO {
	private Long id;

	private String tourname;

	private Long price;

	 private String startplace;

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