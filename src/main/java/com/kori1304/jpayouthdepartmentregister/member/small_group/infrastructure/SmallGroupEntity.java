package com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class SmallGroupEntity extends BaseEntity {

  private String name;


  @Override
  public String toString() {
    return "SmallGroup{" +
        "name='" + name + '\'' +
        '}';
  }
}
