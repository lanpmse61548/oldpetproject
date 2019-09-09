package pet.dto;

import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DishesDTO {
private Long id;
	
	private String name;
	private String description;
	private String dis_type;
	private Long price;
	private String imagePath;
	private List<String> imagePaths = new ArrayList<String>();
	private List<String> oldUrls = new ArrayList<String>();

	private String unit  ;
	private long version;
	public Long getId() {
		return id;
	}
	public void setId(Long dis_id) {
		this.id = dis_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String dis_name) {
		this.name = dis_name;
	}
//	@JsonIgnore
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDis_type() {
		return dis_type;
	}
	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}
	

	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@JsonIgnore
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public List<String> getImagePaths() {
		return imagePaths;
	}
	public void setImagePaths(List<String> imagePaths) {
		this.imagePaths = imagePaths;
	}
	public List<String> getOldUrls() {
		return oldUrls;
	}
	public void setOldUrls(List<String> oldUrls) {
		this.oldUrls = oldUrls;
	}
	
	
	
}