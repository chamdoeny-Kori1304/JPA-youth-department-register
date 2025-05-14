package com.kori1304.jpayouthdepartmentregister.member.domain;

import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 팩토리 메서드 패턴과 함께 쓰기 좋음
@EqualsAndHashCode(of = "id") // ID 기반 동등성
@Builder(toBuilder = true)
/** ID 프론트 비공개*/
public class Member {

  @Setter
  private final Long id;
  @Setter
  private SmallGroup smallGroup;
  private String name;
  private Gender gender;
  private String phoneNumber;
  private String address;
  private LocalDate birthDate;
  private String humanRelations;


  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", smallGroupName='" + (smallGroup != null ? smallGroup.getName() : "null") + '\'' +
        ", name='" + name + '\'' +
        ", gender=" + gender +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        ", birthDate=" + birthDate +
        ", humanRelations='" + humanRelations + '\'' +
        '}';
  }
}
