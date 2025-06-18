package com.kori1304.jpayouthdepartmentregister.user.domain.model;


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
public class Role {

  private long userId;
  private long authorityId;

  private Authority authority;
}
