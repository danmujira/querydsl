package prj.danmuji.querydsl.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;

@Getter
@Setter
@Builder
public class UserDto {
    private String name;
    private String phone;
    private UserState state = UserState.ACTIVE;
    private UserRole role = UserRole.USER;
    private String tag;
}
