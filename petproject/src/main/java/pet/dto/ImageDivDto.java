package pet.dto;

import java.util.ArrayList;
import java.util.List;



public class ImageDivDto {
   private String divID;
   private List<String> images =new ArrayList<String>();
		public String getDivID() {
			return divID;
		}
		public void setDivID(String divID) {
			this.divID = divID;
		}
		public List<String> getImages() {
			return images;
		}
		public void setImages(List<String> images) {
			this.images = images;
		}
   
   
}
