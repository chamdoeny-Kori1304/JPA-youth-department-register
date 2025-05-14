package com.kori1304.jpayouthdepartmentregister.common.factory;

import com.kori1304.jpayouthdepartmentregister.member.domain.Gender;
import com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure.SmallGroupEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberEntity;

public class MemberEntityTestFactory {

  private static final Object[][] SAMPLE_DATA = {
      {1L, "홍길동", Gender.MALE, "777-1111-1111", "서울특별시 강남구", LocalDate.of(1995, 1, 15), "김철수", 1L},
      {2L, "김영희", Gender.FEMALE, "777-2222-2222", "서울특별시 마포구", LocalDate.of(1994, 5, 10), "이영수", 1L},
      {3L, "이철수", Gender.MALE, "777-3333-3333", "부산광역시 해운대구", LocalDate.of(1996, 3, 25), "홍길동", 2L},
      {4L, "박지민", Gender.FEMALE, "777-4444-4444", "대전광역시 유성구", LocalDate.of(1995, 12, 5), "김영희", 2L},
      {5L, "최수민", Gender.FEMALE, "777-5555-5555", "광주광역시 서구", LocalDate.of(1997, 7, 18), "이철수", 3L},
      {6L, "한지훈", Gender.MALE, "777-6666-6666", "인천광역시 연수구", LocalDate.of(1993, 9, 9), "박지민", 3L},
      {7L, "서예린", Gender.FEMALE, "777-7777-7777", "대구광역시 중구", LocalDate.of(1998, 11, 30), "최수민", 4L},
      {8L, "정우진", Gender.MALE, "777-8888-8888", "울산광역시 남구", LocalDate.of(1992, 6, 6), "한지훈", 4L},
      {9L, "유하늘", Gender.FEMALE, "777-9999-9999", "경기도 성남시", LocalDate.of(1999, 4, 20), "서예린", 5L},
      {10L, "노진혁", Gender.MALE, "777-0000-0000", "경기도 수원시", LocalDate.of(1990, 10, 1), "정우진", 5L}
  };

  public static List<MemberEntity> createSampleEntities() {
    List<MemberEntity> entities = new ArrayList<>();
    for (Object[] row : SAMPLE_DATA) {
      entities.add(MemberEntity.builder()
          .name((String) row[1])
          .gender((Gender) row[2])
          .phoneNumber((String) row[3])
          .address((String) row[4])
          .birthDate((LocalDate) row[5])
          .humanRelations((String) row[6])
          .smallGroupId((Long) row[7])
          .build()
      );
    }
    return entities;
  }

  public static MemberEntity getSampleMemberEntity(int number) {
    if (number > 9 || number < 0) {
      return null;
    }

    Object[] row = SAMPLE_DATA[number];

    return MemberEntity.builder()
        .name((String) row[1])
        .gender((Gender) row[2])
        .phoneNumber((String) row[3])
        .address((String) row[4])
        .birthDate((LocalDate) row[5])
        .humanRelations((String) row[6])
        .smallGroupId((Long) row[7])
        .build();
  }
}

