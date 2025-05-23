package com.kori1304.jpayouthdepartmentregister.user.infrastructure;

import com.kori1304.jpayouthdepartmentregister._common.jwt.TokenProvider;
import com.kori1304.jpayouthdepartmentregister._common.util.RepositoryExceptionHandler;
import com.kori1304.jpayouthdepartmentregister.user.domain.Users;
import com.kori1304.jpayouthdepartmentregister.user.domain.model.User;
import com.kori1304.jpayouthdepartmentregister.user.infrastructure.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class UserJpaAdapter implements Users {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  private final ModelMapper modelMapper;
  private final RoleRepository roleRepository;

  @Override
  public User save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    UserEntity newUser = modelMapper.map(user, UserEntity.class);

    return RepositoryExceptionHandler.execute(() -> {
      UserEntity result = userRepository.save(newUser);    // 설명. 반환형은 int값이 아닌 엔티티임.

      return modelMapper.map(result, User.class);
    });
  }

  @Override
  public Boolean update(long userId, User user) {
    UserEntity newUser = modelMapper.map(user, UserEntity.class);

    return RepositoryExceptionHandler.execute(() -> {
      UserEntity entity = userRepository.save(newUser);    // 설명. 반환형은 int값이 아닌 엔티티임.

      return entity.getId() == userId;
    });
  }

  @Override
  public User findByUsername(String username) {
    return RepositoryExceptionHandler.execute(() -> {

      UserEntity entity = userRepository.findByName(username);
      return modelMapper.map(entity, User.class);
    });
  }

  @Override
  public User findByEmail(String email) {
    return RepositoryExceptionHandler.execute(() -> {

      UserEntity entity = userRepository.findByEmail(email);
      return modelMapper.map(entity, User.class);
    });
  }

  @Override
  public User findById(Long id) {
    return RepositoryExceptionHandler.execute(() -> {

      UserEntity entity = userRepository.findById(id).get();
      return modelMapper.map(entity, User.class);
    });
  }

  @Override
  public boolean isExistsByEmail(String email) {

    return userRepository.existsByEmail(email);
  }

  @Override
  public boolean isExistsById(long id) {
    return userRepository.existsById(id);
  }

  @Override
  public Boolean delete(long userId) {
    return null;
  }
}
