package com.kori1304.jpayouthdepartmentregister.attendance.application;

import com.kori1304.jpayouthdepartmentregister._common.RepositoryAccessException;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendances;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceService {

  private final Attendances attendances;

  public boolean add(Long memberId, Attendance attendance) {
    try {
      Attendance domain = attendances.add(memberId, attendance);
      return domain.getClass() == Attendance.class;

    } catch (Exception e) {
      log.error("레포지토리 접근 실패", e);

      return false;
    }
  }

  //TODO MemberService로 이동
  public List<Member> getMembersByDate(LocalDate date) {

    return null;
  }

  public List<Attendance> getByMember(Long memberId) {

    try {
      List<Attendance> result = attendances.getByMemberId(memberId);
      return result;

    } catch (Exception e) {
      log.error("getByMember() 실패", e);

      return null;
    }

  }

  public List<LocalDate> getDatesByMember(Long memberId) {

    try {
      List<LocalDate> result = attendances.getByDatesByMemberId(memberId);
      return result;

    } catch (Exception e) {
      log.error("getDatesByMember() 실패", e);

      return null;
    }
  }

  public boolean update(Attendance attendance) {

    try {
      Attendance domain = attendances.update(attendance);
      return domain.getClass() == Attendance.class;

    } catch (Exception e) {
      log.error("update() 실패", e);

      return false;
    }
  }


  public boolean delete(Attendance attendance) {

    return false;
  }

}
