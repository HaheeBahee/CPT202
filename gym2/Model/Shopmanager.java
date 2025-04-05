package com.gym1.gym1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Shopmanager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "shopmanagergene")
    @SequenceGenerator(sequenceName = "shopmanagergene", name="shopmanagergene", allocationSize=1)
    private int shopManagerId;
    private String shopManagerEmail;
    private String shopManagerPassword;
    private String shopManagerName;


    
    public Shopmanager(int shopManagerId, String shopManagerPassword, String shopManagerEmail, String shopManagerName) {
        this.shopManagerId = shopManagerId;
        this.shopManagerPassword = shopManagerPassword;
        this.shopManagerName = shopManagerName;
        this.shopManagerEmail = shopManagerEmail;
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
    public String getShopManagerEmail(){
        return shopManagerEmail;
    }
    public void setShopManagerEmail(String shopManagerEmail){
        this.shopManagerEmail = shopManagerEmail;
    }
    
    
}
