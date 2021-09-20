package com.airtnt.frontend.room;

import java.util.Set;

import com.airtnt.common.entity.Currency;
import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.PriceType;

public class RoomDTO {
    private int id;
    private Set<Image> images;
    private String name;
    private float price;
    private PriceType priceType;
    private String currencySymbol;

    public RoomDTO() {

    }

    public RoomDTO(int id, Set<Image> images, String name, float price, PriceType priceType, String currencySymbol) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.price = price;
        this.priceType = priceType;
        this.currencySymbol = currencySymbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

}
