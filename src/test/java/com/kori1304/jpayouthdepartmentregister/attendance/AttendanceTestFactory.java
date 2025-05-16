package com.kori1304.jpayouthdepartmentregister.attendance;

import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import com.kori1304.jpayouthdepartmentregister.attendance.infrastructure.AttendanceEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceTestFactory {

  public static String[] names = {"홍길동", "김영희", "이철수", "박지민", "최수민", "한지훈", "서예린", "정우진", "유하늘", "노진혁"};

  private static final Object[][] SAMPLE_DATA = {
      {1L, names[0], LocalDate.now(), true, 1L},
      {2L, names[1], LocalDate.now().minusDays(1), true, 1L},
      {3L, names[2], LocalDate.now().minusDays(2), false, 2L},
      {4L, names[3], LocalDate.now().minusDays(3), true, 2L},
      {5L, names[4], LocalDate.now().minusDays(4), false, 3L},
      {6L, names[5], LocalDate.now().minusDays(5), true, 3L},
      {7L, names[6], LocalDate.now().minusDays(6), false, 4L},
      {8L, names[7], LocalDate.now().minusDays(7), true, 4L},
      {9L, names[8], LocalDate.now().minusDays(8), false, 5L},
      {10L, names[9], LocalDate.now().minusDays(9), true, 5L}
  };


  public static List<Attendance> createSampleEntities() {
    List<Attendance> entities = new ArrayList<>();

    for (Object[] row : SAMPLE_DATA) {
      entities.add(Attendance.builder()
          .name((String) row[1])
          .date((LocalDate) row[2])
          .isAttendance((Boolean) row[3])
          .memberId((Long) row[4])
          .build()
      );
    }

    return entities;
  }

  public static Attendance getSampleMemberEntity(int number) {
    if (number > 9 || number < 0) {
      return null;
    }

    Object[] row = SAMPLE_DATA[number];

    return Attendance.builder()
        .name((String) row[1])
        .date((LocalDate) row[2])
        .isAttendance((Boolean) row[3])
        .memberId((Long) row[4])
        .build();
  }
}
