package com.kori1304.jpayouthdepartmentregister.member.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.kori1304.jpayouthdepartmentregister.common.factory.MemberTestFactory;
import com.kori1304.jpayouthdepartmentregister.common.factory.MemberEntityTestFactory;
import com.kori1304.jpayouthdepartmentregister.member.domain.Member;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberEntity;
import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberRepository;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class MemberServiceTest {

  private final List<Member> memberDTOList =  MemberTestFactory.createSampleMembers();
  private final List<MemberEntity> memberEntityList =  MemberEntityTestFactory.createSampleEntities();


  @Autowired
  private MemberService memberService;

  @MockitoBean
  private MemberRepository memberRepository;

  @Test
  void contextLoads() {

    int expect = 5;
//    when(memberService.getAllMembers()).thenReturn(memberDTOList);
    when( memberRepository.findAll()).thenReturn(memberEntityList);

    Map<String, List<String>>  result = memberService.getMemberNamesGroupedByTeam();

    log.info(result.keySet().toString());

    // Assert
    assertEquals(expect, result.size());

  }

  @Test
  void append_success() {
    // given
    Member member = MemberTestFactory.getSampleMemberDTO(4);

    MemberEntity mockEntity = MemberEntity.fromDomain(member);

    when(memberRepository.save(any(MemberEntity.class))).thenReturn(mockEntity);

    // when
    boolean result = memberService.append(member);

    // then
    assertTrue(result);
    verify(memberRepository, times(1)).save(any(MemberEntity.class));
  }

  // TODO 예외 처리를 따로 둬야하나?
  @Test
  void append_failure() {
    // given
    Member member = Member.builder().name("에러케이스").build();


    when(memberRepository.save(any(MemberEntity.class))).thenThrow(new RuntimeException("DB Error"));

    // when
    boolean result = memberService.append(member);

    // then
    assertFalse(result);
    verify(memberRepository, times(1)).save(any(MemberEntity.class));
  }

}