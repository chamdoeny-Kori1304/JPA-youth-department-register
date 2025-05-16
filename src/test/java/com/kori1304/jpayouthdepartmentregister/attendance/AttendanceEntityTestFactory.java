package com.kori1304.jpayouthdepartmentregister.attendance;

import com.kori1304.jpayouthdepartmentregister.attendance.infrastructure.AttendanceEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceEntityTestFactory {

  private static final Object[][] SAMPLE_DATA = {
      {1L, LocalDate.now(), true, 1L},
      {2L, LocalDate.now().minusDays(1), true, 1L},
      {3L, LocalDate.now().minusDays(2), false, 2L},
      {4L, LocalDate.now().minusDays(3), true, 2L},
      {5L, LocalDate.now().minusDays(4), false, 3L},
      {6L, LocalDate.now().minusDays(5), true, 3L},
      {7L, LocalDate.now().minusDays(6), false, 4L},
      {8L, LocalDate.now().minusDays(7), true, 4L},
      {9L, LocalDate.now().minusDays(8), false, 5L},
      {10L, LocalDate.now().minusDays(9), true, 5L}
  };

  public static List<AttendanceEntity> createSampleEntities() {
    List<AttendanceEntity> entities = new ArrayList<>();

    for (Object[] row : SAMPLE_DATA) {
      entities.add(AttendanceEntity.builder()
          .date((LocalDate) row[1])
          .isAttendance((Boolean) row[2])
          .memberId((Long) row[3])
          .build()
      );
    }

    return entities;
  }

  public static AttendanceEntity getSample(int number) {
    if (number > 9 || number < 0) {
      return null;
    }

    Object[] row = SAMPLE_DATA[number];

    return AttendanceEntity.builder()
        .date((LocalDate) row[1])
        .isAttendance((Boolean) row[2])
        .memberId((Long) row[3])
        .build();
  }
}

