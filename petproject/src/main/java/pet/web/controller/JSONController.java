package pet.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pet.config.AppConfig;
import pet.dom.Dishes;
import pet.dom.HtmlPageEntity;
import pet.dom.ImageDivEntity;
import pet.dom.ImageEntity;
import pet.dom.Tour;
import pet.dom.TourMapper;
import pet.dom.TourShort;
import pet.dto.CreatePageDto;
import pet.dto.DishesDTO;
import pet.dto.DivImgNum;
import pet.dto.HtmlPageDto;
import pet.dto.ImageDivDto;
import pet.dto.TourDTO;
import pet.dto.TourListDto;
import pet.service.IHtmlPageService;
import pet.service.ITourService;

@Controller
@CrossOrigin(origins = "*")
public class JSONController {

//	@Autowired
//	@Qualifier("fuckThisShit")
//	private ITourService sv;
//
//	@Autowired
//	private IHtmlPageService htmlPageService;
//	
//	@Autowired
//	private HttpServletRequest request;
//	
//	
//	
//	private static final Logger LOGGER = Logger.getLogger(JSONController.class.getName());
//
//	@RequestMapping(value = "/getAllTour", method = RequestMethod.GET)
//	public @ResponseBody List<TourDTO> getTourInJSON() {
//		try {
//			List<TourDTO> ldto = new ArrayList<TourDTO>();
//			Iterable<Tour> tmp = sv.searchAll();
//			
//			
//			Iterator<Tour> it =tmp.iterator();
//			while (it.hasNext()) {
//			 Tour i = it.next();
//				if(i.getId().equals(0)){
//					it.remove();
//				}
//			}
//
//			TourMapper mapper = Mappers.getMapper(TourMapper.class);
//			for (Tour tour : tmp) {
//				TourDTO tmpDto = mapper.tourToTourDTO(tour);
//				ldto.add(tmpDto);
//			}
//			return ldto;
//		} catch (Exception e) {
//			LOGGER.log(Level.FINE, "shit happen", e);
//
//		}
//		return null;
//	}
//
//	
//	
//	@RequestMapping(value = "/param", method = RequestMethod.GET)
//	public @ResponseBody Iterable<TourShort> setShopInJSON(@RequestParam("id") int id) {
//
//		return sv.searchSortAll();
//
//	}
//	
//	@RequestMapping(value = "/getNewTour", method = RequestMethod.POST)
//    public @ResponseBody HtmlPageDto getHtmPage(){
//		HtmlPageDto dto = new HtmlPageDto();
//		dto.setId(0);
//		return dto;
//	}
//	
//	
//	
//	@RequestMapping(value = "/getTour/{id}", method = RequestMethod.POST)
//    public @ResponseBody HtmlPageDto getHtmPage(@PathVariable("id") long id){
//	
//		HtmlPageEntity entity=	htmlPageService.findByID(id);
//		return mapDto(entity);
//	}
//
//
//
//	private HtmlPageDto mapDto(HtmlPageEntity entity) {
//		HtmlPageDto dto = new HtmlPageDto();
//		if(entity !=null){
//	
//		
//		for (ImageDivEntity element : entity.getImgDiv()) {
//			ImageDivDto div = new ImageDivDto();
//			div.setDivID(element.getDivID());
//			for (ImageEntity img : element.getImages()) {
//				div.getImages().add(img.getUrl());
//			}
//			dto .getDivImg().add(div);
//		}
//		dto.setPageContent(new String(entity.getPageContent()));
//		dto.setUserId(entity.getUserId());
//		dto.setId(entity.getId());   
//		}
//		
//		return dto;
//	}
//	
//	
//	@RequestMapping(value = "/getTours", method = RequestMethod.GET)
//    public @ResponseBody List<TourListDto> getTours(){
//	
//
//		List<TourListDto> tourList = new ArrayList<TourListDto>();
//		
//		List<HtmlPageEntity> dummyPageList = htmlPageService.searchAll();
//		for (HtmlPageEntity entity : dummyPageList) {
//			HtmlPageDto htmlPageDto =mapDto(entity);
//			TourListDto tmp = new TourListDto();
//			if(!htmlPageDto.getDivImg().isEmpty()){
//			tmp.setUrl(htmlPageDto.getDivImg().get(0).getImages().get(0));
//			}
//			tmp.setTitle("random tile");
//			 tourList.add(tmp);
//		}
//		
//		return tourList;
//	}
	
	
	
//	@RequestMapping(value = "/dish", method = RequestMethod.POST)
//	public @ResponseBody List<DishesDTO> getDish() {
////		DishesDTO dto1 = new DishesDTO();
////        dto1.setId(new Long(1));
////        dto1.setName("pho");
////        dto1.setPrice(new Long(13321));
////        DishesDTO dto2 = new DishesDTO();
////        dto2.setId(new Long(2));
////        dto2.setName("my");
////        dto2.setPrice(new Long(132));
////        List<DishesDTO> dish = new ArrayList<DishesDTO>();
////        dish.add(dto2);
////        dish.add(dto1);
//		return AppConfig.dummyList;

//	}
//	@ResponseBody
//	@RequestMapping(value = "/createdish", method = RequestMethod.POST)
//    public DishesDTO createdish(@RequestBody DishesDTO dto){
//	   dto.setId(new Long(AppConfig.dummyList.size()));
//		AppConfig.dummyList.add(dto);
//		
//		return dto;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/createdish2", method = RequestMethod.POST)
//    public  DishesDTO createdish( @RequestPart("uploadFile") MultipartFile[] file, @RequestPart("info") DishesDTO dto){
//		// TODO move code to service
//		// create file and prepare file link for Page dto
//		FileOutputStream fos = null;
//		for (MultipartFile multipartFile : file) {
//			String filename = multipartFile.getOriginalFilename();
//			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssssss").format(Calendar.getInstance().getTime());
//			int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
//
//			File img = new File(
//					"D:/apache-tomcat-8.5.20/webapps/image/" + timeStamp + "_" + randomNum + "_" + filename);
//			boolean isFirstImg = true;
//			try {
//
//				fos = new FileOutputStream(img);
//				fos.write(multipartFile.getBytes());
//
//				img.createNewFile();
//
//				// set the first image
//				if (isFirstImg) {
//					dto.setImagePath("http://localhost:8080/image/" + timeStamp + "_" + filename);
//				}
//				isFirstImg = false;
//				dto.getImagePaths().add("http://localhost:8080/image/" + timeStamp + "_" + filename);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if(fos !=null){
//					fos.close();
//					}
//				} catch (IOException e) {
//					//Ignore
//				}
//			}
//		}
//		
//		//TODO  this shit is wrong
//		//numer of img that is removed in frond-end
//		List<Integer> tobeRemove = new ArrayList<Integer>();
//		
//		 for (String oldUrl : dto.getOldUrls()) {
//				for (String current : dto.getImagePaths()) {
//                    if(oldUrl.equals(current)){
//                    tobeRemove.add(dto.getImagePaths().indexOf(current));
//                    }
//				}
//		}
//		 
//		for (Integer integer : tobeRemove) {
//		dto.getImagePaths().remove(integer.intValue());
//		}
//		 
//		int pos = -1;
//		for (DishesDTO tmp : AppConfig.dummyList) {
//			if(tmp.getId().equals(dto.getId())){
//			pos=	AppConfig.dummyList.indexOf(tmp);
//			}
//		}
//		if(pos >-1){
//			AppConfig.dummyList.set(pos, dto);
//		}
//		else{
//		AppConfig.dummyList.add(dto);
//		}
//		return dto;
//	}
//	
//	
//	@RequestMapping(value = "/fuckJava", method = RequestMethod.POST)
//	@ResponseBody
//    public  String fuckJava(){
//	
//		return "fuck";
//	}	
//	
	
//	@RequestMapping(value = "/createtour", method = RequestMethod.POST)
//	@ResponseBody
//    public  String createTour(@RequestPart("uploadFile") MultipartFile[] file,  @RequestPart("info") CreatePageDto cotent){
//		String id ="";
//		HtmlPageDto currentPageObject = null;
//		String username =(String)request.getAttribute("username");
//		long userId = 0;
//		if(cotent.getId() !=0){
//			for (HtmlPageDto dto : AppConfig.dummyPageList) {
//				if (dto.getId() == cotent.getId() && dto.getUserId()!=userId) {
//					return "fuck you";
//				}else if (dto.getId() == cotent.getId() && dto.getUserId()==(userId)){
//					currentPageObject = dto;
//					break;
//				}
//			}
//		}
//		
//	
//
//		// TODO move code to service
//		// create file and prepare file link for Page dto
//		
//	
//		List<String> imgs = new ArrayList<String>();
//		List<ImageDivDto> imgDivs = new ArrayList<ImageDivDto>();
//		FileOutputStream fos = null;
//		for (MultipartFile multipartFile : file) {
//			String filename = multipartFile.getOriginalFilename();
//			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssssss").format(Calendar.getInstance().getTime());
//			int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
//			File img = new File(
//					"D:/apache-tomcat-8.5.20/webapps/image/" + timeStamp + "_" + randomNum + "_" + filename);
//
//			try {
//
//				fos = new FileOutputStream(img);
//				fos.write(multipartFile.getBytes());
//				img.createNewFile();
//				imgs.add("http://localhost:8080/image/" + timeStamp + "_" + randomNum + "_" + filename);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				return "failed";
//			} finally {
//				try {
//					if (fos != null) {
//						fos.close();
//					}
//				} catch (IOException e) {
//					// Ignore
//				}
//			}
//
//		}
//		
//		
//	
//		//add the added img to each div
//		try{
//		
//			ImageDivDto tmpDiv;
//			List<DivImgNum> imgDivNums = cotent.getImgNumInDiv();
//			int i = 0;
//			int previousInt = 0;
//			for (DivImgNum divImgNum : imgDivNums) {
//				tmpDiv = new ImageDivDto();
//				tmpDiv.setDivID(divImgNum.getDivID());
//				previousInt +=divImgNum.getImgNum();
//				while(i<previousInt){
//					tmpDiv.getImages().add(imgs.get(i));
//					i++;
//				}
//				imgDivs.add(tmpDiv);
//		}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "fail";
//			
//		}
//		
//	
//		
//		if(cotent.getId() == 0){
//		HtmlPageDto pageDto = new HtmlPageDto();
//		pageDto.setId(userId);
//		pageDto.setPageContent(cotent.getContent());
//		pageDto.setDivImg(imgDivs);
//		pageDto.setId(AppConfig.countPage);
//		id =String.valueOf(pageDto.getId());
//		AppConfig.countPage++;
//		AppConfig.dummyPageList.add(pageDto);
//		}else{
//			//delete selected file
//			List<String> removedUrl = cotent.getRemovedImg();
//			for (String string : removedUrl) {
//				File deletdfile = new File("D:/apache-tomcat-8.5.20/webapps/image/" +string.substring(string.lastIndexOf("/") + 1));
//				deletdfile.delete();
//			}
//			
//			//get current page object
//			
//			for (HtmlPageDto dto : AppConfig.dummyPageList) {
//				if(dto.getId() == cotent.getId()){
//				     currentPageObject = dto;
//				     break;
//				}
//			}
//			//set text content
//			currentPageObject.setPageContent(cotent.getContent());
//			
//			
//			Iterator<ImageDivDto> it = currentPageObject.getDivImg().iterator();
//			while (it.hasNext()) {
//				if(cotent.getRemovedDiv().contains(it.next().getDivID())){
//					it.remove();
//				}
//				
//			}
//			
//			
//			Iterator<ImageDivDto> it2 = currentPageObject.getDivImg().iterator();
//			while (it2.hasNext()) {
//				
//				Iterator<String> imgOfDivs =	it2.next().getImages().iterator();
//				while (imgOfDivs.hasNext()) {
//				if(removedUrl.contains(imgOfDivs.next())){
//					imgOfDivs.remove();
//					}
//				}
//			}
//			
//			//remove checked img
//			try{
////			List<String> del = new ArrayList<String>();
////			for (ImageDivDto divDto : currentPageObject.getDivImg()) {
////				for (String img : divDto.getImages()) {
////					for (String removed : removedUrl) {
////						if(removed.equals(img)){
////							del.add(img);
////							 //divDto.getImages().remove(divDto.getImages().indexOf(img));
////						}
////					}
////				}
////				for (String stringDel : del) {
////					divDto.getImages().remove(stringDel);
////				}
////				del = new ArrayList<String>();
////			}
//			
//			//imgDivs = div that we add data taken from frond end
//			ImageDivDto imgDivDtoUpdateFrom = null;
//			ImageDivDto imgDivDtoUpdateTo = null;
//			for (ImageDivDto imgDivDto : imgDivs) {
//				for (ImageDivDto divDtoInOldList : currentPageObject.getDivImg()) {
//						if (imgDivDto.getDivID().equals(divDtoInOldList.getDivID())) {
//							imgDivDtoUpdateFrom = imgDivDto;
//							imgDivDtoUpdateTo = divDtoInOldList;
//							continue;
//						}
//					}
//				//nev div added
//					if (imgDivDtoUpdateFrom == null) {
//						currentPageObject.getDivImg().add(imgDivDto);
//				//old div update
//				} else {
//						imgDivDtoUpdateTo.getImages().addAll(imgDivDtoUpdateFrom.getImages());
//					}
//				
//				
//				 imgDivDtoUpdateFrom = null;
//				 imgDivDtoUpdateTo = null;
//			}
//			} catch (Exception e) {
//				LOGGER.log(Level.FINE, "shit happen", e);
//
//			}
//		}
//		return id;
//	}
//	
//	
//	@RequestMapping(value = "/createtour", method = RequestMethod.POST)
//	@ResponseBody
//    public  String createTour(@RequestPart("uploadFile") MultipartFile[] file,  @RequestPart("info") CreatePageDto cotent){
//		
//		HtmlPageEntity entity= null;
//		String username =(String)request.getAttribute("username");
//		long userId = 0;
//
//		
//	
//		List<String> imgs = new ArrayList<String>();
//		List<ImageDivDto> imgDivs = new ArrayList<ImageDivDto>();
//		FileOutputStream fos = null;
//		for (MultipartFile multipartFile : file) {
//			String filename = multipartFile.getOriginalFilename();
//			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssssss").format(Calendar.getInstance().getTime());
//			int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
//			File img = new File(
//					"D:/apache-tomcat-8.5.20/webapps/image/" + timeStamp + "_" + randomNum + "_" + filename);
//
//			try {
//
//				fos = new FileOutputStream(img);
//				fos.write(multipartFile.getBytes());
//				img.createNewFile();
//				imgs.add("http://localhost:8080/image/" + timeStamp + "_" + randomNum + "_" + filename);
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				return "failed";
//			} finally {
//				try {
//					if (fos != null) {
//						fos.close();
//					}
//				} catch (IOException e) {
//					// Ignore
//				}
//			}
//
//		}
//		
//		
//	
//
//		//add the added img to each div
//		try{
//			List<DivImgNum> imgDivNums = cotent.getImgNumInDiv();
//			entity=	htmlPageService.findByID(cotent.getId());
//			if(entity==null){
//				entity = new HtmlPageEntity();
//			}
//			//entity.getImgDiv().removeIf(x -> str2.contains(x.get)); 
//			entity.getImgDiv().removeIf(p -> cotent.getRemovedDiv().contains(p.getDivID()));
//			entity.getImgDiv().forEach(p -> p.getImages().removeIf(x ->cotent.getRemovedImg().contains(x.getUrl())));
//			
//			int i = 0;
//			int previousInt = 0;
//			for (DivImgNum divImgNum : imgDivNums) {
//				ImageDivEntity tmpDiv =getImgDiv(divImgNum.getDivID(),entity);
//				if(tmpDiv==null){
//					tmpDiv = new ImageDivEntity();
//					tmpDiv.setDivID(divImgNum.getDivID());
//				}
//				previousInt +=divImgNum.getImgNum();
//				while(i<previousInt){
//					ImageEntity img = new ImageEntity();
//					img.setUrl(imgs.get(i));
//					img.setDiv(tmpDiv);
//					tmpDiv.getImages().add(img);
//					i++;
//				}
//				if(!entity.getImgDiv().contains(tmpDiv)){
//					entity.getImgDiv().add(tmpDiv);
//					tmpDiv.setPage(entity);
//				}
//		  
//		}
//			entity.setPageContent(cotent.getContent().getBytes());
//			 if(entity.getId() !=0){
//				   htmlPageService.createOrUpdate(entity, false);
//			   }else{
//				   htmlPageService.createOrUpdate(entity, true);
//			   }
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "fail";
//			
//		}
//		
//	
//		
//
//		return String.valueOf(entity.getId());
//	}	
//
//	
//	
//	
//	@ResponseBody
//	@RequestMapping(value = "/deletedish", method = RequestMethod.POST)
//    public DishesDTO deleteDish(@RequestBody DishesDTO dto){
//	    DishesDTO tmp = null;
//		for (DishesDTO  dtoDish : AppConfig.dummyList) {
//			if(dto.getId().equals(dtoDish.getId())){
//				tmp= dtoDish;
//				break;
//			}
//		}
//		AppConfig.dummyList.remove(tmp);
//		return tmp;
//	}
//	
//	public ITourService getSv() {
//		return sv;
//	}
//
//	public void setSv(ITourService sv) {
//		this.sv = sv;
//	}
//
//
//
//	public IHtmlPageService getHtmlPageService() {
//		return htmlPageService;
//	}
//
//
//
//	public void setHtmlPageService(IHtmlPageService htmlPageService) {
//		this.htmlPageService = htmlPageService;
//	}
//
//	public ImageDivEntity getImgDiv(String divID,HtmlPageEntity entity){
//		if(entity !=null){
//			for (ImageDivEntity element : entity.getImgDiv()) {
//				if(element.getDivID().equals(divID)){
//					return element;
//				}
//			}
//		}
//		ImageDivEntity rs = new ImageDivEntity();
//		rs.setDivID(divID);
//		return rs;
//	}
}