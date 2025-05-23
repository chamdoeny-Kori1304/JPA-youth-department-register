package com.kori1304.jpayouthdepartmentregister.user.infrastructure;

import com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserEntity, Long> {


  UserEntity findByEmail(String email);

  /* 설명. JPQL과 @Query를 활용한 구문 */
  @Query("SELECT MAX(u.id) FROM UserEntity u")    // 설명. JPQL에서 엔티티 이름은 대소문자까지 완벽히 일치할 것!
  int maxMemberCode();

  UserEntity findByName(String username);

  boolean existsByEmail(String email);
}
