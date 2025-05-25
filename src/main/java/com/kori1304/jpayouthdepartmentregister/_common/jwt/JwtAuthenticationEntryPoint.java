package com.kori1304.jpayouthdepartmentregister._common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * [HTTP] 401(Unauthorized)
 *
 *
 * 인증되지 않은 사용자가 보호된, 리소스에 접근하려고 할 때 호출.
 * 즉, 로그인 안되어 있으면 발생
*/
 @Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
		response.setContentType("application/json;charset=UTF-8");

		String body = new ObjectMapper().writeValueAsString(
				Map.of(
						"message", "로그인이 필요합니다.",
						"exception", authException.getClass().getSimpleName()
				)
		);

		response.getWriter().write(body);
	}
}