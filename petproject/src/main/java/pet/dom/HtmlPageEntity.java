package pet.dom;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "HtmlPage")
public class HtmlPageEntity implements Serializable{
	private long id;
    private byte[] pageContent;
    private String title;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private String location;
    private Long cost;
    private String type;
    private Long childCost;
    
    private Integer discountPercent;
    private Set<ImageDivEntity>  imgDiv = new HashSet<ImageDivEntity>();
    private UserEntity user;
	private long version;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	@Lob
	@Column(name = "page_content", columnDefinition="BLOB")
	public byte[] getPageContent() {
		return pageContent;
	}


	public void setPageContent(byte[] pageContent) {
		this.pageContent = pageContent;
	}


	@Version
	@Column(name = "version")
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page" ,cascade = CascadeType.ALL ,orphanRemoval=true)
	public Set<ImageDivEntity> getImgDiv() {
		return imgDiv;
	}


	public void setImgDiv(Set<ImageDivEntity> imgDiv) {
		this.imgDiv = imgDiv;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_user_id", nullable = false)
	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


    @Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

    @Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "cost")
	public Long getCost() {
		return cost;
	}


	public void setCost(Long cost) {
		this.cost = cost;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "childCost")
	public Long getChildCost() {
		return childCost;
	}


	public void setChildCost(Long childCost) {
		this.childCost = childCost;
	}

	@Column(name = "discountPercent")
	public Integer getDiscountPercent() {
		return discountPercent;
	}


	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
	
    
}
