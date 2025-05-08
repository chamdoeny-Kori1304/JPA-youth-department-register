package com.kori1304.jpayouthdepartmentregister.member.api;

import com.kori1304.jpayouthdepartmentregister.member.domain.MemberEntity;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("members")
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;

  @PostMapping("")
  public ResponseEntity<?> post(@RequestBody MemberEntity memberEntity) {
    MemberEntity res = memberRepository.save(memberEntity);
    try {
      return ResponseEntity.ok().body(res);
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body("Failed to get all member data: " + e.getMessage());

    }

  }
}
