package com.kori1304.jpayouthdepartmentregister.user.domain;

import com.kori1304.jpayouthdepartmentregister.user.domain.model.User;

public interface Users {

  User save(User user);

  Boolean update(long userId, User user);

  User findByUsername(String username);

  User findByEmail(String email);

  User findById(Long id);

  boolean isExistsByEmail(String email);

  boolean isExistsById(long id);

  Boolean delete(long userId);
}
