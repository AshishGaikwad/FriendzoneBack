package in.grastone.dating.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginDTO {
	@NotBlank(message = "username should not be blank")
	@Size(min = 6, max = 20, message = "Please enter valid username. It should between 6 to 20 characters")
	private String username;

	@Size(min = 6, max = 20, message = "Please enter valid password & length should between 6 to 20")
	@NotBlank(message = "Password should not be blank")
	private String password;

}
