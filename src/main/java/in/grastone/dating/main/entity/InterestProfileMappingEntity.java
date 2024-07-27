package in.grastone.dating.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name="INTEREST_PROFILE_MAPPING")
@Table
public class InterestProfileMappingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ipId;
	
	
	@OneToOne
	@JoinColumn(name="interest_id",referencedColumnName = "iId")
	private InterestEntity interestId;
	
	@ManyToOne
	@JoinColumn(name="profile_id")
	private ProfileEntity profile;
}
