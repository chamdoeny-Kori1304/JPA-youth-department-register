package com.kori1304.jpayouthdepartmentregister.attendance.application;

import com.kori1304.jpayouthdepartmentregister._common.RepositoryAccessException;
import com.kori1304.jpayouthdepartmentregister.attendance.AttendanceTestFactory;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendanceServiceTest {

  @Mock
  private Attendances attendances;

  @InjectMocks
  private AttendanceService attendanceService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void add_ShouldReturnTrueWhenAttendanceIsSaved() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    when(attendances.add(1L, attendance)).thenReturn(attendance);

    // When
    boolean result = attendanceService.add(1L, attendance);

    // Then
    assertTrue(result);
    verify(attendances, times(1)).add(1L, attendance);
  }


  @Test
  void add_ShouldReturnFalseWhenRepositoryAccessExceptionOccurs() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    RepositoryAccessException expectedException = new RepositoryAccessException("Error saving attendance");
    when(attendances.add(1L, attendance)).thenThrow(expectedException);

    // When
    boolean result = attendanceService.add(1L, attendance);

    // Then
    assertFalse(result);
    verify(attendances, times(1)).add(1L, attendance);
  }

  @Test
  void getByMember_ShouldReturnAttendanceList() {
    // Given
    Long memberId = 1L;
    Attendance attendance1 = AttendanceTestFactory.getSample(1);
    Attendance attendance2 = AttendanceTestFactory.getSample(2);
    List<Attendance> attendanceList = Arrays.asList(attendance1, attendance2);

    when(attendances.getByMemberId(memberId)).thenReturn(attendanceList);

    // When
    List<Attendance> result = attendanceService.getByMember(memberId);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    verify(attendances, times(1)).getByMemberId(memberId);
  }

  @Test
  void getByMember_ShouldReturnNullWhenRepositoryAccessExceptionOccurs() {
    // Given
    Long memberId = 1L;
    RuntimeException expectedException = new RuntimeException("Error retrieving attendance by member ID");
    when(attendances.getByMemberId(memberId)).thenThrow(expectedException);

    // When
    List<Attendance> result = attendanceService.getByMember(memberId);

    // Then
    assertNull(result);
    verify(attendances, times(1)).getByMemberId(memberId);
  }

  @Test
  void getDatesByMember_ShouldReturnDateList() {
    // Given
    Long memberId = 1L;
    LocalDate date1 = LocalDate.now();
    LocalDate date2 = LocalDate.now().minusDays(1);
    List<LocalDate> dateList = Arrays.asList(date1, date2);

    when(attendances.getByDatesByMemberId(memberId)).thenReturn(dateList);

    // When
    List<LocalDate> result = attendanceService.getDatesByMember(memberId);

    // Then
    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.contains(date1));
    assertTrue(result.contains(date2));
    verify(attendances, times(1)).getByDatesByMemberId(memberId);
  }

  @Test
  void getDatesByMember_ShouldReturnNullWhenRepositoryAccessExceptionOccurs() {
    // Given
    Long memberId = 1L;
    RuntimeException expectedException = new RuntimeException("Error retrieving attendance dates by member ID");
    when(attendances.getByDatesByMemberId(memberId)).thenThrow(expectedException);

    // When
    List<LocalDate> result = attendanceService.getDatesByMember(memberId);

    // Then
    assertNull(result);
    verify(attendances, times(1)).getByDatesByMemberId(memberId);
  }

  @Test
  void update_ShouldReturnTrueWhenAttendanceIsUpdated() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    when(attendances.update(attendance)).thenReturn(attendance);

    // When
    boolean result = attendanceService.update(attendance);

    // Then
    assertTrue(result);
    verify(attendances, times(1)).update(attendance);
  }

  @Test
  void update_ShouldReturnFalseWhenAttendanceIsNotUpdated() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    when(attendances.update(attendance)).thenReturn(null);

    // When
    boolean result = attendanceService.update(attendance);

    // Then
    assertFalse(result);
    verify(attendances, times(1)).update(attendance);
  }

  @Test
  void update_ShouldReturnFalseWhenRepositoryAccessExceptionOccurs() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);
    RuntimeException expectedException = new RuntimeException("Error updating attendance");
    when(attendances.update(attendance)).thenThrow(expectedException);

    // When
    boolean result = attendanceService.update(attendance);

    // Then
    assertFalse(result);
    verify(attendances, times(1)).update(attendance);
  }

  @Test
  void delete_ShouldReturnFalseAsMethodIsNotImplemented() {
    // Given
    Attendance attendance = AttendanceTestFactory.getSample(1);

    // When
    boolean result = attendanceService.delete(attendance);

    // Then
    assertFalse(result);
  }
}