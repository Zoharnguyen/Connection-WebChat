package com.example.websocketdemo.constant;

import java.util.ArrayList;
import java.util.List;

public class Constant {

    public List<String> list;

    public List<String> genderList() {
        list = new ArrayList<>();
        list.add("");
        list.add("Male");
        list.add("Female");
        return list;
    }

    public List<String> cityAddressList() {
        list = new ArrayList<>();
        list.add("");
        list.add("Da Nang");
        list.add("Ha Noi");
        list.add("Ho Chi Minh");
        list.add("Nha Trang");
        list.add("Thai Nguyen");
        list.add("Khanh Hoa");
        return list;
    }

    public List<String> filmCategoryFavouriteList() {
        list = new ArrayList<>();
        list.add("");
        list.add("Hanh Dong");
        list.add("Lang Man");
        list.add("Vien Tuong");
        list.add("Phieu Luu");
        return list;
    }

    public List<String> musicCategoryFavouriteList() {
        list = new ArrayList<>();
        list.add("");
        list.add("Pop");
        list.add("Balad");
        list.add("Rap");
        list.add("Rock");
        list.add("EDM");
        return list;
    }

    public List<String> gameCategoryFavouriteList() {
        list = new ArrayList<>();
        list.add("");
        list.add("Hanh Dong");
        list.add("Nhap Vai");
        list.add("Chien Thuat");
        list.add("Dau Tri");
        return list;
    }

    public List<String> sportCategoryFavouriteList() {
        list = new ArrayList<>();
        list.add("");
        list.add("Bong Da");
        list.add("Chay Bo");
        list.add("Cau Long");
        list.add("The Thao Dien Tu");
        return list;
    }

}
