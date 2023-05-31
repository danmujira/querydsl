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
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

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

    @CreationTimestamp
    @Column(name = "create_at")
    LocalDateTime createAt;

    @Builder
    User(String name, String phone, UserState state, UserRole role) {
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.role = role;
    }
}
