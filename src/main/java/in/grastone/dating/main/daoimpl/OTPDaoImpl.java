package in.grastone.dating.main.daoimpl;

import in.grastone.dating.main.dao.OTPDao;
import in.grastone.dating.main.dto.SendOTPDTO;
import in.grastone.dating.main.entity.OTPEntity;
import in.grastone.dating.main.repository.OTPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OTPDaoImpl implements OTPDao {

    @Autowired
    private OTPRepository lOtpRepository;
    
    
    @Override
    public OTPEntity saveOTP(OTPEntity pOTPEntity) {

        try {

            return lOtpRepository.save(pOTPEntity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public OTPEntity getOTP(String mobileNo, String otpKey) {
    	System.out.println("OTP id &"+otpKey);
    	System.out.println("OTP id &"+otpKey.equals("61c7caee-fee7-45f0-a809-a633e6d1c7a2"));
        return lOtpRepository.findByMobileNoAndOtpKey(mobileNo,otpKey);
    }

}
