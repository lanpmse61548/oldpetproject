package pet.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Image")
public class ImageEntity {
private String url;
private ImageDivEntity div;
 
@Id
@Column(name = "url")
public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "fk_div_id", nullable = false)
public ImageDivEntity getDiv() {
	return div;
}

public void setDiv(ImageDivEntity div) {
	this.div = div;
}
  
 
}
