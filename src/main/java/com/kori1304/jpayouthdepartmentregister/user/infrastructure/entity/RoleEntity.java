package com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(UserRolePk.class)
@Builder(toBuilder = true)
public class RoleEntity  {

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Id
  @Column(name = "authority_id")
  private Long authorityId;

  @ManyToOne
  @JoinColumn(name = "authority_id", insertable = false, updatable = false)
  private AuthorityEntity authorityEntity;
//@ManyToOne
//@MapsId("authorityId") // 👈 복합키 필드 authorityId와 연결
//@JoinColumn(name = "authority_id")
//private AuthorityEntity authorityEntity;


  public RoleEntity(long userId, long authorityId) {
    this.userId = userId;
    this.authorityId = authorityId;
  }

  public RoleEntity(int userid, int authorityId) {
    this.userId =  (long) userid;
    this.authorityId = (long) authorityId;
  }
}

