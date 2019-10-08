package com.interview.test.entity;


import javax.persistence.*;

/**
 * @author Chandra Bhushan Verma
 * Entity class for users photos
 */

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "photo_name", nullable = false)
    private String photoName;

    @Column(name = "photo_descriptions", nullable = false)
    private String photoDesc;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "photo_user")
    private String photoUser;

    @Column(name = "rating")
    Long rating;


    @Lob
    @Column(name = "content", nullable = true)
    private byte[] content;

    public Photo() {
    }

    public Photo(String photoName, String contentType, byte[] content, String photoUser) {
        this.photoName = photoName;
        this.contentType = contentType;
        this.content = content;
        this.photoUser = photoUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoDesc() {
        return photoDesc;
    }

    public void setPhotoDesc(String photoDesc) {
        this.photoDesc = photoDesc;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
