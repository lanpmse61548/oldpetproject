package pet.dto;

import java.util.ArrayList;
import java.util.List;

public class CreatePageDto {
	private long id;
	private String content;
	private String imgNumsInDidv;
	private CreatePageMetaDataDto metadata;
	private List<String> removedImg = new ArrayList<>();
	private List<String> removedDiv = new ArrayList<>();
	private List<DivImgNum> imgNumInDiv = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CreatePageMetaDataDto getMetadata() {
		return metadata;
	}

	public void setMetadata(CreatePageMetaDataDto metadata) {
		this.metadata = metadata;
	}

	public List<String> getRemovedImg() {
		return removedImg;
	}

	public void setRemovedImg(List<String> removedImg) {
		this.removedImg = removedImg;
	}

	public List<String> getRemovedDiv() {
		return removedDiv;
	}

	public void setRemovedDiv(List<String> removedDiv) {
		this.removedDiv = removedDiv;
	}

	public List<DivImgNum> getImgNumInDiv() {
		return imgNumInDiv;
	}

	public void setImgNumInDiv(List<DivImgNum> imgNumInDiv) {
		this.imgNumInDiv = imgNumInDiv;
	}

	public String getImgNumsInDidv() {
		return imgNumsInDidv;
	}

	public void setImgNumsInDidv(String imgNumsInDidv) {
		this.imgNumsInDidv = imgNumsInDidv;
	}

}
