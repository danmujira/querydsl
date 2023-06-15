package prj.danmuji.querydsl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import prj.danmuji.querydsl.model.domain.User;
import prj.danmuji.querydsl.model.domain.UserDocument;
import prj.danmuji.querydsl.model.dto.SearchCondition;
import prj.danmuji.querydsl.model.dto.UserDto;
import prj.danmuji.querydsl.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * 사용자 리스트 조회(JPA 사용)
     * @param name
     * @param page
     * @return
     */
    @GetMapping("")
    public Page<User> getUserListPage(String name, int page, int size) {
        return userService.getUserListPage(name, page, size);
    }

    /**
     * 사용자 리스트 조회(ElasticSearch 사용)
     * @param q name, phone, role, state, tag
     * @param pageable size, page
     * @return
     */
    @GetMapping("/es")
    public List<UserDocument> getUserList(SearchCondition q, Pageable pageable) {
        return userService.getUserListByEs(q, pageable);
    }

    /**
     * 사용자 등록(DB + ES)
     * @param userDto
     * @return
     */
    @PostMapping("")
    public long saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    /**
     * 폰 정보 변경(DB + ES)
     * @param param
     * @return
     */
    @PutMapping("")
    public long updateUserPhone(@RequestBody Map<String, Object> param) {
        return userService.updateUserPhone((int) param.get("id"), (String) param.get("phone"));
    }
}
