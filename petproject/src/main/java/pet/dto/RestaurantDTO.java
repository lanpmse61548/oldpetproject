package pet.dto;

public class RestaurantDTO {
	private Long res_id ;
	private String res_name; 
	private String res_address;
	private Long fk_owner;
	public Long getRes_id() {
		return res_id;
	}
	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getRes_address() {
		return res_address;
	}
	public void setRes_address(String res_address) {
		this.res_address = res_address;
	}
	public Long getFk_owner() {
		return fk_owner;
	}
	public void setFk_owner(Long fk_owner) {
		this.fk_owner = fk_owner;
	}
	
	
}