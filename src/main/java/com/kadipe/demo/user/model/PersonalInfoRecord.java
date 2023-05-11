package com.kadipe.demo.user.model;

import java.time.LocalDate;

public record PersonalInfoRecord(
        String id,
        String name,
        LocalDate birthday,
        String motherName,
        String fatherName,
        String gender,
        String maritalState,
        String blood,
        String rh,
        String hash) {}
