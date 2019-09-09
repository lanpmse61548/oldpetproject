package pet.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Dishes")
public class Dishes {
	private Long id;
	
	private String name;
	private String description;
	private String dis_type;
	private String main_ingredient ;
	private Long price;

	private String unit  ;
	private long version;
	private Restaurant res;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dis_id")
	public Long getId() {
		return id;
	}
	public void setId(Long dis_id) {
		this.id = dis_id;
	}
	
	@Column(name = "dis_name")
	public String getName() {
		return name;
	}
	public void setName(String dis_name) {
		this.name = dis_name;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "dis_type")
	public String getDis_type() {
		return dis_type;
	}
	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}
	
	@Column(name = "main_ingredient")
	public String getMain_ingredient() {
		return main_ingredient;
	}
	public void setMain_ingredient(String main_ingredient) {
		this.main_ingredient = main_ingredient;
	}
	
	@Column(name = "price")
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	

	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Version
	@Column(name = "version")
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_res_id", nullable = false)
	public Restaurant getRes() {
		return res;
	}
	public void setRes(Restaurant res) {
		this.res = res;
	}
	
	
	
}