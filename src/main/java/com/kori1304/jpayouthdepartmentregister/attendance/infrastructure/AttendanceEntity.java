package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;


import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
@Entity
@Builder(toBuilder = true)
public class AttendanceEntity extends BaseEntity {

  @Column(name = "attendance_date")
  private LocalDate date;

  @Column(name = "is_attendance")
  private Boolean isAttendance;


  @Column(name = "member_id")
  private Long memberId;

}
