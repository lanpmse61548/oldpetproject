package pet.dao;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value="prototype")
public class GenericJpaDao< T extends Serializable >
 extends AbstractJpaDao< T > implements IGenericDao< T >{
   //
}
