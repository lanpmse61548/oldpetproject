package pet.dto;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageDto {
	private long id;
    private long userId;
    private String pageContent;
    private CreatePageMetaDataDto metaData = new CreatePageMetaDataDto();
    private  List<ImageDivDto> divImg = new ArrayList<ImageDivDto>();
    
	public String getPageContent() {
		return pageContent;
	}
	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	public List<ImageDivDto> getDivImg() {
		return divImg;
	}
	public void setDivImg(List<ImageDivDto> divImg) {
		this.divImg = divImg;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public CreatePageMetaDataDto getMetaData() {
		return metaData;
	}
	public void setMetaData(CreatePageMetaDataDto metaData) {
		this.metaData = metaData;
	}
	
    
}
