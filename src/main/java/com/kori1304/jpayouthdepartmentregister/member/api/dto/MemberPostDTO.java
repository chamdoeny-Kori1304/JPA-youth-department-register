package com.kori1304.jpayouthdepartmentregister.member.api.dto;

import com.kori1304.jpayouthdepartmentregister.member.domain.Gender;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class MemberPostDTO {

  private String name;
  private Gender gender;
  private String phoneNumber;
  private String address;
  private LocalDate birthDate;
  private String humanRelations;

  // 이름을 받아서 DB에서 소그룹 ID를 받자
  private String smallGroupName;

  public Member toDomain() {

    return Member.builder()
        .name(name)
        .gender(gender)
        .phoneNumber(phoneNumber)
        .address(address)
        .birthDate(birthDate)
        .humanRelations(humanRelations)
        .build();
  }
}
