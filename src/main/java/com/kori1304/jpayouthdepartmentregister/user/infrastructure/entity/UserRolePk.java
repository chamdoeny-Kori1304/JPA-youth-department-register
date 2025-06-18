package com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 설명. 복합키 타입을 정의할 때는 Serializable을 반드시 구현 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRolePk implements Serializable {

    private long userId;
    private long authorityId;

}

