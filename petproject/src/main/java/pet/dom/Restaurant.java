package pet.dom;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Restaurant")
public class Restaurant {
	private Long res_id ;
	private String res_name; 
	private String res_address;
	private Long fk_owner;
	
	private List<Dishes> listDishes;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id")
	public Long getRes_id() {
		return res_id;
	}

	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}

	@Column(name = "res_name",nullable = false)
	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	@Column(name = "res_address",nullable = false)
	public String getRes_address() {
		return res_address;
	}

	public void setRes_address(String res_address) {
		this.res_address = res_address;
	}
  
	@JsonIgnore
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "res")
	public List<Dishes> getListDishes() {
		return listDishes;
	}

	public void setListDishes(List<Dishes> listDishes) {
		this.listDishes = listDishes;
	}
	
	
}