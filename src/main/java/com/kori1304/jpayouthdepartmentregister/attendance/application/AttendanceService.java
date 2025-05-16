package com.kori1304.jpayouthdepartmentregister.attendance.application;

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
    Attendance domain = attendances.add(memberId, attendance);

    if (domain.getClass() == Attendance.class) {
      return true;
    }
    return false;
  }

  //TODO MemberService로 이동
  public List<Member> getMembersByDate(LocalDate date) {

    return null;
  }

  public List<Attendance> getByMember(Long memberId) {

    return    attendances.getByMemberId(memberId);

  }

  public List<LocalDate> getDatesByMember(Long memberId) {

    return attendances.getByDatesByMemberId(memberId);
  }

  public boolean update(Attendance attendance) {
    Attendance domain = attendances.update(attendance);

    if (domain.getClass() == Attendance.class) {
      return true;
    }
    return false;
  }


  public boolean delete(Attendance attendance) {

    return false;
  }

}
