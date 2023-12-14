package com.example.sarvikademo.modal;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @Column(name="pet_id")
    private Integer petId;

    @Id
    @Column(name = "date")
    private Date date;

    @Id
    @Column(name = "type")
    private  String type;

    @Id
    @Column(name = "remark")
    private  String remark;


    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
