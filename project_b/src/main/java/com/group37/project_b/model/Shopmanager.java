package com.group37.project_b.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Shopmanager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shopManagerId;
    private String shopManagerPassword;
    private String shopManagerName;
    private String shopManagerEmail;


    
    public Shopmanager(int shopManagerId, String shopManagerPassword, String shopManagerName) {
        this.shopManagerId = shopManagerId;
        this.shopManagerPassword = shopManagerPassword;
        this.shopManagerName = shopManagerName;
    }
    public Shopmanager() {
    }
    public int getShopManagerId() {
        return shopManagerId;
    }
    public void setShopManagerId(int shopManagerId) {
        this.shopManagerId = shopManagerId;
    }
    public String getShopManagerPassword() {
        return shopManagerPassword;
    }
    public void setShopManagerPassword(String shopManagerPassword) {
        this.shopManagerPassword = shopManagerPassword;
    }
    public String getShopManagerName() {
        return shopManagerName;
    }
    public void setShopManagerName(String shopManagerName) {
        this.shopManagerName = shopManagerName;
    }
    public String getShopManagerEmail() {
        return shopManagerEmail;
    }
    public void setShopManagerEmail(String shopManagerEmail) {
        this.shopManagerEmail = shopManagerEmail;
    }
    
    
}
