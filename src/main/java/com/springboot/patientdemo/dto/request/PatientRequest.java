package com.springboot.patientdemo.dto.request;

import jakarta.validation.constraints.*;


public class PatientRequest {

    @NotNull(message = "field required")
    private String firstName;

    @NotNull(message = "field required")
    private String lastName;

    @NotNull(message = "field required")
    private String gender;

    @Min(value = 0, message = "Age must be larger than or equal to 0")
    @Max(value = 120, message = "Age should not be more than 120")
    @NotNull(message = "field required")
    private Integer age;

    @Email(message = "Invalid email")
    @NotNull(message = "field required")
    private String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "Phone number format is invalid")
    @NotNull(message = "field required")
    private String phoneNumber;

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

    public void setAge(Integer age) {
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

}
