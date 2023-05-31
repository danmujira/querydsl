package prj.danmuji.querydsl.model.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import prj.danmuji.querydsl.model.domain.User;
import prj.danmuji.querydsl.model.dto.UserDto;

import javax.transaction.Transactional;
import java.util.List;

import static prj.danmuji.querydsl.model.domain.QUser.user;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    public UserRepositorySupport() {
        super(User.class);
    }

    @Transactional
    public long updateUserPhone(long id, String phone) {
        return update(user)
                .set(user.phone, phone)
                .where(user.seq.eq(id))
                .execute();
    }

}
