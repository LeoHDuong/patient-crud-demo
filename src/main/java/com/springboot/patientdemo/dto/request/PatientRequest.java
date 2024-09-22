package com.springboot.patientdemo.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class PatientRequest {

    @NotNull(message = "field required")
    String firstName;

    @NotNull(message = "field required")
    String lastName;

    @NotNull(message = "field required")
    String gender;

    @Min(value = 0, message = "Age must be larger than or equal to 0")
    @Max(value = 120, message = "Age should not be more than 120")
    @NotNull(message = "field required")
    Integer age;

    @Email(message = "Invalid email")
    @NotNull(message = "field required")
    String email;

    @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$", message = "Phone number format is invalid")
    @NotNull(message = "field required")
    String phoneNumber;

    public String toString() {
        return "Patient [firstName=" + firstName + ", lastName="
                + lastName + ", gender=" + gender + ", age=" + age + ", email=" + email
                + ", phoneNumber=" + phoneNumber + "]";
    }
}
