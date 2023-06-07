package prj.danmuji.querydsl.config;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import prj.danmuji.querydsl.config.properties.ElasticsearchProp;

@RequiredArgsConstructor
public class ElasticsearchConfig extends AbstractElasticsearchConfig {

    private final ElasticsearchProp elasticsearchProp;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchProp.getUrl())
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
