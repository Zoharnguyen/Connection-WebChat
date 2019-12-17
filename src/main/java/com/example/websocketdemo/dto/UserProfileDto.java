package com.example.websocketdemo.dto;

public class UserProfileDto {

    private String fullName;
    private String gender;
    private String sportCategoryFavourite;
    private String filmCategoryFavourite;
    private String cityAddress;
    private int age;
    private String musicCategoryFavourite;
    private String gameCategoryFavourite;
    private int point;
    private String imageProfile;

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSportCategoryFavourite() {
        return sportCategoryFavourite;
    }

    public void setSportCategoryFavourite(String sportCategoryFavourite) {
        this.sportCategoryFavourite = sportCategoryFavourite;
    }

    public String getFilmCategoryFavourite() {
        return filmCategoryFavourite;
    }

    public void setFilmCategoryFavourite(String filmCategoryFavourite) {
        this.filmCategoryFavourite = filmCategoryFavourite;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMusicCategoryFavourite() {
        return musicCategoryFavourite;
    }

    public void setMusicCategoryFavourite(String musicCategoryFavourite) {
        this.musicCategoryFavourite = musicCategoryFavourite;
    }

    public String getGameCategoryFavourite() {
        return gameCategoryFavourite;
    }

    public void setGameCategoryFavourite(String gameCategoryFavourite) {
        this.gameCategoryFavourite = gameCategoryFavourite;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
