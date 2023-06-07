package prj.danmuji.querydsl.model.domain.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import prj.danmuji.querydsl.model.domain.UserDocument;

import java.util.List;

@Repository
public interface UserSearchRepository extends ElasticsearchRepository<UserDocument, Long> {
    List<UserDocument> findByName(String name);
    List<UserDocument> findByTag(String tag);
}
