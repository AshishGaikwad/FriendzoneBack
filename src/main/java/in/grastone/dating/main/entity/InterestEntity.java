package in.grastone.dating.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Interest")
public class InterestEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iId;
	
	private String interest;
}
