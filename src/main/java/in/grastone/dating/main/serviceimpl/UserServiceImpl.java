package in.grastone.dating.main.serviceimpl;

import in.grastone.dating.main.entity.UserEntity;
import in.grastone.dating.main.repository.UserRepository;
import in.grastone.dating.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserEntity loadUserByUsername(String pMobile) {
                return userRepository.findByMobile(pMobile)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}