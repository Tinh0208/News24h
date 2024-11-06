package edu.abcd.doancnpm.Model;

import java.io.Serializable;

public class Approved implements Serializable {
    private String title;
    private String content;
    private String image;
    private String category;
    private String id;
    private boolean isApproved;
    public Approved() {

    }

    public Approved(String title, String content, String image, String category, String id, boolean isApproved) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.category = category;
        this.id = id;
        this.isApproved = isApproved;
    }



    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
