package com.kori1304.jpayouthdepartmentregister._common.jwt;

import com.kori1304.jpayouthdepartmentregister._common.jwt.dto.TokenDTO;
import com.kori1304.jpayouthdepartmentregister.user.domain.model.User;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

  TokenDTO generateTokenDTO(User user);

  String getUserId(String token);

  boolean validateToken(String token);

  Authentication getAuthentication(String token);
}
