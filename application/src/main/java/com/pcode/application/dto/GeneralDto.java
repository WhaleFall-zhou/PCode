package com.pcode.application.dto;

import java.util.ArrayList;
import java.util.List;

public class GeneralDto<T> extends PageDto {
    private String itemName;
    private List<T> items=new ArrayList<>();
    private Object item;
    private String retCode;
    private String retMsg;
    private Integer zone;

    public GeneralDto() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "GeneralDto{" +
                "items=" + items +
                ", item=" + item +
                ", retCode='" + retCode + '\'' +
                ", retMsg='" + retMsg + '\'' +
                ", zone=" + zone +
                '}';
    }
}
