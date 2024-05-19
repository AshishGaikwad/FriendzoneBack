package in.grastone.dating.main.service;

import in.grastone.dating.main.dto.UserRegistrationDTO;
import in.grastone.dating.main.entity.UserEntity;

public interface AuthService {
  boolean isUsernameAvailable(String username);
  boolean isEmailAvailable(String email);
  boolean isMobileAvailable(String mobile);
  UserEntity register(UserRegistrationDTO pUserRegistrationDTO); 
  UserEntity fetchUserByName(String username);
}
