package com.solvd.apiuitest.enums;

public enum ProductPreviewTab {
    WHATSTRENDING("What's Trending"),
    NEWARRIVALS("New Arrivals");

    private ProductPreviewTab(String name){
        this.name = name;
    }

    private String name;

    public String getName(){
        return name;
    }
}
