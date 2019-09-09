package pet.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pet.dao.IGenericDao;
import pet.dom.HtmlPageEntity;
import pet.dom.ImageDivEntity;
import pet.dom.ImageEntity;
import pet.dom.UserEntity;
import pet.dto.CreatePageDto;
import pet.dto.DivImgNum;

@Service
@Transactional(rollbackOn = Throwable.class)
public class HtmlPageServiceImpl implements IHtmlPageService {
	// @PersistenceContext
	// private EntityManager em;
	//
	// @PersistenceContext
	// EntityManagerFactory entityManagerFactory;

	@Autowired
	SessionFactory sessionFactory;

	IGenericDao<HtmlPageEntity> dao;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	@Qualifier("hibernateDao")
	public void setDao(IGenericDao<HtmlPageEntity> daoToSet) {
		dao = daoToSet;
		dao.setClazz(HtmlPageEntity.class);
	}

	@Override
	public List<HtmlPageEntity> searchAll() {
		// JPAQuery tmp = new JPAQuery(em);
		// return
		// tmp.from(QHtmlPageEntity.htmlPageEntity).list(QHtmlPageEntity.htmlPageEntity);
		List<HtmlPageEntity> entities = dao.findAll();
		for (HtmlPageEntity entity : entities) {
			if (entity != null) {
				entity.getImgDiv().size();
				for (ImageDivEntity element : entity.getImgDiv()) {
					element.getImages().size();
				}
			}
		}
		return entities;

	}

	@Override
	public List<HtmlPageEntity> findByLimitAll(int first, int last) {
		List<HtmlPageEntity> entities = dao.findByLimitAll(first, last);
		for (HtmlPageEntity entity : entities) {
			if (entity != null) {
				entity.getImgDiv().size();
				for (ImageDivEntity element : entity.getImgDiv()) {
					element.getImages().size();
				}
			}
		}
		return entities;
	}

	@Override
	public HtmlPageEntity createOrUpdate(HtmlPageEntity entity, boolean isNew) {
		if (isNew) {
			// em.persist(entity);

			dao.save(entity);
		} else {
			// em.merge(entity);
			dao.update(entity);
		}
		return entity;
	}

	@Override
	public HtmlPageEntity findByID(long id) {
		HtmlPageEntity entity = dao.findOne(id);// em.find(HtmlPageEntity.class,
												// id);
		// String username = (String) request.getAttribute("username");
		// HtmlPageEntity entity = findByIdAndUsername(id, username);
		if (entity != null) {
			Hibernate.initialize(entity.getImgDiv());

			for (ImageDivEntity element : entity.getImgDiv()) {
				Hibernate.initialize(element.getImages().size());
			}

		}
		return entity;
	}

	public HtmlPageEntity findByIdAndUsername(Long id, String Username) {
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<HtmlPageEntity> query = builder.createQuery(HtmlPageEntity.class);
		Root<HtmlPageEntity> root = query.from(HtmlPageEntity.class);
		Join<HtmlPageEntity, UserEntity> joinUser = root.join("user");
		query = query.select(root).where(builder.equal(root.get("id"), id),
				builder.equal(joinUser.get("username"), Username));

		HtmlPageEntity entity = sessionFactory.getCurrentSession().createQuery(query).getSingleResult();
		if (entity != null) {
			Hibernate.initialize(entity.getImgDiv());

			for (ImageDivEntity element : entity.getImgDiv()) {
				Hibernate.initialize(element.getImages().size());
			}
		}
		return entity;

		// Criteria a=
		// sessionFactory.getCurrentSession().createCriteria(HtmlPageEntity.class);
		// a.createAlias(associationPath, alias)

	}

	public List<HtmlPageEntity> findByUsername(String Username) {
		CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<HtmlPageEntity> query = builder.createQuery(HtmlPageEntity.class);
		Root<HtmlPageEntity> root = query.from(HtmlPageEntity.class);
		Join<HtmlPageEntity, UserEntity> joinUser = root.join("user");
		query = query.select(root).where(builder.equal(joinUser.get("username"), Username));

		List<HtmlPageEntity> entities = sessionFactory.getCurrentSession().createQuery(query).getResultList();
		for (HtmlPageEntity entity : entities) {
			if (entity != null) {
				entity.getImgDiv().size();
				for (ImageDivEntity element : entity.getImgDiv()) {
					element.getImages().size();
				}
			}
		}
		return entities;

	}

