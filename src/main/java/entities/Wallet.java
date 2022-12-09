package com.blacklion.coiner.entities;

import java.sql.Date;

public class Wallet {
    private Long id;
    private Long userId;
    private double coins;
    private Date lastUpdate;

    public Wallet(Long id, Long userId, double coins, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.coins = coins;
        this.lastUpdate = lastUpdate;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public double getCoins() {
        return coins;
    }
    public void setCoins(double coins) {
        this.coins = coins;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    
}
