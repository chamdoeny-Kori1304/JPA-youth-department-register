package com.kori1304.jpayouthdepartmentregister.attendance.domain;

import com.kori1304.jpayouthdepartmentregister._common.exception.RepositoryAccessException;
import java.time.LocalDate;
import java.util.List;


public interface Attendances {

  Attendance add(Attendance attendance) throws RepositoryAccessException;

  Attendance update(Attendance attendance) throws RepositoryAccessException;

  List<Attendance> getByMemberId(Long memberId) throws RepositoryAccessException;

  List<LocalDate> getByDatesByMemberId(Long memberId) throws RepositoryAccessException;

  boolean delete(Long memberId, Attendance attendance) throws RepositoryAccessException;
}
