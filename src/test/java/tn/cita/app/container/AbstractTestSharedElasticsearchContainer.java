package tn.cita.app.container;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

public abstract class AbstractTestSharedElasticsearchContainer {
	
	protected static final ElasticsearchContainer ELASTICSEARCH_CONTAINER;
	
	static {
		ELASTICSEARCH_CONTAINER = new ElasticsearchContainer("elasticsearch:7.16.3");
		ELASTICSEARCH_CONTAINER.start();
	}
	
	
	
}







