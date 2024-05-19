package in.grastone.dating.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.grastone.dating.main.entity.ImagesEntity;

public interface ImageRepository extends JpaRepository<ImagesEntity, String>{
	
	List<ImagesEntity> findByUserId(String userId);

}
