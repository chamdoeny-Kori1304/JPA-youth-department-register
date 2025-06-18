package com.kori1304.jpayouthdepartmentregister.user.api;


import com.kori1304.jpayouthdepartmentregister._common.ResponseDTO;
import com.kori1304.jpayouthdepartmentregister.user.application.AuthService;
import com.kori1304.jpayouthdepartmentregister.user.dto.LoginDTO;
import com.kori1304.jpayouthdepartmentregister.user.dto.SignupDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private static final Logger log = LoggerFactory.getLogger(AuthService.class);


  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }


  @Operation(summary = "로그인 요청", description = "로그인 및 인증이 진행됩니다. 에러가 없다면 token 발급", tags = {"AuthController"})
  @PostMapping("/login")
  public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {

    return ResponseEntity
        .ok()
        .body(new ResponseDTO(HttpStatus.OK, "로그인 성공~", authService.login(loginDTO)));

  }

  @Operation(summary = "회원 가입 요청", description = "회원 가입이 진행됩니다.", tags = {"AuthController"})
  @PostMapping("/signup")
  public ResponseEntity<ResponseDTO> signup(@RequestBody SignupDTO signupDTO) {  // 회원 가입 정보를 받아 냄
    log.info("입려력된 값:  " + signupDTO.toString());

    return ResponseEntity
        .ok()
        .body(new ResponseDTO(HttpStatus.CREATED, "회원가입 성공", authService.signup(signupDTO)));

  }

  //TODO 필요시 추가, 현재 사용 X
  @Operation(summary = "비밀 번호 변경", description = "비밀번호 변경, 아직은 사용하지 않습니다. ", tags = {"AuthController"})
  @PostMapping("/password/reset")
  public ResponseEntity<ResponseDTO> resetPassword(@RequestBody SignupDTO signupDTO) {  // 회원 가입 정보를 받아 냄
    log.info("입려력된 값:  " + signupDTO.toString());

    return ResponseEntity
        .ok()
        .body(new ResponseDTO(HttpStatus.FORBIDDEN, "미개발 API, 사용 안 함", new Object()));

  }
}
