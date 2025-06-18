package com.kori1304.jpayouthdepartmentregister._common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


/* 설명.
 *  Spring Security Filter Chain에서 인증 실패 시 실행되는 핸들러.
 *  AuthenticationEntryPoint 인터페이스를 구현해 인증되지 않은 사용자가 보호된 리소스에 접근하려 할 때
 *  HttpServletResponse를 사용하여 적절한 HTTP 응답을 보낸다.
 *  ===================================================================================================================
 *  참고:
 *  요청에 대한 응답의 결과로 403(Forbidden) 응답 코드 대신, 가끔 404(Not Found) 응답 코드를 사용해야 할 때도 있다.
 *  이는 보안상의 이유로 리소스의 존재 여부 자체를 클라이언트에게 감추기 위해서다.
 *  403은 서버가 요청을 이해 했고, 요청한 리소스가 존재하지만 권한이 없음을 의미한다.
 *  또한 404는 서버가 요청한 리소스를 찾을 수 없거나 존재하지 않음을 의미한다.
 *  즉, 관리자만 접근할 수 있는 페이지라던가 고객들에게 프라이빗한 페이지를 보장하고 있는 서비스라면
 *  요청한 경로에 리소스가 존재한다는 사실 자체를 숨기는 편이 더 합리적이기 때문에
 *  404 응답을 반환하여 정보 노출을 방지할 수 있다.
 * */

/**
 * [HTTP] 403(Forbidden)
 * 인증된 사용자이지만 해당 리소스에 접근할 권한이 부족할 때 호출
 * 즉, 로그인되었지만, 그 사용자에게 해당 작업을 수행할 권한이 없을 때 발생.
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
		response.setContentType("application/json;charset=UTF-8");

		String body = new ObjectMapper().writeValueAsString(
				Map.of(
						"message", "접근 권한이 없습니다.",
						"exception", accessDeniedException.getClass().getSimpleName()
				)
		);

		response.getWriter().write(body);
	}
}
