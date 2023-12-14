package com.example.sarvikademo.modal;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "pet")
public class Pet {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name")
    private  String name;

    @Column(name = "owner")
    private  String owner;

    @Column(name = "species")
    private  String species;

    @Column(name = "sex")
    private  String sex;

    @Column(name = "birth")
    private  Date birth;

    @Column(name = "death")
    private Date death;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }
}
