package in.grastone.dating.main.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class UserRegistrationDTO {
    private String id;

    @NotBlank(message = "First Name should not be blank")
    @Size(min = 2,max = 20,message = "Please enter valid first name. It should between 2 to 20 characters")
    private String firstName;

    @NotBlank(message = "Last Name should not be blank")
    @Size(min = 2,max = 20,message = "Please enter valid last name. It should between 2 to 20 characters")
    private String lastName;

    @NotBlank(message = "username should not be blank")
    @Size(min = 6,max = 20,message = "Please enter valid username. It should between 6 to 20 characters")
    private String username;


    @Size(min = 10,max = 10,message = "Please enter valid mobile & length should be 10")
    @NotBlank(message = "mobile should not be blank")
    private String mobileNo;


    @Size(min = 6,max = 6,message = "Please enter valid username & length should be 6")
    @NotBlank(message = "otp should not be blank")
    private String otp;

    @NotBlank(message = "otp id should not be blank")
    @Size(min = 1,max = 36,message = "Please enter valid OTP id")
    private String otpId;


    @Email
    @Size(min = 5,max = 300,message = "Please enter valid email")
    @NotBlank(message = "email should not be blank")
    private String email;

    @NotNull(message = "DOB should not be blank")
    private LocalDate dob;


    @Size(min = 6,max = 20,message = "Please enter valid password & length should between 6 to 20")
    @NotBlank(message = "Password should not be blank")
    private String password;


    private String confirmPassword;


}
