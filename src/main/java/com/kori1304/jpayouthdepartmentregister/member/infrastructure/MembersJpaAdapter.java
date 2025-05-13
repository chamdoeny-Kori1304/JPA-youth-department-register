package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.util.RepositoryExceptionHandler;
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

    return RepositoryExceptionHandler.execute(() -> memberRepository.save(entity).toDomain());
  }

  @Override
  public Member updateInfo(Member member) {
    return RepositoryExceptionHandler.execute(() -> {
      Long id = memberRepository.findByName(member.getName()).getId();
      MemberEntity entity = MemberEntity.fromDomain(member);
      entity.setId(id);

      return memberRepository.save(entity).toDomain();
    });
  }

  @Override
  public List<Member> findAll() {
    return RepositoryExceptionHandler.execute(() -> {
      List<Member> result = new ArrayList<>();
      List<MemberEntity> members = memberRepository.findAll();

      if (members == null || members.isEmpty()) {
        return result;
      }

      for (MemberEntity member : members) {
        result.add(member.toDomain());
      }

      return result;
    });

  }

  @Override
  public List<Member> findBySmallGroupId(Long id) {
    return RepositoryExceptionHandler.execute(() -> {

      List<Member> result = new ArrayList<>();
      List<MemberEntity> members = memberRepository.findAllBySmallGroupId(id);

      for (MemberEntity member : members) {
        result.add(member.toDomain());
      }

      return result;
    });
  }

  @Override
  public List<Member> findByNameContaining(String search) {
    return RepositoryExceptionHandler.execute(() -> {
      List<Member> result = new ArrayList<>();

      List<MemberEntity> entities = memberRepository.findByNameContaining(search);

      for (MemberEntity entity : entities) {
        result.add(entity.toDomain());
      }

      return result;

    });
  }


  @Override
  public Member findByName(String name) {
    return RepositoryExceptionHandler.execute(() -> {

          MemberEntity entity = memberRepository.findByName(name);
          return entity.toDomain();
        }
    );
  }

  @Override
  public Member findById(Long id) {
    return RepositoryExceptionHandler.execute(() ->
        memberRepository.findById(id).get().toDomain()
    );
  }
}
