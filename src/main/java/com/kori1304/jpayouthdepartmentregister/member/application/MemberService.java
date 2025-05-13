package com.kori1304.jpayouthdepartmentregister.member.application;


import com.kori1304.jpayouthdepartmentregister.member.api.dto.MemberPostDTO;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.domain.Members;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class MemberService {

  private final Members members; // 도메인 리포지토리 인터페이스
  private final ModelMapper modelMapper;


  @Transactional
  public boolean add(Member member) {
    try {
      members.save(member);

      return true;
    } catch (Exception e) {
      log.error("Failed to save member: " + e.getMessage());
      return false;
    }
  }

  @Transactional
  public boolean add(MemberPostDTO dto, SmallGroup smallGroup) {
    try {

      Member member = dto.toDomain();
      member.setSmallGroup(smallGroup);

      members.save(member);

      return true;
    } catch (Exception e) {
      log.error("Failed to save member: " + e.getMessage());
      return false;
    }
  }

  public List<Member> getAllMembers() {
    return members.findAll();
  }

  public Map<String, List<String>> getMemberNamesGroupedByTeam() {
    Map<String, List<String>> result = new HashMap<>();
    List<Member> allMembers = getAllMembers();

    for (Member member : allMembers) {
      result.computeIfAbsent(member.getSmallGroup().getId().toString(), k -> new ArrayList<>())
          .add(member.getName());
    }

    return result;
  }

  public Member getById(Long id) {
    return members.findById(id);
  }

  public Member getByName(String name) {
    return members.findByName(name);
  }

  public List<Member> getMembersBySmallGroup(Long id) {
    return members.findBySmallGroupId(id);
  }

  public List<Member> getMembersByNameContaining(String name) {
    return members.findByNameContaining(name);
  }

  @Transactional
  public boolean setMemberInfo(Member member) {
    try {
      members.updateInfo(member);
      return true;

    } catch (Exception e) {
      log.error("Failed to update member: " + e.getMessage());
      return false;

    }
  }

}

