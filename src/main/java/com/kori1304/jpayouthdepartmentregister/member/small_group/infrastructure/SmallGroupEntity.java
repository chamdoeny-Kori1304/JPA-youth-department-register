package com.kori1304.jpayouthdepartmentregister.member.small_group.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import com.kori1304.jpayouthdepartmentregister.member.small_group.SmallGroup;
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


  static public SmallGroupEntity fromDomain(SmallGroup smallGroup) {
    if (smallGroup == null) {
      return null;
    }

    SmallGroupEntity entity = new SmallGroupEntity(smallGroup.getName());
    entity.setId(smallGroup.getId());

    return entity;
  }


  @Override
  public String toString() {
    return "SmallGroup{" +
        "name='" + name + '\'' +
        '}';
  }

  public SmallGroup toDomain() {

    return SmallGroup.builder()
        .id(this.getId())
        .name(name)
        .build();
  }

}
