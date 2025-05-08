package com.kori1304.jpayouthdepartmentregister.member.domain;

import com.kori1304.jpayouthdepartmentregister.attendance.domain.AttendanceEntity;
import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import com.kori1304.jpayouthdepartmentregister.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MemberEntity extends BaseEntity {

  private String smallGroupName;
  private String name;
  private Gender gender;
  private String phoneNumber;
  private String address;
  private LocalDate birthDate;
  private String humanRelations;

  @Builder.Default
  @OneToMany(mappedBy = "member")
  private List<AttendanceEntity> attendanceList = new ArrayList<>();


  static public MemberEntity fromDTO(MemberDTO memberDTO) {

    MemberEntity memberEntity = MemberEntity.builder()
        .smallGroupName(memberDTO.getSmallGroupName())
        .name(memberDTO.getName())
        .gender(memberDTO.getGender())
        .phoneNumber(memberDTO.getPhoneNumber())
        .address(memberDTO.getAddress())
        .birthDate(memberDTO.getBirthDate())
        .humanRelations(memberDTO.getHumanRelations())
        .build();

    return memberEntity;

  }

  @Override
  public String toString() {
    return "MemberEntity{" +
        "smallGroupName='" + smallGroupName + '\'' +
        ", name='" + name + '\'' +
        ", gender=" + gender +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        ", birthDate=" + birthDate +
        ", humanRelations='" + humanRelations + '\'' +
        ", attendanceList=" + attendanceList +
        '}';
  }

  public MemberDTO toDTO() {
    MemberDTO memberDTO = MemberDTO.builder()
        .smallGroupName(smallGroupName)
        .name(name)
        .gender(gender)
        .phoneNumber(phoneNumber)
        .address(address)
        .birthDate(birthDate)
        .humanRelations(humanRelations)
        .build();

    return memberDTO;
  }
}
