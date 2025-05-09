package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import com.kori1304.jpayouthdepartmentregister.member.domain.Members;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class MembersJpaAdapter implements Members {

  private final MemberRepository memberRepository;

  @Override
  public MemberEntity save(MemberEntity member) {
    return memberRepository.save(member);
  }

  @Override
  public List<MemberEntity> findAll() {
    return memberRepository.findAll();
  }
}
