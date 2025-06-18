package com.kori1304.jpayouthdepartmentregister._common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ContentTypeCheckFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // 로그인 요청만 검사
    if ("POST".equalsIgnoreCase(request.getMethod())
        && request.getRequestURI().equals("/auth/login")) {

      String contentType = request.getContentType();

      if (contentType == null || !contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
        response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE); // 415
        response.setContentType("application/json;charset=UTF-8");

        String errorJson = new ObjectMapper().writeValueAsString(
            Map.of(
                "message", "Content-Type은 'application/json'이어야 합니다.",
                "exception", ContentTypeCheckFilter.class.getSimpleName()

            )
        );

        response.getWriter().write(errorJson);
        return; // 필터 체인 종료
      }
    }

    // 정상 요청은 계속 흐름 진행
    filterChain.doFilter(request, response);
  }


}

