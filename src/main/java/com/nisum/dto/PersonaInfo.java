package com.nisum.dto;

import java.io.Serializable;

public class PersonaInfo implements Serializable {
    private static final long serialVersionUID = 1685606726480195924L;

    private Integer id;
    private String name;
    private String lastname;
    private String address;
    private Long phone;
    private String hair;

    public PersonaInfo(Integer id, String name, String lastname, String address, Long phone, String hair) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.hair = hair;
    }

    public PersonaInfo() {};

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    @Override
    public String toString() {
        return "PersonaInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", hair='" + hair + '\'' +
                '}';
    }
}
