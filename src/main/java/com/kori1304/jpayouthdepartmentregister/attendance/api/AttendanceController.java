package com.kori1304.jpayouthdepartmentregister.attendance.api;


import com.kori1304.jpayouthdepartmentregister._common.ResponseDTO;
import com.kori1304.jpayouthdepartmentregister._common.util.DateUtils;
import com.kori1304.jpayouthdepartmentregister.attendance.application.AttendanceService;
import com.kori1304.jpayouthdepartmentregister.attendance.domain.Attendance;
import com.kori1304.jpayouthdepartmentregister.member.application.MemberService;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * TODO 설계 단계
 * 1. interface Attendances에 적어둔 메서드 정의
 * 2. adepter 객체에 repository 2개 사용해되 괘찮은가?
 * <p>
 * 2. MemberService에 date를 입력받아 date에 출석한 멤버 return 기능 추가
 */


@RequiredArgsConstructor
@RestController
@RequestMapping("/attendances")
public class AttendanceController {

  /**
   * 1. (name)  =>List<Attendance> :name아 출석한 모든 attendance (year도 같이 받아야함) 2. (date) => List<Member> : date에 출석한 모든
   * Member 반환 (MemberService로 이동 예정) 3. (name, date) => Boolean : 출석 체크 또는 출석 변경 4. (date) =>
   */

  private final AttendanceService attendanceService;
  private final MemberService memberService;

  @Operation(summary = "출석 체크", description = "멤버 이름, ", tags = {"AttendanceController"})
  @PostMapping("")
  public ResponseEntity<ResponseDTO> post(@RequestBody Attendance dto) {
    ResponseEntity<ResponseDTO> reject = validateSundayOrReject(dto.getDate());
    if (reject != null) {
      return reject;
    }

    try {
      Member member = memberService.getByName(dto.getName());
      Boolean res = attendanceService.add(member.getId(), dto);
      HttpStatus httpStatus = res ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

      return ResponseEntity.ok().body(new ResponseDTO(httpStatus, "출석 체크: " + res, res));
    } catch (Exception e) {
      return handleServerError("출석 생성 실패: " + e.getMessage(), e);
    }
  }

  @Operation(summary = "출석 수정", description = "멤버 이름, 결과 값이 true면 성공 false나 ", tags = {"AttendanceController"})
  @PutMapping("")
  public ResponseEntity<ResponseDTO> put(@RequestBody Attendance dto) {
    ResponseEntity<ResponseDTO> reject = validateSundayOrReject(dto.getDate());
    if (reject != null) {
      return reject;
    }

    boolean res = attendanceService.update(dto);
    HttpStatus httpStatus = res ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

    return ResponseEntity.ok().body(new ResponseDTO(httpStatus, "출석 수정: " + res, res));
  }

  @Operation(summary = "", description = "멤버 이름, ", tags = {"AttendanceController"})
  @GetMapping("")
  public ResponseEntity<ResponseDTO> get(@RequestParam(name = "name", defaultValue = "") String memberName) {

    if (memberName == null || memberName.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
          .body(new ResponseDTO(HttpStatus.NOT_ACCEPTABLE, "이름을 입력하세요", false));
    }

    Member member = memberService.getByName(memberName);
    List<LocalDate> res = attendanceService.getDatesByMemberOrNull(member.getId());
    HttpStatus httpStatus = res != null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

    return ResponseEntity.ok().body(new ResponseDTO(httpStatus, "조회 성공", res));

  }

  private ResponseEntity<ResponseDTO> validateSundayOrReject(LocalDate date) {
    String msg = "날짜가 일요일이 아닙니다";

    if (!DateUtils.isSunday(date)) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
          .body(new ResponseDTO(HttpStatus.NOT_ACCEPTABLE, msg, false));
    }
    return null;
  }

  private ResponseEntity<ResponseDTO> handleServerError(String message, Exception e) {
    return ResponseEntity.internalServerError()
        .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, message, e.getCause()));
  }


}
