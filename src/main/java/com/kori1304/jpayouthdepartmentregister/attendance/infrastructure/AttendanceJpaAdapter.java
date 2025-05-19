package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.RepositoryAccessException;
import com.kori1304.jpayouthdepartmentregister._common.util.RepositoryExceptionHandler;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendances;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;


/**
 * TODO
 * Attendances.isAttendnace가 true인 것만 find 하기
 */

@Repository
@Slf4j
@RequiredArgsConstructor
public class AttendanceJpaAdapter implements Attendances {

  final AttendanceRepository attendanceRepository;
  final ModelMapper modelMapper;

  @Override
  public Attendance add(Attendance attendance) throws RepositoryAccessException {
    return RepositoryExceptionHandler.execute(() -> {

      AttendanceEntity entity = modelMapper.map(attendance, AttendanceEntity.class);
      AttendanceEntity result = attendanceRepository.save(entity);

      return modelMapper.map(result, Attendance.class);
    });
  }

  @Override
  public Attendance update(Attendance attendance) throws RepositoryAccessException {
    return RepositoryExceptionHandler.execute(() -> {

      AttendanceEntity entity = modelMapper.map(attendance, AttendanceEntity.class);
      AttendanceEntity result = attendanceRepository.save(entity);

      return modelMapper.map(result, Attendance.class);

    });
  }

  @Override
  public List<Attendance> getByMemberId(Long memberId) throws RepositoryAccessException {
    return RepositoryExceptionHandler.execute(() -> {

      List<AttendanceEntity> res = attendanceRepository.getAllByMemberIdAndIsAttendance(memberId, true);

      return res.stream().map(
          order -> modelMapper.map(order, Attendance.class)
      ).collect(Collectors.toList());
    });
  }

  @Override
  public List<LocalDate> getByDatesByMemberId(Long memberId) throws RepositoryAccessException {
    return RepositoryExceptionHandler.execute(() -> {

      List<LocalDate> response = new ArrayList<>();

      List<AttendanceEntity> attendanceEntityList = attendanceRepository.getAllByMemberIdAndIsAttendance(memberId,
          true);

      for (AttendanceEntity entity : attendanceEntityList) {
        response.add(entity.getDate());
      }
      return response;
    });
  }

  @Override
  public boolean delete(Long attendanceId, Attendance attendance) {
    return false;
  }
}
