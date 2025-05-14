package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;

import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendances;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
@RequiredArgsConstructor
public class AttendanceJpaAdapter implements Attendances {

  final AttendanceRepository attendanceRepository;
  final ModelMapper modelMapper;

  @Override
  public AttendanceEntity add(Long memberId, Attendance attendance) {
    try {
      AttendanceEntity entity = modelMapper.map(attendance, AttendanceEntity.class);
      AttendanceEntity result = attendanceRepository.save(entity);

      return result;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RepositoryCreationException(e.getMessage(), AttendanceEntity.class);

    }
  }

  @Override
  public AttendanceEntity update(Long attendanceId, Attendance attendance) {
    try {
      AttendanceEntity entity = modelMapper.map(attendance, AttendanceEntity.class);
      entity.setId(attendanceId);
      AttendanceEntity result = attendanceRepository.save(entity);

      return result;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public boolean delete(Long attendanceId, Attendance attendance) {
    return false;
  }
}
