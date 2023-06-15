package prj.danmuji.querydsl.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;

@Getter
@Setter
@Builder
public class SearchCondition {
    Long seq;
    String name;
    String phone;
    UserRole role;
    UserState state;
    String tag;
}
