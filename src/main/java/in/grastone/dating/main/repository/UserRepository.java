package in.grastone.dating.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.grastone.dating.main.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByMobile(String mobileNo);
    boolean existsByUsername(String username);
    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);
    UserEntity findByUsername(String username);
}
