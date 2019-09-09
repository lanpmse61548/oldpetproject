package pet.service;

import pet.dom.UserEntity;

public interface IUserService {
	public UserEntity findByID(long id);
	public UserEntity findByUsernamer(String username);
}
