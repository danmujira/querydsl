package prj.danmuji.querydsl.model.dto;

import lombok.Data;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;

@Data
public class UserDto {
    private String name;
    private String phone;
    private UserState state = UserState.ACTIVE;
    private UserRole role = UserRole.USER;
    private String tag;
}
