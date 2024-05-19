package in.grastone.dating.main.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.grastone.dating.main.entity.ImagesEntity;
import in.grastone.dating.main.repository.ImageRepository;
import in.grastone.dating.main.service.ImageService;
import jakarta.transaction.Transactional;


@Service
public class ImageDBService implements ImageService{

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	@Transactional
	public ImagesEntity save(String userId,byte[] image) {
		// TODO Auto-generated method stub
		ImagesEntity imagesEntity = new ImagesEntity();
		
		imagesEntity.setImage(image);
		imagesEntity.setUserId(userId);
		imagesEntity.setStatus(true);
				
		return imageRepository.save(imagesEntity);
	}

	@Override
	@Transactional
	public List<ImagesEntity> getAll(String userId) {
		// TODO Auto-generated method stub
		return imageRepository.findByUserId(userId);
	}

	

}
