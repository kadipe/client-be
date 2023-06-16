package com.kadipe.demo.user.model;

import java.time.LocalDate;

public record PersonalInfoRecord(
        String id,
        String email,
        String fullName,
        LocalDate birthday,
        String motherName,
        String fatherName,
        String gender,
        String maritalStatus,
        String blood,
        String rh,
        String countryCodeCel,
        String localCodeCel,
        String numberCel,
        String countryCodeTel,
        String localCodeTel,
        String numberTel,
        String address,
        String number,
        String complement,
        String postalCode,
        String hash) {}
