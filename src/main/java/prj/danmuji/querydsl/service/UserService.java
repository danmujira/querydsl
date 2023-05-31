package prj.danmuji.querydsl.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    /**
     * 모든 유저 정보 조회
     * @return
     */
    public List<User> getUserList() {
        // JpaRepository 사용
        return userRepository.findAll();
    }

    /**
     * 이름 검색조건의 페이징 데이터 조회
     * @param name
     * @param page
     * @return
     */
    public Page<User> getUserListPage(String name, int page) {
        Predicate predicate = null;
        if (StringUtils.hasText(name)) {
            predicate = user.name.contains(name);
        }
        return userRepository.findAll(predicate, PageRequest.of(page - 1, 10, Sort.Direction.DESC, "seq"));
    }

    /**
     * 이름에 해당하는 유저 정보 조회
     * @param name
     * @return
     */
    public User getUserOne(String name) {
        // QuerydslPredicateExecutor 사용
        Predicate predicate = user.name.eq(name);
        return userRepository.findOne(predicate).orElseThrow(() -> new NoUserException(HttpStatus.BAD_REQUEST, "No User Found"));
    }

    /**
     * 유저 등록
     * @param userDto
     * @return
     */
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

    /**
     * 유저 폰 정보 수정
     * @param id
     * @param phone
     * @return
     */
    public long updateUserPhone(@RequestBody long id, String phone) {
        // QuerydslRepositorySupport 사용
        return userRepositorySupport.updateUserPhone(id, phone);
    }
}
