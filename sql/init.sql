-- 테이블 생성


-- 1. 권한 테이블
CREATE TABLE authority (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL UNIQUE,
                           `desc` VARCHAR(255)
);

-- 2. 사용자 테이블
CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE,
                      phone_number VARCHAR(20),
                      password VARCHAR(255),
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 3. 역할 매핑 테이블
CREATE TABLE role (
                      user_id BIGINT NOT NULL,
                      authority_id BIGINT NOT NULL,
                      PRIMARY KEY (user_id, authority_id),
                      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                      FOREIGN KEY (authority_id) REFERENCES authority(id) ON DELETE CASCADE
);

-- 4 authority에 관한 추가
INSERT INTO authority (name, `desc`) VALUES
                                         ('ROLE_USER', '일반 사용자 권한'),
                                         ('ROLE_ADMIN', '관리자 권한'),
                                         ('ROLE_MANAGER', '매니저 권한');
