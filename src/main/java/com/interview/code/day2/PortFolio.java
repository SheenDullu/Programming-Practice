package com.interview.code.day2;

import java.util.List;

public class PortFolio {

    private int id;
    private Stocks stock;
    private List<Holdings> holdingsList;

    public int getId() {
        return id;
    }

    public List<Holdings> getHoldingsList() {
        return holdingsList;
    }

    public void setHoldingsList(List<Holdings> holdingsList) {
        this.holdingsList = holdingsList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }
}
