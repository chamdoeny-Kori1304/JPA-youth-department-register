package com.kori1304.jpayouthdepartmentregister.member.application;


import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.domain.Members;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class MemberService {

  private final Members members; // 도메인 리포지토리 인터페이스

  public boolean append(Member member) {
    try {
      MemberEntity newMember = members.save(MemberEntity.fromDomain(member));
      return true;
    } catch (Exception e) {
      log.error("Failed to save member: " + e.getMessage());
      return false;
    }
  }

  public List<Member> getAllMembers() {
    List<MemberEntity> entities = members.findAll();
    return entities.stream()
        .map(MemberEntity::toDomain)
        .collect(Collectors.toList());
  }

  public Map<String, List<String>> getMemberNamesGroupedByTeam() {
    Map<String, List<String>> result = new HashMap<>();
    List<Member> allMembers = getAllMembers();

    for (Member member : allMembers) {
      result.computeIfAbsent(member.getSmallGroupName(), k -> new ArrayList<>())
          .add(member.getName());
    }

    return result;
  }
}

