package com.kadipe.demo.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record UserInfoRecord(
        PersonalInfoRecord personalInfo,
        List<EmailRecord> listEmail,
        List<PhoneRecord> listPhone,
        List<AddressRecord> listAddress) {}
