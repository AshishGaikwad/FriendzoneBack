package in.grastone.dating.main.repository;

import in.grastone.dating.main.entity.OTPEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTPEntity,String> {
    OTPEntity findByMobileNoAndOtpKey(String mobileNo,String otpKey);
}
