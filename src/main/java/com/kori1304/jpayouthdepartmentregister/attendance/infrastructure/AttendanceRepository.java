package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

  List<AttendanceEntity> getAllByMemberIdAndIsAttendance(Long memberId, Boolean isAttendance);
}
