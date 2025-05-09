package com.kori1304.jpayouthdepartmentregister.member.domain;

import com.kori1304.jpayouthdepartmentregister.member.infrastructure.MemberEntity;
import java.util.List;

public interface Members {
  MemberEntity save(MemberEntity member);
  List<MemberEntity> findAll();
}