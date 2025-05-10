package com.kori1304.jpayouthdepartmentregister.member.domain;

import java.util.List;

public interface Members {
  Member save(Member member);
  List<Member>  findAll();

  List<Member> findBySmallGroupName(String team);
  Member findByName(String name);

}