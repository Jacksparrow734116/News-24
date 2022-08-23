package com.example.news24;

public class Ctegory {
    String imageId;
    String category;

    public Ctegory(String imageId, String category) {
        this.imageId = imageId;
        this.category = category;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
