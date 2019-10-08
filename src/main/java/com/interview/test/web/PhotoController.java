package com.interview.test.web;

import com.interview.test.entity.Photo;
import com.interview.test.entity.User;
import com.interview.test.errors.PhotoNotFoundExceptions;
import com.interview.test.repository.PhotoRepository;
import com.interview.test.service.SecurityService;
import com.interview.test.service.UserService;
import com.interview.test.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author  Chandra Bhushan Verma
 * REST Controller class for photos REST endpoints
 */

@RestController
@RequestMapping("photos")
public class PhotoController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @Autowired
    PhotoRepository photoRepository ;

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);


    @PostMapping(path = "/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam ("photo") MultipartFile file) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("Logged in User Details: {}", userDetails.getUsername());

        logger.info("Photo uploadPhoto started ...   ");

        if(!file.getContentType().contains("image")){
            logger.debug("Invalid content type found : {}", file.getContentType());
            throw new IllegalArgumentException("Invalid content type found");
        }
        try{
            Photo img = new Photo( file.getOriginalFilename(),file.getContentType(),file.getBytes() , userDetails.getUsername());
            logger.info("Saving photo details to database: {} ", img.getPhotoName() );
            Photo savedImage = photoRepository.save(img);
            logger.info(" Photo saved successfully returning response code - {}", img.getPhotoName() );
            return new ResponseEntity<>("Successfully uploaded - "
                    + savedImage.getId(), HttpStatus.OK);
        } catch (IOException exception){
            logger.error("Error during photo upload- {} ", exception.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception){
            logger.error("Error during photo upload -{}  ", exception.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(path = "/rate")
    public ResponseEntity<?> ratePhoto(@RequestParam ("rating") Long rate , @RequestParam ("photoId") Long photoId) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("Logged in User Details: {}", userDetails.getUsername());

        logger.info("Photo rating  started ...   ");

        try{
            logger.info("Photo getPhoto started ...   ");
            logger.debug("Retrieving photo with id  : {}", photoId);
            Photo photo = photoRepository.findById(photoId)
                    .orElseThrow(() -> new PhotoNotFoundExceptions("id-"+photoId));

            photo.setRating(rate);
            Photo savedImage = photoRepository.save(photo);
            logger.info(" Photo saved successfully with rating returning response code - {}", photo.getRating() );
            return new ResponseEntity<>("Successfully uploaded - "
                    + savedImage.getId(), HttpStatus.OK);
        } catch (Exception exception){
            logger.error("Error during photo upload -{}  ", exception.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id) throws IOException {

        logger.info("Photo getPhoto started ...   ");
        logger.debug("Retrieving photo with id  : {}", id);
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new PhotoNotFoundExceptions("id-"+id));
        logger.debug("Retrieved  photo with id  : {} , name {} ", id , photo.getPhotoName());
        logger.info("GetPhoto ended...   ");
        return  ResponseEntity.ok().contentType(MediaType.valueOf(photo.getContentType())).body(photo.getContent());
    }

    @GetMapping (produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<?> getAllPhoto() throws IOException {

        logger.info("Photo getPhoto started ...   ");
        List<Photo> photos = photoRepository.findAll();
        return photos.stream().map(photo->photo.getId()).collect(Collectors.toList());
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhoto(@PathVariable Long id) throws IOException {
       logger.info("Delete photo started ...   ");
       logger.debug("Deleting photo with photo id   : {}", id);
       //TODO : Need to add exception
       photoRepository.deleteById(id);
       logger.debug("Deleted photo with id  : {} ", id );
       logger.info("Delete photo end ...   ");
        return new ResponseEntity<>("Successfully Deleted  - "
                + id, HttpStatus.OK);
    }

}
