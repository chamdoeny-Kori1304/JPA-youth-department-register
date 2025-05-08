package com.kori1304.jpayouthdepartmentregister.member.infrastructure;

import com.kori1304.jpayouthdepartmentregister.member.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
