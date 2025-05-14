package com.kori1304.jpayouthdepartmentregister.attendance.domain;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Setter
@Getter
@ToString
@Slf4j
@Builder
public class Attendance {

  private Long id;
  private String name; // 출석한 사람
  private Long memberId;
  private boolean isAttendance;
  private LocalDate date; // 출석한 날짜
}
