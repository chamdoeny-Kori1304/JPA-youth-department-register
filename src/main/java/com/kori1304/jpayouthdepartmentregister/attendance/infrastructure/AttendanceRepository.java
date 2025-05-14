package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
}
