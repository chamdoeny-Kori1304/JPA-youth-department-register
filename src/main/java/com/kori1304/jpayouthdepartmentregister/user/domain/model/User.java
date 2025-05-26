package com.kori1304.jpayouthdepartmentregister.user.domain.model;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails {
  private Long id;
  private String name;
  private String email;
  private String password;
  private List<Role> userRoles;
  private Collection<GrantedAuthority> authorities;

  @Override
  public String getUsername() {
    return name;
  }

}
