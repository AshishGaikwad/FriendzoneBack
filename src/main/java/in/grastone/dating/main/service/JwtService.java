package in.grastone.dating.main.service;

import in.grastone.dating.main.entity.UserEntity;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(String pMobile);

    boolean isTokenValid(String token, UserEntity userDetails);
}