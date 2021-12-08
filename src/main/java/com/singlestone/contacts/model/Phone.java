package com.singlestone.contacts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "contact_phone")
public class Phone {

    @Id
    @NotBlank(message = "Phone number is required")
    private String number;

    @Column(name = "number_type")
    @NotBlank(message = "Phone type is required")
    @Pattern(regexp = "home|work|mobile")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", nullable = false)
    private Contact contact;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Phone{" +
                ", number='" + number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
