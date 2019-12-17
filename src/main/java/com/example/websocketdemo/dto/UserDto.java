package com.example.websocketdemo.dto;

import com.example.websocketdemo.entity.PersonDesire;
import com.example.websocketdemo.model.UploadForm;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class UserDto {

    UUID idUser;
    String userName;
    String password;
    String imageProfile;
    String imageBackground;
    String fullName;
    String gender;
    LocalDate dateOfBirth;
    String sportCategoryFavourite;
    String filmCategoryFavourite;
    String cityAddress;
    int age;
    String musicCategoryFavourite;
    String gameCategoryFavourite;
    List<PersonDesire> personDesires;
    UploadForm uploadFormProfile;
    UploadForm uploadFormBackgroud;
    String urlProfile;
    String urlBackgroud;
    MultipartFile[] files;

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public UploadForm getUploadFormProfile() {
        return uploadFormProfile;
    }

    public void setUploadFormProfile(UploadForm uploadFormProfile) {
        this.uploadFormProfile = uploadFormProfile;
    }

    public UploadForm getUploadFormBackgroud() {
        return uploadFormBackgroud;
    }

    public void setUploadFormBackgroud(UploadForm uploadFormBackgroud) {
        this.uploadFormBackgroud = uploadFormBackgroud;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getUrlBackgroud() {
        return urlBackgroud;
    }

    public void setUrlBackgroud(String urlBackgroud) {
        this.urlBackgroud = urlBackgroud;
    }

    public UserDto(String gender) {
        this.gender = gender;
    }

    public UserDto() {
    }

    public List<PersonDesire> getPersonDesires() {
        return personDesires;
    }

    public void setPersonDesires(List<PersonDesire> personDesires) {
        this.personDesires = personDesires;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getSportCategoryFavourite() {
        return sportCategoryFavourite;
    }

    public void setSportCategoryFavourite(String sportCategoryFavourite) {
        this.sportCategoryFavourite = sportCategoryFavourite;
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
}
