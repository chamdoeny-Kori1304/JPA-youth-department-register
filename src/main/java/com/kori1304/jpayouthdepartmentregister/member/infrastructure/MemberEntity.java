package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import com.kori1304.jpayouthdepartmentregister.member.domain.Gender;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

  @Column(name = "small_group_id")
  private Long smallGroupId;



  static public MemberEntity fromDomain(Member member) {
    Long smallGroupID = member.getSmallGroup() == null ? null : member.getSmallGroup().getId();

    MemberEntity entity = MemberEntity.builder()
        .smallGroupId(smallGroupID)
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
        "smallGroupID'" + smallGroupId + '\'' +
        ", name='" + name + '\'' +
        ", gender=" + gender +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        ", birthDate=" + birthDate +
        ", humanRelations='" + humanRelations + '\'' +
        '}';
  }

  public Member toDomain() {
    SmallGroup smallGroup = smallGroupId == null ? null : SmallGroup.builder().id(smallGroupId).build();

    return Member.builder()
        .id(getId())
        .name(name)
        .gender(gender)
        .phoneNumber(phoneNumber)
        .address(address)
        .birthDate(birthDate)
        .humanRelations(humanRelations)
        .smallGroup(smallGroup)
        .build();

  }
}
