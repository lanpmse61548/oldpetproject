package pet.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pet.dom.HtmlPageEntity;
import pet.dto.CreatePageDto;

public interface IHtmlPageService {
	List<HtmlPageEntity> searchAll();

	HtmlPageEntity createOrUpdate(HtmlPageEntity entity, boolean isNew);

	HtmlPageEntity findByID(long id);

	HtmlPageEntity findByIdAndUsername(Long id, String Username);

	HtmlPageEntity createOrUpdate(MultipartFile[] file, CreatePageDto cotent);

	List<HtmlPageEntity> findByLimitAll(int first, int last);
	
	List<HtmlPageEntity> findByUsername(String username);
}
