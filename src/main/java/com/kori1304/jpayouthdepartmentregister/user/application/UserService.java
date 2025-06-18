package com.kori1304.jpayouthdepartmentregister.user.application;

import com.kori1304.jpayouthdepartmentregister.user.domain.Users;
import com.kori1304.jpayouthdepartmentregister.user.domain.model.Role;
import com.kori1304.jpayouthdepartmentregister.user.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService  {

  private final Users users;

  @Autowired
  public UserService(Users users) {
    this.users = users;
  }

  @Transactional
  public User getById(String userId) throws UsernameNotFoundException {
    User user = users.findById(Long.parseLong(userId));

    List<GrantedAuthority> authorities = new ArrayList<>();

    for (Role memberRole : user.getUserRoles()) {
      String authorityName = memberRole.getAuthority().getName();
      authorities.add(new SimpleGrantedAuthority(authorityName));
    }

    user.setAuthorities(authorities);
    return user;
  }
}
