package com.kori1304.jpayouthdepartmentregister.attendance.domain;

import com.kori1304.jpayouthdepartmentregister.attendance.infrastructure.AttendanceEntity;


public interface Attendances {


  public AttendanceEntity add(Long memberId, Attendance attendance);

  public AttendanceEntity update(Long memberId, Attendance attendance);

  public boolean delete(Long memberId, Attendance attendance);

}
