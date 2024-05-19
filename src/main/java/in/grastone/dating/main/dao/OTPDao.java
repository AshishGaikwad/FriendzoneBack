package in.grastone.dating.main.dao;

import in.grastone.dating.main.entity.OTPEntity;

public interface OTPDao {

    public OTPEntity saveOTP(OTPEntity pOTPEntity);
    public OTPEntity getOTP(String mobileNo, String otpKey);


}
