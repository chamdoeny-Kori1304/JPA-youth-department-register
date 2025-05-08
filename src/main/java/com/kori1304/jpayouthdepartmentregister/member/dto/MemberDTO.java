package com.kori1304.jpayouthdepartmentregister.member.dto;

import com.kori1304.jpayouthdepartmentregister.member.domain.Gender;
import com.kori1304.jpayouthdepartmentregister.member.domain.MemberEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String smallGroupName;
    private String name;
    private Gender gender;
    private String phoneNumber;
    private String address;
    private LocalDate birthDate;
    private String humanRelations;


    @Override
    public String toString() {
        return "MemberDTO{" +
                "smallGroupName='" + smallGroupName + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", humanRelations='" + humanRelations + '\'' +
                '}';
    }

    public MemberEntity toEntity() {
        MemberEntity memberEntity = MemberEntity.builder()
                .smallGroupName(this.smallGroupName)
                .name(this.name)
                .gender(this.gender)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .birthDate(this.birthDate)
                .humanRelations(this.humanRelations)
                .build();

        return memberEntity;
    }
}
