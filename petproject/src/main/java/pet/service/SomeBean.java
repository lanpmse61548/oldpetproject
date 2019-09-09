package pet.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SomeBean implements ISomeBean {

 public String getSomeRandomShit(){
	 return "sth";
 }
}
