package com.kori1304.jpayouthdepartmentregister._common.util;

import com.kori1304.jpayouthdepartmentregister._common.RepositoryAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

/**
 * @Repository를 사용한 객체에서 exception 처리를 위해 사용
 * <p>
 * 모든 케이스마다 사용하지 말고 최소한으로 사용 1. http 500을 줄 것들만 정의 2. return 있다면 handler 필요 없음
 */

@Slf4j
public class RepositoryExceptionHandler {
  public static String dbExceptionMsg = "데이터베이스 처리 중 오류 발생";
  public static String unexpectedMsg = "데이터베이스 처리 중 오류 발생";

  @FunctionalInterface
  public interface ExceptionalSupplier<T> {

    T get() throws Exception;
  }

  public static <T> T execute(ExceptionalSupplier<T> supplier) {
    try {
      T result = supplier.get();

      if (result == null) {
        throw new IllegalStateException("DB 작업 결과가 null입니다.");
      }

      return result;

    } catch (DataAccessException e) {
      log.error(" 데이터베이스 예외 발생: {}", e.getMessage(), e);

      throw new RepositoryAccessException(unexpectedMsg, e);
    } catch (Exception e) {
      log.error("알 수 없는 예외 발생: {}", e.getMessage(), e);

      throw new RepositoryAccessException(unexpectedMsg, e);
    }
  }
}

