package com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity;

import com.kori1304.jpayouthdepartmentregister._common.BaseEntity;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class UserEntity extends BaseEntity {

    private String name;
    private String email;
    private String phoneNumber;

    @Column(nullable = true)
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<RoleEntity> userRoles;

}