	public HtmlPageEntity createOrUpdate(MultipartFile[] file, CreatePageDto content) {
		HtmlPageEntity entity = null;
		String username = (String) request.getAttribute("username");

		List<String> imgs = new ArrayList<String>();
		FileOutputStream fos = null;
		for (MultipartFile multipartFile : file) {
			String filename = multipartFile.getOriginalFilename();
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssssss").format(Calendar.getInstance().getTime());
			int randomNum = ThreadLocalRandom.current().nextInt(1, 10000);
			File img = new File(
					"D:/apache-tomcat-8.5.20/webapps/image/" + timeStamp + "_" + randomNum + "_" + filename);

			try {

				fos = new FileOutputStream(img);
				fos.write(multipartFile.getBytes());
				img.createNewFile();
				imgs.add("http://localhost:8080/image/" + timeStamp + "_" + randomNum + "_" + filename);

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					// Ignore
				}
			}

		}

		// add the added img to each div
		try {
			List<DivImgNum> imgDivNums = content.getImgNumInDiv();
			try {
				entity = findByIdAndUsername(content.getId(), username);
			} catch (NoResultException e) {
				entity = null;
			}

			if (entity == null) {
				entity = new HtmlPageEntity();
				entity.setUser(iUserService.findByUsernamer(username));
			}
			// entity.getImgDiv().removeIf(x -> str2.contains(x.get));
			entity.getImgDiv().removeIf(p -> content.getRemovedDiv().contains(p.getDivID()));
			entity.getImgDiv().forEach(p -> p.getImages().removeIf(x -> content.getRemovedImg().contains(x.getUrl())));

			int i = 0;
			int previousInt = 0;
			for (DivImgNum divImgNum : imgDivNums) {
				ImageDivEntity tmpDiv = getImgDiv(divImgNum.getDivID(), entity);
				if (tmpDiv == null) {
					tmpDiv = new ImageDivEntity();
					tmpDiv.setDivID(divImgNum.getDivID());
				}
				previousInt += divImgNum.getImgNum();
				while (i < previousInt) {
					ImageEntity img = new ImageEntity();
					img.setUrl(imgs.get(i));
					img.setDiv(tmpDiv);
					tmpDiv.getImages().add(img);
					i++;
				}
				if (!entity.getImgDiv().contains(tmpDiv)) {
					entity.getImgDiv().add(tmpDiv);
					tmpDiv.setPage(entity);
				}

			}

			entity.setPageContent(content.getContent().getBytes());
			entity.setCost(content.getMetadata().getCost());
			entity.setChildCost(content.getMetadata().getChildCost());
			entity.setDiscountPercent(content.getMetadata().getDiscountPercent());
			Date start = new SimpleDateFormat("dd.MM.yyyy").parse(content.getMetadata().getStartDate());
			entity.setStartDate(start);

			Date end = new SimpleDateFormat("dd.MM.yyyy").parse(content.getMetadata().getEndDate());
			entity.setEndDate(end);
			entity.setCreatedDate(new Date());
			entity.setTitle(content.getMetadata().getTitle());

			entity.setLocation(content.getMetadata().getLocation());
			entity.setType(content.getMetadata().getType());

			if (entity.getId() != 0) {
				createOrUpdate(entity, false);
			} else {
				createOrUpdate(entity, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
		return entity;
	}

	public ImageDivEntity getImgDiv(String divID, HtmlPageEntity entity) {
		if (entity != null) {
			for (ImageDivEntity element : entity.getImgDiv()) {
				if (element.getDivID().equals(divID)) {
					return element;
				}
			}
		}
		ImageDivEntity rs = new ImageDivEntity();
		rs.setDivID(divID);
		return rs;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public IUserService getiUserService() {
		return iUserService;
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IGenericDao<HtmlPageEntity> getDao() {
		return dao;
	}

}
