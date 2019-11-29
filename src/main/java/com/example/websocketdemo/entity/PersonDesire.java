package com.example.websocketdemo.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "person_desire")
@NoArgsConstructor
public class PersonDesire {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "gender")
    private String gender;

    @Column
    private int age;

    @Column
    private int spaceAge;

    @Column
    private String cityAddress;

    @Column
    private String sportFavourite;

    @Column
    private String filmCategoryFavourite;

    @Column
    private String gameCategoryFavourite;

    @Column
    private String musicCategoryFavourite;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserInformationBasic userInformationBasic;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSpaceAge() {
        return spaceAge;
    }

    public void setSpaceAge(int spaceAge) {
        this.spaceAge = spaceAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getSportFavourite() {
        return sportFavourite;
    }

    public void setSportFavourite(String sportFavourite) {
        this.sportFavourite = sportFavourite;
    }

    public String getFilmCategoryFavourite() {
        return filmCategoryFavourite;
    }

    public void setFilmCategoryFavourite(String filmCategoryFavourite) {
        this.filmCategoryFavourite = filmCategoryFavourite;
    }

    public String getGameCategoryFavourite() {
        return gameCategoryFavourite;
    }

    public void setGameCategoryFavourite(String gameCategoryFavourite) {
        this.gameCategoryFavourite = gameCategoryFavourite;
    }

    public String getMusicCategoryFavourite() {
        return musicCategoryFavourite;
    }

    public void setMusicCategoryFavourite(String musicCategoryFavourite) {
        this.musicCategoryFavourite = musicCategoryFavourite;
    }

    public UserInformationBasic getUserInformationBasic() {
        return userInformationBasic;
    }

    public void setUserInformationBasic(UserInformationBasic userInformationBasic) {
        this.userInformationBasic = userInformationBasic;
    }

}
