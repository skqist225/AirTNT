package com.airtnt.frontend.host;

import org.springframework.web.multipart.MultipartFile;

public class PhotoDTO {
    private String userName;
    private MultipartFile[] photos;

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
