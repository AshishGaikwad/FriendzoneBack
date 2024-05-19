package in.grastone.dating.main.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.grastone.dating.main.dto.UserRegistrationDTO;
import in.grastone.dating.main.entity.UserEntity;
import in.grastone.dating.main.repository.UserRepository;
import in.grastone.dating.main.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isMobileAvailable(String mobile) {
        return userRepository.existsByMobile(mobile);
    }

	@Override
	public UserEntity register(UserRegistrationDTO pUserRegistrationDTO) {
		
		UserEntity register=new UserEntity();
		register.setUsername(pUserRegistrationDTO.getUsername());
		register.setFirstName(pUserRegistrationDTO.getFirstName());
		register.setLastName(pUserRegistrationDTO.getLastName());
		register.setMobile(pUserRegistrationDTO.getMobileNo());
		register.setEmail(pUserRegistrationDTO.getEmail());
		register.setPassword( passwordEncoder.encode(pUserRegistrationDTO.getPassword()));
		register.setStatus(0);
		return userRepository.save(register);
	}

	@Override
	public UserEntity fetchUserByName(String username) {
		return userRepository.findByUsername(username);
	}
}
