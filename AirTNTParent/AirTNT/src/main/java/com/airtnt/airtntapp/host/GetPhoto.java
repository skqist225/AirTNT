package com.airtnt.airtntapp.host;

class GetPhoto {
    private String username;
    private String folderno;
    private String[] roomImages;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(String[] roomImages) {
        this.roomImages = roomImages;
    }

    public String getFolderno() {
        return folderno;
    }

    public void setFolderno(String folderno) {
        this.folderno = folderno;
    }
}