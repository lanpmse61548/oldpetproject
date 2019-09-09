package pet.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pet.dom.HtmlPageEntity;
import pet.dom.ImageDivEntity;
import pet.dom.ImageEntity;
import pet.dto.CreatePageDto;
import pet.dto.HtmlPageDto;
import pet.dto.ImageDivDto;
import pet.dto.TourListDto;
import pet.service.IHtmlPageService;

@Controller
@CrossOrigin(origins = "*")
public class PageController {
	@Autowired
	private IHtmlPageService htmlPageService;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/getNewTour", method = RequestMethod.POST)
	public @ResponseBody HtmlPageDto getHtmPage() {
		HtmlPageDto dto = new HtmlPageDto();
		dto.setId(0);
		return dto;
	}

	@RequestMapping(value = "/getTour/{id}", method = RequestMethod.POST)
	public @ResponseBody HtmlPageDto getHtmPage(@PathVariable("id") long id) {

		HtmlPageEntity entity = htmlPageService.findByID(id);
		return mapDto(entity);
	}

	private HtmlPageDto mapDto(HtmlPageEntity entity) {
		HtmlPageDto dto = new HtmlPageDto();
		if (entity != null) {

			for (ImageDivEntity element : entity.getImgDiv()) {
				ImageDivDto div = new ImageDivDto();
				div.setDivID(element.getDivID());
				for (ImageEntity img : element.getImages()) {
					div.getImages().add(img.getUrl());
				}
				dto.getDivImg().add(div);
			}
			dto.setPageContent(new String(entity.getPageContent()));
			// dto.setUserId(entity.getUserId());
			dto.setId(entity.getId());

			dto.getMetaData().setCost(entity.getCost());
			dto.getMetaData().setChildCost(entity.getChildCost());
			dto.getMetaData().setDiscountPercent(entity.getDiscountPercent());

			DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

			dto.getMetaData().setStartDate(dateFormat.format(entity.getStartDate()));

			dto.getMetaData().setEndDate(dateFormat.format(entity.getEndDate()));
			dto.getMetaData().setCreatedDate(dateFormat.format(entity.getCreatedDate()));

			dto.getMetaData().setTitle(entity.getTitle());

			dto.getMetaData().setLocation(entity.getLocation());
			dto.getMetaData().setType(entity.getType());
		}

		return dto;
	}

	@RequestMapping(value = "/getTours", method = RequestMethod.GET)
	public @ResponseBody List<TourListDto> getTours() {

		List<TourListDto> tourList = new ArrayList<TourListDto>();

		List<HtmlPageEntity> dummyPageList = htmlPageService.searchAll();
		for (HtmlPageEntity entity : dummyPageList) {
			HtmlPageDto htmlPageDto = mapDto(entity);
			TourListDto tmp = new TourListDto();
			if (!htmlPageDto.getDivImg().isEmpty()) {
				tmp.setUrl(htmlPageDto.getDivImg().get(0).getImages().get(0));
			}
			tmp.setTitle(entity.getTitle());
			tmp.setId(entity.getId());
			tourList.add(tmp);
		}

		return tourList;
	}

	@RequestMapping(value = "/getToursWithLimit/{first}/{last}", method = RequestMethod.GET)
	public @ResponseBody List<TourListDto> getToursWithLimit(@PathVariable("first") int first,
			@PathVariable("last") int last) {

		List<TourListDto> tourList = new ArrayList<TourListDto>();

		List<HtmlPageEntity> dummyPageList = htmlPageService.findByLimitAll(first, last);

		for (HtmlPageEntity entity : dummyPageList) {
			HtmlPageDto htmlPageDto = mapDto(entity);
			TourListDto tmp = new TourListDto();
			if (!htmlPageDto.getDivImg().isEmpty()) {
				tmp.setUrl(htmlPageDto.getDivImg().get(0).getImages().get(0));
			}
			tmp.setTitle(entity.getTitle());
			tmp.setId(entity.getId());
			tourList.add(tmp);
		}

		return tourList;
	}

	@RequestMapping(value = "/getTourWithUser/{userName}", method = RequestMethod.GET)
	public @ResponseBody List<TourListDto> getTourWithUser(@PathVariable("userName") String userName) {

		List<TourListDto> tourList = new ArrayList<TourListDto>();

		List<HtmlPageEntity> dummyPageList = htmlPageService.findByUsername(userName);
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		for (HtmlPageEntity entity : dummyPageList) {
			HtmlPageDto htmlPageDto = mapDto(entity);
			TourListDto tmp = new TourListDto();
			if (!htmlPageDto.getDivImg().isEmpty()) {
				tmp.setUrl(htmlPageDto.getDivImg().get(0).getImages().get(0));
			}
			tmp.setTitle(entity.getTitle());
			tmp.setId(entity.getId());
			tmp.setCreatedDate(dateFormat.format(entity.getCreatedDate()));
			tourList.add(tmp);
		}

		return tourList;
	}

	@RequestMapping(value = "/createtour", method = RequestMethod.POST)
	@ResponseBody
	public String createTour(@RequestPart("uploadFile") MultipartFile[] file,
			@RequestPart("info") CreatePageDto cotent) {

		HtmlPageEntity entity = htmlPageService.createOrUpdate(file, cotent);

		if (entity == null) {
			return "fail";
		}

		return String.valueOf(entity.getId());
	}

	public IHtmlPageService getHtmlPageService() {
		return htmlPageService;
	}

	public void setHtmlPageService(IHtmlPageService htmlPageService) {
		this.htmlPageService = htmlPageService;
	}

}
