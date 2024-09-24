package com.springboot.patientdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name="patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    int id;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="gender")
    String gender;

    @Column(name="age")
    Integer age;

    @Column(name="email")
    String email;

    @Column(name="phone_number")
    String phoneNumber;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name="created_at")
    LocalDateTime createdAt;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name="updated_at")
    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String toString() {
        return "Patient [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", gender=" + gender + ", age=" + age + ", email=" + email
                + ", phoneNumber=" + phoneNumber + "]";
    }
}