package com.kori1304.jpayouthdepartmentregister._common.jwt;

import org.springframework.security.core.Authentication;

public interface TokenProvider  {

  boolean validateToken(String jwt);

  Authentication getAuthentication(String jwt);
}
