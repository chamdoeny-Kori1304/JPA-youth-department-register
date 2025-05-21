package com.kori1304.jpayouthdepartmentregister._common.jwt;

import com.kori1304.jpayouthdepartmentregister._common.jwt.dto.TokenDTO;
import com.kori1304.jpayouthdepartmentregister.user.infrastructure.UserEntity;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

  TokenDTO generateTokenDTO(UserEntity user);

  String getUserId(String token);

  boolean validateToken(String token);

  Authentication getAuthentication(String token);
}
