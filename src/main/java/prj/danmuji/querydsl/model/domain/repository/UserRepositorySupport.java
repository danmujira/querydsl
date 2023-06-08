package prj.danmuji.querydsl.model.domain.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import prj.danmuji.querydsl.model.domain.User;

import static prj.danmuji.querydsl.model.domain.QUser.user;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {

    public UserRepositorySupport() {
        super(User.class);
    }

    public long updateUserPhone(long id, String phone) {
        return update(user)
                .set(user.phone, phone)
                .where(user.seq.eq(id))
                .execute();
    }

}
