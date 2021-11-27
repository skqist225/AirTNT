package com.airtnt.frontend.host;

import org.springframework.web.multipart.MultipartFile;

public class PhotoDTO {
    private String username;
    private String folderno;
    private MultipartFile[] photos;

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFolderno() {
        return folderno;
    }

    public void setFolderno(String folderno) {
        this.folderno = folderno;
    }

}
