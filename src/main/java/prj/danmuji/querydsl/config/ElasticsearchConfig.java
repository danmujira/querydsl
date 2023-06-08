package prj.danmuji.querydsl.config;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import prj.danmuji.querydsl.config.properties.ElasticsearchProp;

@RequiredArgsConstructor
public class ElasticsearchConfig extends AbstractElasticsearchConfig {

    private final ElasticsearchProp elasticsearchProp;

    /**
     * elastic 설정 prop를 이용하여 기본정보 설정
     * @return
     */
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchProp.getUrl())
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
