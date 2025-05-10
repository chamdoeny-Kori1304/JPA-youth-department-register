package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import com.kori1304.jpayouthdepartmentregister.attendance.domain.AttendanceEntity;
import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import com.kori1304.jpayouthdepartmentregister.member.domain.Gender;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
import com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure.SmallGroupEntity;

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

  private String name;
  private Gender gender;
  private String phoneNumber;
  private String address;
  private LocalDate birthDate;
  private String humanRelations;

  @OneToOne
  @JoinColumn(name = "small_group_id")
  private SmallGroupEntity smallGroupEntity;

  @Builder.Default
  @OneToMany(mappedBy = "member")
  private List<AttendanceEntity> attendanceList = new ArrayList<>();


  static public MemberEntity fromDomain(Member member) {
    SmallGroupEntity smallGroup = SmallGroupEntity.fromDomain(member.getSmallGroup());

    MemberEntity entity = MemberEntity.builder()
        .smallGroupEntity(smallGroup)
        .name(member.getName())
        .gender(member.getGender())
        .phoneNumber(member.getPhoneNumber())
        .address(member.getAddress())
        .birthDate(member.getBirthDate())
        .humanRelations(member.getHumanRelations())
        .build();

    entity.setId(member.getId());

    return entity;
  }

  @Override
  public String toString() {

    return "MemberEntity{" +
        "smallGroupName='" + smallGroupEntity + '\'' +
        ", name='" + name + '\'' +
        ", gender=" + gender +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        ", birthDate=" + birthDate +
        ", humanRelations='" + humanRelations + '\'' +
        ", attendanceList=" + attendanceList +
        '}';
  }

  public Member toDomain() {
    SmallGroup smallGroup = SmallGroup.builder().id(smallGroupEntity.getId()).name(smallGroupEntity.getName()).build();

    return Member.builder()
        .smallGroup(smallGroup)
        .name(name)
        .gender(gender)
        .phoneNumber(phoneNumber)
        .address(address)
        .birthDate(birthDate)
        .humanRelations(humanRelations)
        .build();

  }
}
