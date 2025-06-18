package com.kori1304.jpayouthdepartmentregister.user.application;

import com.kori1304.jpayouthdepartmentregister._common.exception.BusinessRuleViolationException;
import com.kori1304.jpayouthdepartmentregister._common.jwt.TokenProvider;
import com.kori1304.jpayouthdepartmentregister.user.domain.Users;
import com.kori1304.jpayouthdepartmentregister.user.domain.model.User;
import com.kori1304.jpayouthdepartmentregister.user.dto.LoginDTO;
import com.kori1304.jpayouthdepartmentregister.user.dto.SignupDTO;
import com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity.RoleEntity;
import com.kori1304.jpayouthdepartmentregister.user.infrastructure.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private static final Logger log = LoggerFactory.getLogger(AuthService.class);
  private final Users users;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  private final ModelMapper modelMapper;
  private final RoleRepository roleRepository;


  /**
   * @param: LoginDTO {email. password}
   * @return: TokenDTO token
   */
  public Object login(LoginDTO loginDTO) {

    String msg = "이메일과 비밀번호가 매칭되지 않습니다. 이메일과 비밀번호를 제대로 입력해주세요 ";
    log.info("[AuthService] login() START");
    log.info("[AuthService] {}", loginDTO);

    /* 1. 아이디 조회 */
    if (!users.isExistsByEmail(loginDTO.getEmail())) {
      log.info("[AuthService] login() Required User Not Found!");
      throw new BusinessRuleViolationException(msg);
    }

    /* 2. 비밀번호 매칭 */

    User user = users.findByEmail(loginDTO.getEmail());
    if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
      log.info("[AuthService] login() Password Match Failed!");
      throw new BusinessRuleViolationException(msg);
    }

    /* 3. 토큰 발급 */
    return tokenProvider.generateTokenDTO(user);
  }


  /**
   * @param: UserDTO {name,email. password}
   * @return: String user.email
   */
  @Transactional
  public String signup(SignupDTO signupDTO) {
    log.info("[AuthService] signup() Start.");
    log.info("[AuthService] memberDTO {}", signupDTO);

    /*  이메일 중복 유효성 검사(비즈니스 로직에 따라 선택적으로 구현하면 됨) */
    if (users.isExistsByEmail(signupDTO.getEmail())) {
      log.info("[AuthService] 이메일이 중복됩니다.");
      throw new BusinessRuleViolationException("이메일이 중복됩니다.");
    }

    User user = modelMapper.map(signupDTO, User.class);
    User newUser = users.save(user);

    RoleEntity newRole = new RoleEntity(newUser.getId(), 2);
    roleRepository.save(newRole);

    log.info("[AuthService] signup() End.");

    return signupDTO.getEmail();
  }
}
