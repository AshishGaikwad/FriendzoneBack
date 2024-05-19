package in.grastone.dating.main.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.grastone.dating.main.entity.ImagesEntity;
import in.grastone.dating.main.entity.RespEntity;
import in.grastone.dating.main.service.ImageService;
import in.grastone.dating.main.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("profile")
public class ProfileController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private ImageService imageService;
	
	
	

	

	@PostMapping(value = "image/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		authorization = authorization.replace("Bearer ", "");
		System.out.println("Authorization" + authorization);
		System.out.println(file.getOriginalFilename());

		String userId = jwtService.extractUserName(authorization);

		try {
			ImagesEntity userImage = imageService.save(userId, file.getBytes());

			if (userImage != null) {

				return ResponseEntity.ok().body(new RespEntity(1, "Image uploaded successfully", userImage));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new RespEntity(2, "Failed to upload successfully", null));

	}

	@GetMapping(value = "image/all")
	public ResponseEntity<?> getAllImage(HttpServletRequest request) {

		String authorization = request.getHeader("Authorization");
		authorization = authorization.replace("Bearer ", "");
		System.out.println("Authorization" + authorization);

		String userId = jwtService.extractUserName(authorization);

		List<ImagesEntity> userImage = imageService.getAll(userId);

		if (userImage != null) {

			return ResponseEntity.ok().body(new RespEntity(1, "Image fetch successfully", userImage));
		}

		return ResponseEntity.ok().body(new RespEntity(2, "Failed to fetch", null));

	}

}
