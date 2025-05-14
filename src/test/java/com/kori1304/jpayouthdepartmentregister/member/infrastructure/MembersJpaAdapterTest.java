package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kori1304.jpayouthdepartmentregister.common.factory.MemberEntityTestFactory;
import com.kori1304.jpayouthdepartmentregister.common.factory.MemberTestFactory;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MembersJpaAdapterTest {

  @Mock
  private MemberRepository memberRepository;

  @InjectMocks
  private MembersJpaAdapter membersJpaAdapter;

  @Test
  void save_성공_도메인으로_변환() {
    Member domain = MemberTestFactory.getSampleMemberDTO(0);
    MemberEntity entity = MemberEntity.fromDomain(domain);
    MemberEntity savedEntity = MemberEntity.fromDomain(domain);

    when(memberRepository.save(any())).thenReturn(savedEntity);
//    when(savedEntity.toDomain()).thenReturn(domain);

    Member result = membersJpaAdapter.save(domain);

    assertEquals(domain.getName(), result.getName());
    verify(memberRepository).save(any());
  }

  @Test
  void findAll_빈리스트_반환() {
    when(memberRepository.findAll()).thenReturn(List.of());

    List<Member> result = membersJpaAdapter.findAll();

    assertTrue(result.isEmpty());
    verify(memberRepository).findAll();
  }

  @Test
  void findByName_정상조회() {
    MemberEntity entity = MemberEntityTestFactory.getSampleMemberEntity(0);
    Member domain = entity.toDomain();

    when(memberRepository.findByName(any())).thenReturn(entity);
//    when(entity.toDomain()).thenReturn(domain);

    Member result = membersJpaAdapter.findByName("홍길동");

    assertEquals(domain.getName(), result.getName());
  }

  // 기타 테스트 추가 가능
}
