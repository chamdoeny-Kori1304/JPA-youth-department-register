package com.kori1304.jpayouthdepartmentregister.common.factory;


import com.kori1304.jpayouthdepartmentregister.member.domain.Gender;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberTestFactory {

  private static final Object[][] SAMPLE_DATA = {

      {"DTO1팀", "홍길동", Gender.MALE, "010-1111-1111", "서울특별시 강남구", LocalDate.of(1995, 1, 15), "김철수"},
      {"DTO1팀", "김영희", Gender.FEMALE, "010-2222-2222", "서울특별시 마포구", LocalDate.of(1994, 5, 10), "이영수"},
      {"DTO2팀", "이철수", Gender.MALE, "010-3333-3333", "부산광역시 해운대구", LocalDate.of(1996, 3, 25), "홍길동"},
      {"DTO2팀", "박지민", Gender.FEMALE, "010-4444-4444", "대전광역시 유성구", LocalDate.of(1995, 12, 5), "김영희"},
      {"DTO3팀", "최수민", Gender.FEMALE, "010-5555-5555", "광주광역시 서구", LocalDate.of(1997, 7, 18), "이철수"},
      {"DTO3팀", "한지훈", Gender.MALE, "010-6666-6666", "인천광역시 연수구", LocalDate.of(1993, 9, 9), "박지민"},
      {"DTO4팀", "서예린", Gender.FEMALE, "010-7777-7777", "대구광역시 중구", LocalDate.of(1998, 11, 30), "최수민"},
      {"DTO4팀", "정우진", Gender.MALE, "010-8888-8888", "울산광역시 남구", LocalDate.of(1992, 6, 6), "한지훈"},
      {"DTO5팀", "유하늘", Gender.FEMALE, "010-9999-9999", "경기도 성남시", LocalDate.of(1999, 4, 20), "서예린"},
      {"DTO5팀", "노진혁", Gender.MALE, "010-0000-0000", "경기도 수원시", LocalDate.of(1990, 10, 1), "정우진"}
  };

  public static List<Member> createSampleMembers() {
    List<Member> members = new ArrayList<>();
    for (Object[] row : SAMPLE_DATA) {
      members.add(Member.builder()
          .smallGroup(SmallGroup.builder().name((String) row[0]).build())
          .name((String) row[1])
          .gender((Gender) row[2])
          .phoneNumber((String) row[3])
          .address((String) row[4])
          .birthDate((LocalDate) row[5])
          .humanRelations((String) row[6])
          .build());
    }
    return members;
  }

  public static Member getSampleMemberDTO(int number) {
    if (number > 9 || number < 0) return null;

    Object[] row = SAMPLE_DATA[number];

    return Member.builder()
        .smallGroup(SmallGroup.builder().name((String) row[0]).build())
        .name((String) row[1])
        .gender((Gender) row[2])
        .phoneNumber((String) row[3])
        .address((String) row[4])
        .birthDate((LocalDate) row[5])
        .humanRelations((String) row[6])
        .build();
  }
}

