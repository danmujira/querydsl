package prj.danmuji.querydsl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;
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

    @GetMapping("")
    public Page<User> getUserListPage(String name, int page) {
        return userService.getUserListPage(name, page);
    }

    @GetMapping("/es")
    public List<UserDocument> getUserList(SearchCondition q, Pageable pageable) {
        return userService.getUserListByEs(q, pageable);
    }

    @PostMapping("")
    public long saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping("")
    public long updateUserPhone(@RequestBody Map<String, Object> param) {
        return userService.updateUserPhone((int) param.get("id"), (String) param.get("phone"));
    }
}
