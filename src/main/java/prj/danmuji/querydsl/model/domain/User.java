package prj.danmuji.querydsl.model.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import prj.danmuji.querydsl.constant.UserRole;
import prj.danmuji.querydsl.constant.UserState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private UserState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Column(name = "tag")
    private String tag;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Builder
    User(String name, String phone, UserState state, UserRole role, String tag, LocalDateTime createAt) {
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.role = role;
        this.tag = tag;
        this.createAt = createAt;
    }
}
