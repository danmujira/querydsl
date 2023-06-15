package prj.danmuji.querydsl.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import prj.danmuji.querydsl.exception.NoUserException;
import prj.danmuji.querydsl.model.domain.User;
import prj.danmuji.querydsl.model.domain.UserDocument;
import prj.danmuji.querydsl.model.domain.repository.UserRepository;
import prj.danmuji.querydsl.model.domain.repository.UserRepositorySupport;
import prj.danmuji.querydsl.model.domain.repository.UserSearchQueryRepository;
import prj.danmuji.querydsl.model.domain.repository.UserSearchRepository;
import prj.danmuji.querydsl.model.dto.SearchCondition;
import prj.danmuji.querydsl.model.dto.UserDto;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static prj.danmuji.querydsl.model.domain.QUser.user;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositorySupport userRepositorySupport;
    private final UserRepository userRepository;
    private final UserSearchQueryRepository userSearchQueryRepository;
    private final UserSearchRepository userSearchRepository;

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
    public Page<User> getUserListPage(String name, int page, int size) {
        Predicate predicate = null;
        if (StringUtils.hasText(name)) {
            predicate = user.name.contains(name);
            return userRepository.findAll(predicate, PageRequest.of(page - 1, ((size == 0)?10:size), Sort.Direction.DESC, "seq"));
        } else {
            return userRepository.findAll(PageRequest.of(page - 1, ((size == 0)?10:size), Sort.Direction.DESC, "seq"));
        }
    }

    public List<UserDocument> getUserListByEs(SearchCondition q, Pageable pageable) {
        return userSearchQueryRepository.findByConditions(q, pageable);
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
    @Transactional(rollbackOn = Exception.class)
    public long saveUser(UserDto userDto) {
        // DB insert
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .state(userDto.getState())
                .role(userDto.getRole())
                .tag(userDto.getTag())
                .createAt(now)
                .build();
        userRepository.save(user);
        log.info("seq={}", user.getSeq());
        // ElasticSearch insert
        userSearchRepository.save(UserDocument.from(user));
        //webClient.saveUser(user.getSeq(), userDto, now);
        return 1;
    }

    /**
     * 유저 폰 정보 수정
     * @param id
     * @param phone
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public int updateUserPhone(@RequestBody long id, String phone) {
        // QuerydslRepositorySupport 사용
        userRepositorySupport.updateUserPhone(id, phone);
        // Elasticsearch 수정
        User user = userRepository.findById(id).orElseThrow(() -> new NoUserException(HttpStatus.BAD_REQUEST, "No user exist"));
        userSearchRepository.save(UserDocument.from(user));
        return 1;
    }
}
