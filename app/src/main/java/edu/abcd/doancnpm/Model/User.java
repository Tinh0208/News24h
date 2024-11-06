package edu.abcd.doancnpm.Model;

import java.util.List;

public class User {
    private String email;
    private String fullname;
    private String password;
    private List<String> listNewsSaveId;
    private int role;

    public User() {
    }

    public User(String email, String fullname, String password, int role) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.role = role;
    }

    public User( String email, String fullname, String password, List<String> listNewsSaveId, int role) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.listNewsSaveId = listNewsSaveId;
        this.role = role;
    }


    public List<String> getListNewsSaveId() {
        return listNewsSaveId;
    }

    public void setListNewsSaveId(List<String> listNewsSaveId) {
        this.listNewsSaveId = listNewsSaveId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
