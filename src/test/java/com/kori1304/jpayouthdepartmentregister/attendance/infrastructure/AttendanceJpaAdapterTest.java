package com.kori1304.jpayouthdepartmentregister.attendance.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.exception.RepositoryAccessException;
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
    Attendance attendance = AttendanceTestFactory.getSample(1);
    AttendanceEntity attendanceEntity = AttendanceEntityTestFactory.getSample(1);

    when(modelMapper.map(attendance, AttendanceEntity.class)).thenReturn(attendanceEntity);
    when(attendanceRepository.save(attendanceEntity)).thenReturn(attendanceEntity);
    when(modelMapper.map(attendanceEntity, Attendance.class)).thenReturn(attendance);

    // When
    Attendance result = attendanceJpaAdapter.add(attendance);

    // Then
    assertNotNull(result);
    assertEquals(attendance, result);
    verify(modelMapper, times(1)).map(attendance, AttendanceEntity.class);
    verify(attendanceRepository, times(1)).save(attendanceEntity);
    verify(modelMapper, times(1)).map(attendanceEntity, Attendance.class);
  }

  @Test
  void add_ShouldThrowRepositoryAccessException() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    AttendanceEntity attendanceEntity = AttendanceEntityTestFactory.getSample(1);
    RuntimeException expectedException = new RuntimeException("Error saving attendance");

    when(modelMapper.map(attendance, AttendanceEntity.class)).thenReturn(attendanceEntity);
    when(attendanceRepository.save(attendanceEntity)).thenThrow(expectedException);

    // When
    assertThrows(RepositoryAccessException.class, () -> attendanceJpaAdapter.add(attendance));
  }

  @Test
  void update_ShouldUpdateAttendance() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    AttendanceEntity attendanceEntity = AttendanceEntityTestFactory.getSample(1);

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
  void update_ShouldThrowRepositoryAccessException() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    AttendanceEntity attendanceEntity = AttendanceEntityTestFactory.getSample(1);
    RuntimeException expectedException = new RuntimeException("Error updating attendance");

    when(modelMapper.map(attendance, AttendanceEntity.class)).thenReturn(attendanceEntity);
    when(attendanceRepository.save(attendanceEntity)).thenThrow(expectedException);

    // When
    assertThrows(RepositoryAccessException.class, () -> attendanceJpaAdapter.update(attendance));
  }

  @Test
  void getByMemberId_ShouldReturnAttendanceList() {
    // Given
    Long memberId = 1L;
    AttendanceEntity attendanceEntity1 = AttendanceEntityTestFactory.getSample(1);
    AttendanceEntity attendanceEntity2 = AttendanceEntityTestFactory.getSample(2);
    List<AttendanceEntity> attendanceEntities = Arrays.asList(attendanceEntity1, attendanceEntity2);

    when(attendanceRepository.getAllByMemberIdAndIsAttendance(memberId, true)).thenReturn(attendanceEntities);
    when(modelMapper.map(attendanceEntity1, Attendance.class)).thenReturn(AttendanceTestFactory.getSample(1));
    when(modelMapper.map(attendanceEntity2, Attendance.class)).thenReturn(AttendanceTestFactory.getSample(2));

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
  void getByMemberId_ShouldThrowRepositoryAccessException() {
    // Given
    Long memberId = 1L;
    RuntimeException expectedException = new RuntimeException("Error retrieving attendance by member ID");

    when(attendanceRepository.getAllByMemberIdAndIsAttendance(memberId, true)).thenThrow(expectedException);

    // When
    assertThrows(RepositoryAccessException.class, () -> attendanceJpaAdapter.getByMemberId(memberId));
  }

  @Test
  void getByDatesByMemberId_ShouldReturnDateList() {
    // Given
    Long memberId = 1L;
    AttendanceEntity attendanceEntity1 = AttendanceEntityTestFactory.getSample(1);
    AttendanceEntity attendanceEntity2 = AttendanceEntityTestFactory.getSample(2);
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

  @Test
  void getByDatesByMemberId_ShouldThrowRepositoryAccessException() {
    // Given
    Long memberId = 1L;
    RuntimeException expectedException = new RuntimeException("Error retrieving attendance dates by member ID");

    when(attendanceRepository.getAllByMemberIdAndIsAttendance(memberId, true)).thenThrow(expectedException);

    // When
    assertThrows(RepositoryAccessException.class, () -> attendanceJpaAdapter.getByDatesByMemberId(memberId));
  }

  @Test
  void delete_ShouldReturnFalse() {
    // Given
    Long attendanceId = 1L;
    Attendance attendance = AttendanceTestFactory.getSample(1);

    // When
    boolean result = attendanceJpaAdapter.delete(attendanceId, attendance);

    // Then
    assertFalse(result);
  }
}