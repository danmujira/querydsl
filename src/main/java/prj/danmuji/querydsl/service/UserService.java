package prj.danmuji.querydsl.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import prj.danmuji.querydsl.exception.NoUserException;
import prj.danmuji.querydsl.model.domain.User;
import prj.danmuji.querydsl.model.domain.repository.UserRepository;
import prj.danmuji.querydsl.model.domain.repository.UserRepositorySupport;
import prj.danmuji.querydsl.model.dto.UserDto;

import java.util.List;

import static prj.danmuji.querydsl.model.domain.QUser.user;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositorySupport userRepositorySupport;
    private final UserRepository userRepository;

    public List<User> getUserList() {
        // JpaRepository 사용
        return userRepository.findAll();
    }

    public User getUserOne(String name) {
        // QuerydslPredicateExecutor 사용
        Predicate predicate = user.name.eq(name);
        return userRepository.findOne(predicate).orElseThrow(() -> new NoUserException(HttpStatus.BAD_REQUEST, "No User Found"));
    }

    public long saveUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .state(userDto.getState())
                .role(userDto.getRole())
                .build();
        userRepository.save(user);
        return 1;
    }

    public long updateUserPhone(@RequestBody long id, String phone) {
        // QuerydslRepositorySupport 사용
        return userRepositorySupport.updateUserPhone(id, phone);
    }
}
