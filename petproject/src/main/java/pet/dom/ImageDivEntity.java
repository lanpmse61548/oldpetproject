package pet.dom;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ImageDiv")
public class ImageDivEntity {
	private long id;
	private String divID;
    private HtmlPageEntity page;
	private Set<ImageEntity> images = new  HashSet<ImageEntity>();
    
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "div_id")
	public String getDivID() {
		return divID;
	}

	public void setDivID(String divID) {
		this.divID = divID;
	}
	


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_page_id", nullable = false)
	public HtmlPageEntity getPage() {
		return page;
	}

	public void setPage(HtmlPageEntity page) {
		this.page = page;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "div",cascade = CascadeType.ALL, orphanRemoval=true)//cascade={CascadeType.PERSIST, CascadeType.MERGE}
	public Set<ImageEntity> getImages() {
		return images;
	}

	public void setImages(Set<ImageEntity> images) {
		this.images = images;
	}

	
	
}
