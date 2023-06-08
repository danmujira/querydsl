package prj.danmuji.querydsl.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;
import prj.danmuji.querydsl.model.domain.User;
import prj.danmuji.querydsl.model.domain.UserDocument;
import prj.danmuji.querydsl.model.dto.SearchCondition;
import prj.danmuji.querydsl.model.dto.UserDto;
import prj.danmuji.querydsl.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
@SpringBootTest
@ActiveProfiles("local")
class UserControllerTest {
    @Autowired
    private UserService userService;

    @Test
    void getUserListPage() {
        Page<User> userList = userService.getUserListPage("danmuji", 1);
        log.info("{}", userList.getTotalElements());
        assertNotEquals(0, userList.getTotalElements());
    }

    @Test
    void getUserList() {
        List<UserDocument> userList = userService.getUserListByEs(SearchCondition.builder().name("danmuji0").build(), PageRequest.of(0, 10));
        assertNotEquals(0, userList.size());
    }

    @Test
    void saveUser() {
        UserDto userDto = UserDto.builder()
                .name("danmuji13")
                .phone("01012341234")
                .state(UserState.ACTIVE)
                .role(UserRole.ADMIN)
                .tag("tag13 tag10 mysql jpa elastic java")
                .build();
        long result = userService.saveUser(userDto);
        assertNotEquals(0, result);

        List<UserDocument> userList = userService.getUserListByEs(SearchCondition.builder().name("danmuji13").build(), PageRequest.of(0, 10));
        assertNotEquals(0, userList.size());
    }

    @Test
    void updateUserPhone() {
        int result = userService.updateUserPhone(58, "01011112222");
        assertNotEquals(0, result);

        List<UserDocument> userList = userService.getUserListByEs(SearchCondition.builder().seq(58).phone("01011112222").build(), PageRequest.of(0, 10));
        log.info("{}", userList);
        assertNotEquals(0, userList.size());
    }
}