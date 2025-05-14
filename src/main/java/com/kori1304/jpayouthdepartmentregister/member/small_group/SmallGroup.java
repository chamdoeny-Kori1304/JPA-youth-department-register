package com.kori1304.jpayouthdepartmentregister.member.small_group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 팩토리 메서드 패턴과 함께 쓰기 좋음
@EqualsAndHashCode(of = "id") // ID 기반 동등성
@Builder
public class SmallGroup {

  private final Long id;
  private String name;

  @Override
  public String toString() {
    return "SmallGroup{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
