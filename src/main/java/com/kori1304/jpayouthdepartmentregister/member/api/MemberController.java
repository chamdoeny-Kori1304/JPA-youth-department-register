package com.kori1304.jpayouthdepartmentregister.member.api;

import com.kori1304.jpayouthdepartmentregister.member.api.dto.MemberPostDTO;
import com.kori1304.jpayouthdepartmentregister.member.application.MemberService;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroupService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/members")
public class MemberController {

  private final MemberService memberService;
  private final SmallGroupService smallGroupService;
  private final ModelMapper modelMapper;


  @Operation(summary = "멤버 추가", description = "이름, 성별, 생년월일, 주소, 전화번호, 소그룸을 필요", tags = {"MemberController"})
  @PostMapping("")
  public ResponseEntity<?> post(@RequestBody MemberPostDTO dto) {
    String groupName = dto.getSmallGroupName();

    SmallGroup smallGroup = smallGroupService.getByName(groupName);

    if (smallGroup == null) {
      return ResponseEntity.internalServerError()
          .body("Failed to Add new member: " + groupName + "group does not exist");
    }

    try {
      boolean res = memberService.add(dto, smallGroup);

      return ResponseEntity.ok().body(res);
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body("Failed to Add new member: " + e.getMessage());

    }


  }


  @Operation(summary = "이름 유사 검색", description = "{host}/members?name={name} , name에 포함된 키워드로 모든 멤버 검색", tags = {"MemberController"})
  @GetMapping("")
  public ResponseEntity<?> getBySimilarName(@RequestParam(defaultValue = "") String name) {
    try {
      if (name == null || name.isEmpty()) {
        return ResponseEntity.ok(memberService.getAllMembers());

      }
      List<Member> members = memberService.getMembersByNameContaining(name);
      return ResponseEntity.ok(members);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("검색 실패: " + e.getMessage());
    }
  }

}
