package com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmallGroupRepository extends JpaRepository<SmallGroupEntity, Long> {

  Boolean existsByName(String name);

  SmallGroupEntity getByName(String name);
}
