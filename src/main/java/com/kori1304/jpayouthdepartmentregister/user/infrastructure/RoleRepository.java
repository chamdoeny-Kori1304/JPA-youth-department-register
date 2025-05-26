package com.kori1304.jpayouthdepartmentregister.user.infrastructure;

import com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity.RoleEntity;
import com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity.UserRolePk;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository  extends JpaRepository<RoleEntity, UserRolePk> {

}
