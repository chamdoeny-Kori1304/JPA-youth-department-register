package com.kori1304.jpayouthdepartmentregister.member.application;


import com.kori1304.jpayouthdepartmentregister.member.domain.MemberEntity;
import com.kori1304.jpayouthdepartmentregister.member.dto.MemberDTO;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Long append(MemberDTO memberDTO) {

    MemberEntity newMember = memberRepository.save(MemberEntity.fromDTO(memberDTO));

    return newMember.getId();
  }

  public List<MemberDTO> getAllMembers() {
    List<MemberEntity> members = memberRepository.findAll();
    List<MemberDTO> memberDTOs = new ArrayList<>();

    int size = members.size();
    for (int i = 0; i < size; i++) {
      memberDTOs.add(members.get(i).toDTO());


    }
    return memberDTOs;
  }


}
