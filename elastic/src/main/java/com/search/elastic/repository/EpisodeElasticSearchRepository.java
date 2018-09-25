package com.search.elastic.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.search.model.Episode;

public interface EpisodeElasticSearchRepository extends ElasticsearchRepository<Episode, String> {
	
	Episode findById(String id);
	
	@Query("{\"bool\": {\"must\": [{\"match\": {\"metadata.region\": \"?0\"}}]}}")
	Page<Episode> findByRegion(String region, Pageable pageable);
	
	@Query("{\r\n" + 
			"  \"query\" : {\r\n" + 
			"    \"bool\" : {\r\n" + 
			"      \"must_not\" : {\r\n" +
			"        \"term\" : {\r\n" + 
			"          \"metadata.tags\" : \"?0\"\r\n" + 
			"        }\r\n" + 
			"      }\r\n" + 
			"    }\r\n" + 
			"  }\r\n" + 
			"}")
	Page<Episode> findByTags(List<String> tags, Pageable pageable);
	
	//Filter not working need to check
	/*@Query("{\r\n" + 
			"  \"query\" : {\r\n" + 
			"    \"bool\" : {\r\n" + 
			"      \"must_not\" : {\r\n" +
			"        \"term\" : {\r\n" + 
			"          \"metadata.tags\" : \"?0\"\r\n" + 
			"        }\r\n" + 
			"      },\r\n" + 
			"		\"filter\": {\r\n" + 
			"		   \"term\": {\r\n" + 
			"				\"metadata.tags\" : \"?0\"\r\n" +
			"			 }\r\n" + 
			"		 }\r\n" + 
			"    }\r\n" + 
			"  }\r\n" + 
			"}")
	Page<Episode> findByTags(List<String> tags, Pageable pageable);*/

}
