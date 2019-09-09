package pet.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pet.config.core.SpringSecurityInitializer;
import pet.dto.DishesDTO;
import pet.dto.HtmlPageDto;
import pet.service.ITourService;
import pet.service.TourService;

@EnableWebMvc
@Configuration
@ComponentScan({ "pet.*"})
@Import({ SecurityConfig.class })
@PropertySource(value = { "classpath:application.properties" })
@EnableJpaRepositories("pet.dao")
public class AppConfig extends WebMvcConfigurerAdapter{

	public static List<DishesDTO> dummyList = new ArrayList<DishesDTO>();
	public static HtmlPageDto dummyPage = new HtmlPageDto();
	public static List<HtmlPageDto> dummyPageList = new ArrayList<HtmlPageDto>();
	public static int countPage =1;
	
	  public void onStartup(ServletContext servletContext)
	            throws ServletException {
	        System.out.println("Inside application initializer...");

	      
	    }
	  
	  
	  @Bean("fuckThisShit")
	     public ITourService myBean() {
	         // instantiate and configure MyBean obj
	         return new TourService();
	     }
	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	             //   registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
	                registry.addMapping("/**");

	            }
	        };
	    }
	
	  @Bean
	    public CommonsMultipartResolver multipartResolver() {
	        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	        resolver.setMaxUploadSize(1000000);
	        return resolver;
	    }

	
//	@Autowired
//    private Environment environment;
//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/pages/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
	

	
//	@Bean(name = "dataSource")
//	public DriverManagerDataSource dataSource() {
//	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/petdatabase");
//	    driverManagerDataSource.setUsername("root");
//	    driverManagerDataSource.setPassword("123456");
//	    return driverManagerDataSource;
//	}
	
	
//	 @Bean
//	    public DataSource dataSource() {
//	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//	        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//	        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//	        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//	        return dataSource;
//	    }

	
//	   @Bean
//	    public ViewResolver viewResolver() {
//	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	        viewResolver.setViewClass(JstlView.class);
//	        viewResolver.setPrefix("/WEB-INF/pages/");
//	        viewResolver.setSuffix(".jsp");
//	        return viewResolver;
//	    }
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:resources/resources");
	    }
	 
	  @PostConstruct
	 public void postCont(){
//		  DishesDTO dto1 = new DishesDTO();
//	        dto1.setId(new Long(1));
//	        dto1.setName("pho");
//	        dto1.setPrice(new Long(13321));
//	        DishesDTO dto2 = new DishesDTO();
//	        dto2.setId(new Long(2));
//	        dto2.setName("my");
//	        dto2.setPrice(new Long(132));
//	        
//	        DishesDTO dto3 = new DishesDTO();
//	        dto3.setId(new Long(3));
//	        dto3.setName("hu tieu");
//	        dto3.setPrice(new Long(1322));
//	        this.dummyList.add(dto2);
//	        this.dummyList.add(dto1);
//	        this.dummyList.add(dto3);
	 }
	  
	  public static String add(DishesDTO dto){
		  dummyList.add(dto);
		  return "";
	  }
	  
	  
}