package prj.danmuji.querydsl.model.domain.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import prj.danmuji.querydsl.model.domain.UserDocument;
import prj.danmuji.querydsl.model.dto.SearchCondition;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserSearchQueryRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    /**
     * ES 검색
     * @param q
     * @param pageable
     * @return
     */
    public List<UserDocument> findByConditions(SearchCondition q, Pageable pageable) {
        CriteriaQuery query = createQuery(q).setPageable(pageable);
        SearchHits<UserDocument> search = elasticsearchOperations.search(query, UserDocument.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    /**
     * 검색 조건 세팅
     * @param q 검색조건
     * @return
     */
    private CriteriaQuery createQuery(SearchCondition q) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());
        if (q.getSeq() > 0) {
            query.addCriteria(Criteria.where("id").is(q.getSeq()));
        }
        if (StringUtils.hasText(q.getName())) {
            query.addCriteria(Criteria.where("name").is(q.getName()));
        }
        if (StringUtils.hasText(q.getPhone())) {
            query.addCriteria(Criteria.where("phone").is(q.getPhone()));
        }
        if (!ObjectUtils.isEmpty(q.getState())) {
            query.addCriteria(Criteria.where("state").is(q.getState()));
        }
        if (!ObjectUtils.isEmpty(q.getRole())) {
            query.addCriteria(Criteria.where("role").is(q.getRole()));
        }
        if (!ObjectUtils.isEmpty(q.getTag())) {
            query.addCriteria(Criteria.where("tag").matches(q.getTag()));
        }
        query.addSort(Sort.by("id").descending());
        return query;

    }
}
