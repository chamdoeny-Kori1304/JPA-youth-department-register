package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;


import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AttendanceEntity  extends BaseEntity {
    private LocalDate attendanceDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

}
