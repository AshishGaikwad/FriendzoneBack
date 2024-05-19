package in.grastone.dating.main.service;

import java.util.List;

import in.grastone.dating.main.entity.ImagesEntity;

public interface ImageService {
	public ImagesEntity save(String userId,byte[] image);
	
	public List<ImagesEntity> getAll(String userId);
}
