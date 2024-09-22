package com.springboot.patientdemo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class PatientResponse {
    int id;
    String firstName;
    String lastName;
    String gender;
    Integer age;
    String email;
    String phoneNumber;

    public String toString() {
        return "Patient [id=" + id + ", firstName=" + firstName + ", lastName="
                + lastName + ", gender=" + gender + ", age=" + age + ", email=" + email
                + ", phoneNumber=" + phoneNumber + "]";
    }
}
