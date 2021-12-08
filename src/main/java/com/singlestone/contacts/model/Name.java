package com.singlestone.contacts.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Name {

    @Column(name = "first_name")
    @NotBlank
    private String first;

    @Column(name = "middle_name")
    private String middle;

    @Column(name = "last_name")
    private String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String firstName) {
        this.first = firstName;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middleName) {
        this.middle = middleName;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String lastName) {
        this.last = lastName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + first + '\'' +
                ", middleName='" + middle + '\'' +
                ", lastName='" + last + '\'' +
                '}';
    }
}
