package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.domain.Members;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class MembersJpaAdapter implements Members {

  private final MemberRepository memberRepository;

  @Override
  public Member save(Member member) {
    MemberEntity entity = MemberEntity.fromDomain(member);

    return memberRepository.save(entity).toDomain();
  }

  @Override
  public List<Member> findAll() {
    List<Member> result = new ArrayList<>();
    List<MemberEntity> members = memberRepository.findAll();

    for (MemberEntity member : members) {
      result.add(member.toDomain());
    }

    return result;
  }

  @Override
  public List<Member> findBySmallGroupName(String name) {
    List<Member> result = new ArrayList<>();
    List<MemberEntity> members = memberRepository.findAllBySmallGroupEntity_Name(name);

    for (MemberEntity member : members) {
      result.add(member.toDomain());
    }

    return result;
  }

  @Override
  public Member findByName(String name) {

    return memberRepository.findByName(name).toDomain();
  }
}
