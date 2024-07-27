package in.grastone.dating.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Photos")
public class PhotosEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long photoId;
	
	private String url;

}
