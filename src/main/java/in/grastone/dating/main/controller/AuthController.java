package in.grastone.dating.main.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.grastone.dating.main.dao.OTPDao;
import in.grastone.dating.main.dto.SendOTPDTO;
import in.grastone.dating.main.dto.UserLoginDTO;
import in.grastone.dating.main.dto.UserLoginResponseDTO;
import in.grastone.dating.main.dto.UserRegistrationDTO;
import in.grastone.dating.main.entity.OTPEntity;
import in.grastone.dating.main.entity.RespEntity;
import in.grastone.dating.main.entity.UserEntity;
import in.grastone.dating.main.service.AuthService;
import in.grastone.dating.main.service.JwtService;
import in.grastone.dating.main.utility.Util;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

	private Util lUtil;
	private OTPDao lOTPDao;
	private AuthService authService;
	private PasswordEncoder passwordEncoder;	
	private JwtService jwtService;
	
	
	
	
	public AuthController(Util lUtil, OTPDao lOTPDao, AuthService authService, PasswordEncoder passwordEncoder,
			JwtService jwtService) {
		super();
		this.lUtil = lUtil;
		this.lOTPDao = lOTPDao;
		this.authService = authService;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@GetMapping(value="available/user/{username}")
	public ResponseEntity<?> checkUserNameAvailibility(@PathVariable("username") String pUserName){
		
		if (!authService.isUsernameAvailable(pUserName)) {
			return ResponseEntity.ok().body(new RespEntity(1, "Username is available",null));
		}else {
			return ResponseEntity.ok().body(new RespEntity(2, "Username is already used",null));
		}
		
	}

	@PostMapping(value = "sendotp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendOTP(@RequestBody SendOTPDTO pSendOTP) {

		OTPEntity reqEntity = new OTPEntity();
		reqEntity.setMobileNo(pSendOTP.getMobileNo());
		reqEntity.setOtpKey(UUID.randomUUID().toString());
		reqEntity.setOtp(lUtil.generateOTP());
		reqEntity.setStatus("N");
		reqEntity = lOTPDao.saveOTP(reqEntity);

		if (reqEntity != null) {
			pSendOTP.setOtpId(reqEntity.getOtpKey());
			return ResponseEntity.ok().body(new RespEntity(1, "OTP sent successfully", pSendOTP));
		}
		return ResponseEntity.ok().body(new RespEntity(2, "Failed to send OTP", null));
	}
    @PostMapping(value = "verifyotp",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> verifyOTP(@RequestBody SendOTPDTO pSendOTPDTO){

        OTPEntity otpEntity = lOTPDao.getOTP(pSendOTPDTO.getMobileNo(),pSendOTPDTO.getOtpId());
        if(otpEntity != null){
            if(otpEntity.getOtp().equals(pSendOTPDTO.getOtp()) && otpEntity.getStatus().equals("N")){

                if(System.currentTimeMillis()>otpEntity.getTimestamp().getTime()+900000){
                    return ResponseEntity.ok().body(new RespEntity(4,"OTP has expired. Please try again",null));
                }

                String token = jwtService.generateToken(pSendOTPDTO.getMobileNo());


                otpEntity.setStatus("Y");
                otpEntity = lOTPDao.saveOTP(otpEntity);
                return ResponseEntity.ok().body(new RespEntity(1,"OTP verified successfully",token));
            }else{
                return ResponseEntity.ok().body(new RespEntity(3,"Please enter valid OTP",null));
            }
        }
        return ResponseEntity.ok().body(new RespEntity(2,"Failed to verify OTP",null));

    }

	@PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDTO pUserRegistrationDTO) {

		// check if username is exist
		if (authService.isUsernameAvailable(pUserRegistrationDTO.getUsername())) {
			return ResponseEntity.ok().body(new RespEntity(101, "Username is already exist", ""));
		}
		// check if mobileno is exist or not
		if (authService.isMobileAvailable(pUserRegistrationDTO.getMobileNo())) {
			return ResponseEntity.ok().body(new RespEntity(102, "Mobile is already exist", ""));
		}

		// check email is exist or not
		if (authService.isEmailAvailable(pUserRegistrationDTO.getEmail())) {
			return ResponseEntity.ok().body(new RespEntity(103, "Email is already exist", ""));
		}

		// confirm password strength
		
		if(pUserRegistrationDTO.getPassword().equalsIgnoreCase(pUserRegistrationDTO.getConfirmPassword())) {
			return ResponseEntity.ok().body(new RespEntity(104, "Password & confirm password should be same.", ""));
		}

		System.out.println("pUserRegistrationDTO.getOtpId()"+pUserRegistrationDTO.getOtpId());
		// verify otp
		OTPEntity otpEntity = lOTPDao.getOTP(pUserRegistrationDTO.getMobileNo(), pUserRegistrationDTO.getOtpId().trim());
		if (otpEntity != null) {
			if (otpEntity.getOtp().equals(pUserRegistrationDTO.getOtp()) && otpEntity.getStatus().equals("N")) {

				if (System.currentTimeMillis() > otpEntity.getTimestamp().getTime() + 900000) {
					return ResponseEntity.ok().body(new RespEntity(105, "OTP has expired. Please try again", null));
				}
				otpEntity.setStatus("Y");
				otpEntity = lOTPDao.saveOTP(otpEntity);

			} else {
				return ResponseEntity.ok().body(new RespEntity(106, "Please enter valid OTP", null));
			}
		}else {
			return ResponseEntity.ok().body(new RespEntity(108, "Invalid OTP", null));
		}
		
		UserEntity save = authService.register(pUserRegistrationDTO);
		
		if(save != null) {
			save.setPassword(null);
			return ResponseEntity.ok().body(new RespEntity(100, "User registered successfully", save));
		}
		return ResponseEntity.ok().body(new RespEntity(107, "Failed to register user", null));
	}

	@PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO pUserLoginDTO) {
		
		UserEntity userEntity = authService.fetchUserByName(pUserLoginDTO.getUsername());
		
		
		boolean isPassMatched = passwordEncoder.matches(pUserLoginDTO.getPassword(), userEntity.getPassword());
		
		if(isPassMatched) {
			
			String token = jwtService.generateToken(userEntity.getId());
			
			return ResponseEntity.ok().body(new RespEntity(100, "User authentication successful", new UserLoginResponseDTO(pUserLoginDTO.getUsername(),token)));
		}
		
		
		
		return ResponseEntity.ok().body(new RespEntity(101, "Failed to authenticate user", null));
		
	}
	
	
}
