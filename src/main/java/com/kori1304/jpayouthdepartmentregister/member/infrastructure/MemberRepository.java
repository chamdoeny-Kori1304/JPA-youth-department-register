package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  MemberEntity findByName(String name);
  List<MemberEntity> findAllBySmallGroupId(Long smallGroupId);
  List<MemberEntity> findByNameContaining(String search);

}
