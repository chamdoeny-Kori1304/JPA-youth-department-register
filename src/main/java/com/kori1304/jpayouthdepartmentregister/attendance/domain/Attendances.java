package com.kori1304.jpayouthdepartmentregister.attendance.domain;

import java.time.LocalDate;
import java.util.List;


public interface Attendances {


  Attendance add(Long memberId, Attendance attendance);

  Attendance update(Attendance attendance);

  List<Attendance> getByMemberId(Long memberId);

  List<LocalDate> getByDatesByMemberId(Long memberId);

  boolean delete(Long memberId, Attendance attendance);

}
