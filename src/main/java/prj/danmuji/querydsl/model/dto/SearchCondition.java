package prj.danmuji.querydsl.model.dto;

import lombok.Getter;
import lombok.Setter;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;

@Getter
@Setter
public class SearchCondition {
    String name;
    String phone;
    UserRole role;
    UserState state;
    String tag;
}
