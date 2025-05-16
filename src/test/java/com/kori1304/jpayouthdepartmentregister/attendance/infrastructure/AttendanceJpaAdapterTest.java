package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;

import com.kori1304.jpayouthdepartmentregister.attendance.AttendanceEntityTestFactory;
import com.kori1304.jpayouthdepartmentregister.attendance.AttendanceTestFactory;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendanceJpaAdapterTest {

  @Mock
  private AttendanceRepository attendanceRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private AttendanceJpaAdapter attendanceJpaAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void add_ShouldSaveAttendance() {
    // Given
//    Attendance
    Attendance attendance = AttendanceTestFactory.getSampleMemberEntity(0);
    AttendanceEntity attendanceEntity = AttendanceEntityTestFactory.getSampleMemberEntity(0);

    when(modelMapper.map(attendance, AttendanceEntity.class)).thenReturn(attendanceEntity);
    when(attendanceRepository.save(attendanceEntity)).thenReturn(attendanceEntity);
    when(modelMapper.map(attendanceEntity, Attendance.class)).thenReturn(attendance);

    // When
    Attendance result = attendanceJpaAdapter.add(1L, attendance);

    // Then
    assertNotNull(result);
    assertEquals(attendance, result);
    verify(modelMapper, times(1)).map(attendance, AttendanceEntity.class);
    verify(attendanceRepository, times(1)).save(attendanceEntity);
    verify(modelMapper, times(1)).map(attendanceEntity, Attendance.class);
  }

  @Test
  void update_ShouldUpdateAttendance() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSampleMemberEntity(1);
    AttendanceEntity attendanceEntity = AttendanceEntityTestFactory.getSampleMemberEntity(1);

    when(modelMapper.map(attendance, AttendanceEntity.class)).thenReturn(attendanceEntity);
    when(attendanceRepository.save(attendanceEntity)).thenReturn(attendanceEntity);
    when(modelMapper.map(attendanceEntity, Attendance.class)).thenReturn(attendance);

    // When
    Attendance result = attendanceJpaAdapter.update(attendance);

    // Then
    assertNotNull(result);
    assertEquals(attendance, result);
    verify(modelMapper, times(1)).map(attendance, AttendanceEntity.class);
    verify(attendanceRepository, times(1)).save(attendanceEntity);
    verify(modelMapper, times(1)).map(attendanceEntity, Attendance.class);
  }

  @Test
  void getByMemberId_ShouldReturnAttendanceList() {
    // Given
    Long memberId = 1L;
    AttendanceEntity attendanceEntity1 = AttendanceEntityTestFactory.getSampleMemberEntity(3);
    AttendanceEntity attendanceEntity2 = AttendanceEntityTestFactory.getSampleMemberEntity(4);
    List<AttendanceEntity> attendanceEntities = Arrays.asList(attendanceEntity1, attendanceEntity2);

    when(attendanceRepository.getAllByMemberIdAndIsAttendance(memberId, true)).thenReturn(attendanceEntities);
    when(modelMapper.map(attendanceEntity1, Attendance.class)).thenReturn(AttendanceTestFactory.getSampleMemberEntity(3));
    when(modelMapper.map(attendanceEntity2, Attendance.class)).thenReturn(AttendanceTestFactory.getSampleMemberEntity(4));

    // When
    List<Attendance> result = attendanceJpaAdapter.getByMemberId(memberId);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(attendanceRepository, times(1)).getAllByMemberIdAndIsAttendance(memberId, true);
    verify(modelMapper, times(1)).map(attendanceEntity1, Attendance.class);
    verify(modelMapper, times(1)).map(attendanceEntity2, Attendance.class);
  }

  @Test
  void getByDatesByMemberId_ShouldReturnDateList() {
    // Given
    Long memberId = 1L;
    AttendanceEntity attendanceEntity1 = AttendanceEntityTestFactory.getSampleMemberEntity(3);
  AttendanceEntity attendanceEntity2 =  AttendanceEntityTestFactory.getSampleMemberEntity(4);
    List<AttendanceEntity> attendanceEntities = Arrays.asList(attendanceEntity1, attendanceEntity2);

    when(attendanceRepository.getAllByMemberIdAndIsAttendance(memberId, true)).thenReturn(attendanceEntities);

    // When
    List<LocalDate> result = attendanceJpaAdapter.getByDatesByMemberId(memberId);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(attendanceEntity1.getDate()));
    assertTrue(result.contains(attendanceEntity2.getDate()));
    verify(attendanceRepository, times(1)).getAllByMemberIdAndIsAttendance(memberId, true);
  }
}