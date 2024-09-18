package com.springboot.patientdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;

    @Column(name="first_name")
    @NotNull(message = "field required")
    private String firstName;

    @Column(name="last_name")
    @NotNull(message = "field required")
    private String lastName;

    @Column(name="gender")
    @NotNull(message = "field required")
    private String gender;

    @Column(name="age")
    @Min(value = 0, message = "Age must be larger than or equal to 0")
    @Max(value = 120, message = "Age should not be more than 120")
    @NotNull(message = "field required")
    private Integer age;

    @Column(name="email")
    @Email(message = "Invalid email")
    @NotNull(message = "field required")
    private String email;

    @Column(name="phone_number")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "Phone number format is invalid")
    @NotNull(message = "field required")
    private String phoneNumber;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public Patient() {
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
