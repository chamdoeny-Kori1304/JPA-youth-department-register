package com.kori1304.jpayouthdepartmentregister.user.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "role")
@Builder(toBuilder = true)
public class RoleEntity {

  @Id
  @Column(name = "member_code")
  private Long memberNo;

  @Id
  @Column(name = "authority_code")
  private int authorityCode;

  @ManyToOne
  @JoinColumn(name = "authority_code", insertable = false, updatable = false)
  private AuthorityEntity authorityEntity;

}
