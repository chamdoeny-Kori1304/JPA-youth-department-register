package com.kori1304.jpayouthdepartmentregister.member.domain;

import java.util.List;

public interface Members {
  Member save(Member member);
  Member updateInfo(Member member);

  List<Member>  findAll();

  List<Member> findBySmallGroupId(Long id);
  Member findById(Long id);
  Member findByName(String name);

  List<Member> findByNameContaining(String keyword);
}