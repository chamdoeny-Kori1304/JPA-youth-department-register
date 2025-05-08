package com.kori1304.jpayouthdepartmentregister.member.domain;

public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private final String koreanValue;

    Gender(String koreanValue) {
        this.koreanValue = koreanValue;
    }

    @Override
    public String toString() {
        return koreanValue;
    }
}