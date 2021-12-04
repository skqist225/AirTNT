package com.airtnt.airtntapp.room;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private int privacyType;
    private String street;

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
