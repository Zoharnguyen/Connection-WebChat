package com.example.websocketdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "sport_favourite")
    private String sportCategoryFavourite;

    @Column(name = "film_category_favourite")
    private String filmCategoryFavourite;

    @Column(name = "city_address")
    private String cityAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserInformationBasic userInformationBasic;
    @Column(name = "age")
    private int age;
    @Column(name = "music_category_favourite")
    private String musicCategoryFavourite;
    @Column(name = "game_category_favourite")
    private String gameCategoryFavourite;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public UserInformationBasic getUserInformationBasic() {
        return userInformationBasic;
    }

    public void setUserInformationBasic(UserInformationBasic userInformationBasic) {
        this.userInformationBasic = userInformationBasic;
    }

    public String getSportCategoryFavourite() {
        return sportCategoryFavourite;
    }

    public void setSportCategoryFavourite(String sportCategoryFavourite) {
        this.sportCategoryFavourite = sportCategoryFavourite;
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
}
