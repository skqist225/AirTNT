package com.airtnt.frontend.room;

import java.util.Arrays;

public class RoomPostDTO {
    private String name;
    private String[] images;
    private int[] amentities;
    private int country;
    private String state;
    private String city;
    private int bedroomCount;
    private int bathroomCount;
    private int accomodatesCount;
    private int bedCount;
    private int currency;
    private int category;
    private int roomGroup;
    private int roomType;
    private String description;
    private float latitude;
    private float longitude;
    private int price;
    private String priceType;
    private int minimumStay;
    private String stayType;
    private int host;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public int getBathroomCount() {
        return bathroomCount;
    }

    public void setBathroomCount(int bathroomCount) {
        this.bathroomCount = bathroomCount;
    }

    public int getAccomodatesCount() {
        return accomodatesCount;
    }

    public void setAccomodatesCount(int accomodatesCount) {
        this.accomodatesCount = accomodatesCount;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getRoomGroup() {
        return roomGroup;
    }

    public void setRoomGroup(int roomGroup) {
        this.roomGroup = roomGroup;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(int minimumStay) {
        this.minimumStay = minimumStay;
    }

    public String getStayType() {
        return stayType;
    }

    public void setStayType(String stayType) {
        this.stayType = stayType;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public int[] getAmentities() {
        return amentities;
    }

    public void setAmentities(int[] amentities) {
        this.amentities = amentities;
    }

    @Override
    public String toString() {
        return "RoomPostDTO [accomodatesCount=" + accomodatesCount + ", bathroomCount=" + bathroomCount + ", bedCount="
                + bedCount + ", bedroomCount=" + bedroomCount + ", category=" + category + ", city=" + city
                + ", country=" + country + ", currency=" + currency + ", description=" + description + ", host=" + host
                + ", images=" + Arrays.toString(images) + ", latitude=" + latitude + ", longitude=" + longitude
                + ", minimumStay=" + minimumStay + ", name=" + name + ", price=" + price + ", priceType=" + priceType
                + ", roomGroup=" + roomGroup + ", roomType=" + roomType + ", state=" + state + ", stayType=" + stayType
                + "]";
    }

}
