package com.rikkitomikoehrhart.league_standard.model;

import java.time.LocalDate;


public class Player {
    private String id;
    private Team team;
    private String status;
    private String first_name;
    private String last_name;
    private int height;
    private String position;
    private int jersey_number;
    private int experience;
    private String college;
    private String birth_place;
    private LocalDate birthdate;
    private int rookie_year;
    private int draft_round;
    private int draft_pick;

    public Player() {};
    public Player(String id, Team team, String status, String first_name, String last_name, int height, String position, int jersey_number, int experience, String college, String birth_place, LocalDate birthdate, int rookie_year, int draft_round, int draft_pick) {
        this.id = id;
        this.team = team;
        this.status = status;
        this.first_name = first_name;
        this.last_name = last_name;
        this.height = height;
        this.position = position;
        this.jersey_number = jersey_number;
        this.experience = experience;
        this.college = college;
        this.birth_place = birth_place;
        this.birthdate = birthdate;
        this.rookie_year = rookie_year;
        this.draft_round = draft_round;
        this.draft_pick = draft_pick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJersey_number() {
        return jersey_number;
    }

    public void setJersey_number(int jersey_number) {
        this.jersey_number = jersey_number;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getRookie_year() {
        return rookie_year;
    }

    public void setRookie_year(int rookie_year) {
        this.rookie_year = rookie_year;
    }

    public int getDraft_round() {
        return draft_round;
    }

    public void setDraft_round(int draft_round) {
        this.draft_round = draft_round;
    }

    public int getDraft_pick() {
        return draft_pick;
    }

    public void setDraft_pick(int draft_pick) {
        this.draft_pick = draft_pick;
    }

    public String getTeamID() {
        return team.getId();
    }
}
