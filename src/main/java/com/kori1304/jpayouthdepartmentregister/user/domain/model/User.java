package com.kori1304.jpayouthdepartmentregister.user.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {



  private Long id;
  private String name;
  private String email;
  private String password;
  private List<Role> userRoles;
}
