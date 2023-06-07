package prj.danmuji.querydsl.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="search.elastic")
@Configuration
@Data
public class ElasticsearchProp {
    String url;
    String indexName;
}
