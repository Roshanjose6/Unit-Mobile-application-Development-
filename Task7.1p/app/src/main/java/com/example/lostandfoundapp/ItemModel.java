package com.example.lostandfoundapp;

public class ItemModel {
    private String itemid;
    private String itemname;
    private String itemdescription;
    private String itemlocation;
    private String itemdate;
    private String itemType;
    private String itemphone;
    public ItemModel(String itemid, String itemname, String itemdescription, String itemlocation, String itemdate, String itemType, String itemphone) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemdescription = itemdescription;
        this.itemlocation = itemlocation;
        this.itemdate = itemdate;
        this.itemType = itemType;
        this.itemphone = itemphone;
    }
    public String getItemid() {
        return itemid;
    }


    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getItemlocation() {
        return itemlocation;
    }

    public void setItemlocation(String itemlocation) {
        this.itemlocation = itemlocation;
    }

    public String getItemdate() {
        return itemdate;
    }

    public void setItemdate(String itemdate) {
        this.itemdate = itemdate;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemphone() {
        return itemphone;
    }

    public void setItemphone(String itemphone) {
        this.itemphone = itemphone;
    }
}
