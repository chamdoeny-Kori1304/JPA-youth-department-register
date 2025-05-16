package com.kori1304.jpayouthdepartmentregister.attendance.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Builder(toBuilder = true)
public class Attendance {

  private Long id;
  private String name; // 출석한 사람
  private Long memberId;
  private Boolean isAttendance;
  private LocalDate date; // 출석한 날짜
}
