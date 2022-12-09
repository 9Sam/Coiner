package com.blacklion.coiner.entities;

import java.sql.Date;

public class User {
    private Long id;
    private Long idUser;
    private String name;
    private String userName;
    private Date joinedDate;

    public User(){}

    public User(Long id, Long idUser, String name, String userName, Date joinedDate) {
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.userName = userName;
        this.joinedDate = joinedDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

}
