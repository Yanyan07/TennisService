package com.beaconfire.domain;

import com.fasterxml.jackson.annotation.JsonView;
import java.time.LocalDate;
import java.time.Period;

public class Player {
    private int playerId;
    @JsonView(Views.Regular.class)
    private String firstName;
    @JsonView(Views.Admin.class)
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private double heightCm;
    private double weightKg;
    private int careerTitles;
    private int careerWins;
    private String country;
    private int ranking;
    private Integer teamId; // nullable

    @JsonView(Views.Regular.class)
    public int getAge() {
        return birthDate != null ? Period.between(birthDate, LocalDate.now()).getYears() : 0;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(double heightCm) {
        this.heightCm = heightCm;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public int getCareerTitles() {
        return careerTitles;
    }

    public void setCareerTitles(int careerTitles) {
        this.careerTitles = careerTitles;
    }

    public int getCareerWins() {
        return careerWins;
    }

    public void setCareerWins(int careerWins) {
        this.careerWins = careerWins;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
