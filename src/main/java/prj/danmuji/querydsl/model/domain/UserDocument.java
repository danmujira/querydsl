package prj.danmuji.querydsl.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "index04")
public class UserDocument {
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String phone;
    @Field(type = FieldType.Keyword)
    private UserState state;
    @Field(type = FieldType.Keyword)
    private UserRole role;
    @Field(type = FieldType.Text)
    private String tag;
    @Field(type = FieldType.Date, format = {DateFormat.date_hour_minute_second})
    private LocalDateTime createAt;

    public static UserDocument from(User user) {
        return UserDocument.builder()
                .id(user.getSeq())
                .name(user.getName())
                .phone(user.getPhone())
                .state(user.getState())
                .role(user.getRole())
                .tag(user.getTag())
                .createAt(user.getCreateAt())
                .build();
    }

}
