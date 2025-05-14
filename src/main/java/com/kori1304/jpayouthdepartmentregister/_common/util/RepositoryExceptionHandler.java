package com.kori1304.jpayouthdepartmentregister._common.util;

import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @Repository를 사용한 객체에서 exception 처리를 위해 사용
 */

//// 모든 케이스마다 사용하지 말고 최소한으로 사용,
//// 1. http 500을 줄 것들만 정의
//// 2. return 있다면 handler 필요 없음

@Slf4j
public class RepositoryExceptionHandler {

  public static <T> T execute(Supplier<T> supplier) {
    try {
      T result = supplier.get();

      if (result == null) {
        throw new IllegalStateException("supplier.get() returned null");
      }

      return result;

    } catch (DataAccessResourceFailureException | DataIntegrityViolationException e) {
      log.error("치명적 DB 예외 발생: {}", e.getMessage(), e);
      throw e;
    } catch (DataAccessException e) {
      // 그 외 데이터 접근 예외는 필요 시 공통 예외로 래핑
      log.error("데이터 접근 예외 발생: {}", e.getMessage(), e);
      throw new RuntimeException("데이터 처리 중 예외 발생", e);
    }
  }
}

//@Slf4j
//public class RepositoryExceptionHandler {
//
//  public static <T> T executeWithCatch(Supplier<T> supplier) {
//    try {
//      return supplier.get();
//    }
//    catch (DataAccessResourceFailureException e) {
//      log.error("데이터베이스 연결 실패: {}", e.getMessage());
//      throw new DataAccessResourceFailureException(e.getMessage(), e);
//    } catch (DataIntegrityViolationException e) {
//      log.error("데이터 무결성 오류 발생: {}", e.getMessage());
//      throw new DataIntegrityViolationException(e.getMessage(), e);
//
//    } catch (EmptyResultDataAccessException e) {
//      log.error("결과 없음: {}", e);
//      return (T) List.of(); // 또는 상황에 맞게
//    }
//    catch (DataAccessException e) {
//      log.error("결과 없음: {}", e.getStackTrace());
//
//      throw new DataIntegrityViolationException(e.getMessage(), e.getCause());
//    }
//    catch (DataException e) {
//      log.error("기타 예외 발생: {}", e.getMessage());
//      throw new DataException("기타 예외 발생" + e.getMessage(), e.getSQLException());
//    }
//
//  }
//}


