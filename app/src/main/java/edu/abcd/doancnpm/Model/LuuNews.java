package edu.abcd.doancnpm.Model;

import java.io.Serializable;

public class LuuNews implements Serializable {
    private String title;
    private String content,content1;
    private String image,image1;
    private String date;
    private String category;
    private String id;
    private User author;
    private boolean isApproved;
    String documentId;

    public LuuNews() {

    }

    public LuuNews(String title, String content, String image, String image1,String content1,String date, String category, String id, User author, boolean isApproved) {
        this.title = title;
        this.content1 =content1;
        this.image1 = image1;
        this.content = content;
        this.image = image;
        this.date = date;
        this.category = category;
        this.id = id;
        this.author = author;
        this.isApproved = isApproved;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
    public String getImage1(){
        return  image1;
    }
    public String getContent1(){
        return content1;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
