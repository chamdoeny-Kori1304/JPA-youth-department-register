package com.kori1304.jpayouthdepartmentregister.member.small_group;

import com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure.SmallGroupEntity;
import com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure.SmallGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SmallGroupService {

  final private SmallGroupRepository smallGroupRepository;


  public SmallGroupEntity add(String name) {

    return smallGroupRepository.save(new SmallGroupEntity(name));
  }

  public boolean b_isNameExist(String name) {

    return smallGroupRepository.existsByName(name);
  }

  public SmallGroup getByName(String name) {

    return  smallGroupRepository.getByName(name).toDomain();
  }
}
